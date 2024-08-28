package com.terralogic.controller;

import com.terralogic.model.LoginResponse;
import com.terralogic.model.LoginUser;
import com.terralogic.model.RegisterUser;
import com.terralogic.service.AuthenticationService;
import com.terralogic.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> register(@RequestBody @Valid RegisterUser registerUserDto) {
        LoginResponse registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid LoginUser loginUserDto) {
        LoginResponse loginResponse = authenticationService.authenticate(loginUserDto);

        return ResponseEntity.ok(loginResponse);
    }
}
