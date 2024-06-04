package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.config.JwtService;
import org.example.dtos.AuthenticationResponse;
import org.example.dtos.UserVM;
import org.example.entities.Role;
import org.example.entities.User;
import org.example.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// todo: maybe put in UserService or separate authentication Controller from UserController
@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(UserVM userVM) {
		User user = User.builder()
				.userName(userVM.getUsername())
				.email(userVM.getEmail())
				.password(passwordEncoder.encode(userVM.getPassword()))
				.role(Role.USER)
				.build();

		userRepository.save(user);
		String jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

	public AuthenticationResponse authenticate(UserVM userVM) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userVM.getEmail(), userVM.getPassword()));
		User user = userRepository.findByEmail(userVM.getEmail()).orElseThrow();
		String jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}
}
