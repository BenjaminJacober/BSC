package org.example.controllers;

import org.example.repositories.BSC_UserRepository;
import org.example.view_models.BSC_UserVM;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BSC_UserController {

    private final BSC_UserRepository repository;

    public BSC_UserController(BSC_UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    public List<BSC_UserVM> getAllUsers() {
        return BSC_UserVM.from(repository.findAll());
    }
}
