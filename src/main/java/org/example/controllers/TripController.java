package org.example.controllers;

import jakarta.transaction.Transactional;
import org.example.dtos.TripVM;
import org.example.entities.Trip;
import org.example.entities.User;
import org.example.repositories.TripRepository;
import org.example.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TripController {

	private final TripRepository tripRepository;
	private final UserRepository userRepository;

	public TripController(TripRepository tripRepository, UserRepository userRepository) {
		this.tripRepository = tripRepository;
		this.userRepository = userRepository;
	}

	@GetMapping("/trips")
	public List<TripVM> getAllTrips() {
		return TripVM.from(tripRepository.findAll());
	}

	@Transactional
	@PostMapping("/trips/create")
	public TripVM createTrip(@RequestBody TripVM tripVM) {

		System.out.println(tripVM.toString());

		// todo: Do i really need to do this?
		User user = userRepository.findById(tripVM.getUserId()).get();

		Trip trip = new Trip(tripVM.getName(), tripVM.getDescription(), user, Collections.emptyList());
		tripRepository.save(trip);

		return TripVM.from(trip);
	}

}
