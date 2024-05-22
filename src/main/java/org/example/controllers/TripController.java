package org.example.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.entities.User;
import org.example.entities.Trip;
import org.example.repositories.UserRepository;
import org.example.repositories.TripRepository;
import org.example.view_models.TripVM;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TripController {

    private final TripRepository repository;
    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public TripController(TripRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @GetMapping("/trips")
    public List<TripVM> getAllTrips() {
        return TripVM.from(repository.findAll());
    }

    @Transactional
    @PostMapping("/trips/create")
    public TripVM createTrip(@RequestBody TripVM tripVM) {

        System.out.println(tripVM.toString());

        // todo: Do i really need to do this?
        User user = userRepository.findByUserName(tripVM.getUserName()).get(0);

        Trip trip = new Trip(tripVM.getName(), tripVM.getDescription(), user, Collections.emptyList());
        entityManager.persist(trip);

        return TripVM.from(trip);
    }

}