package com.todo_app.back_end.todo_app_back_end.security.auth;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.todo_app.back_end.todo_app_back_end.Exceptions.ConflictException;
import com.todo_app.back_end.todo_app_back_end.security.config.JwtService;
import com.todo_app.back_end.todo_app_back_end.security.models.Role;
import com.todo_app.back_end.todo_app_back_end.security.models.User;
import com.todo_app.back_end.todo_app_back_end.security.models.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        var user = User.builder()

                .firstName(request.getFirstName())

                .lastName(request.getLastName())

                .email(request.getEmail())

                .password(passwordEncoder.encode(request.getPassword()))

                .role(Role.ROLE_USER)

                .build();

        if (!userRepo.existsByEmail(user.getEmail())) {

            userRepo.save(user);

            var jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse.builder().token(jwtToken).build();

        }

        throw new ConflictException(String.format("Todo With Email '%s' Is Already Exists!", user.getEmail()));

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getEmail(), request.getPassword()

                )

        );

        var user = userRepo.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();

    }

}
