package com.todo_app.back_end.todo_app_back_end.security.auth;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.graphql.data.method.annotation.Argument;

import org.springframework.graphql.data.method.annotation.MutationMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @MutationMapping
    public AuthenticationResponse register(

            @Argument(name = "input") RegisterRequest request

    ) {

        return authenticationService.register(request);

    }

    @MutationMapping
    public AuthenticationResponse authenticate(

            @Argument(name = "input") AuthenticationRequest request

    ) {

        return authenticationService.authenticate(request);

    }

}
