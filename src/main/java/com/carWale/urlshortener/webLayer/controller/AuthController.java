package com.carWale.urlshortener.webLayer.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carWale.urlshortener.webLayer.dto.AuthRequest;
import com.carWale.urlshortener.webLayer.dto.UserDto;
import com.carWale.urlshortener.webLayer.service.MyUserDetailsService;
import com.carWale.urlshortener.serverLayer.model.User;
import com.carWale.urlshortener.webLayer.utils.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws Exception {
		UserDto dtoModel = new UserDto();
		System.out.println("User input :"+authRequest.getPassword()+" "+authRequest.getUsername());
		try {
			authenticationManager
					.authenticate((Authentication) new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
							authRequest.getPassword()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}

		final User userDetails = findByusername(authRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails.getUsername());
		
		dtoModel.setEmail(userDetails.getUsername());
		dtoModel.setJwt(jwt);
		dtoModel.setMessage("Login Successful");
		
		return new ResponseEntity<>(dtoModel, HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody UserDto userDto) {
		User userDetails = null;
		try {
			userDetails = userDetailsService.findByUsername(userDto.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (userDetails == null) {
			User user = new User();
			user.setEmail(userDto.getEmail());
			user.setUsername(userDto.getEmail());
			user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Hash the password

			try {
				userDetailsService.createUser(user);
			} catch (Exception e) {
				return (ResponseEntity<String>) ResponseEntity.internalServerError();
			}
			System.out.println(user);
			return ResponseEntity.ok("User registered successfully!");
		}
		return ResponseEntity.ofNullable("User already exist");

	}

	private User findByusername(String username) throws Exception {
		final User userDetails = userDetailsService.findByUsername(username);
		return userDetails;
	}
}
