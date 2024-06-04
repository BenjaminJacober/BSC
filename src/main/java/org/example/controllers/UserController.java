package org.example.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dtos.AuthenticationResponse;
import org.example.dtos.UserVM;
import org.example.repositories.UserRepository;
import org.example.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
// todo: set base Mapping
public class UserController {

	//	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	private final UserRepository userRepository;
	private final AuthenticationService authenticationService;

	@GetMapping("/users")
	public List<UserVM> getAllUsers() {
		return UserVM.from(userRepository.findAll());
	}

	@PostMapping("/users/register")
	public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody @Valid UserVM userVM) {
		return ResponseEntity.ok(authenticationService.register(userVM));
	}

	@PostMapping("/users/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody @Valid UserVM userVM) {
		return ResponseEntity.ok(authenticationService.authenticate(userVM));
	}

}
