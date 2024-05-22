package org.example.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.entities.User;
import org.example.repositories.UserRepository;
import org.example.view_models.UserVM;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
// todo: set base Mapping
public class UserController {

    private final UserRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    public List<UserVM> getAllUsers() {
        return UserVM.from(repository.findAll());
    }

    @Transactional
    @PostMapping("/users/create")
    public UserVM createUser(@RequestBody UserVM userVM) {
        User user = new User(userVM.getUserName(), userVM.getHashedPassword(), userVM.getEmailAddress());
        entityManager.persist(user);

        return UserVM.from(user);
    }


}
