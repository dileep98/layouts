package com.terralogic.service;

import com.terralogic.entity.Role;
import com.terralogic.entity.User;
import com.terralogic.model.LoginResponse;
import com.terralogic.model.LoginUser;
import com.terralogic.model.RegisterUser;
import com.terralogic.model.RoleEnum;
import com.terralogic.repository.RoleRepository;
import com.terralogic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse signup(RegisterUser input) {
        Role userRole = roleRepository.findByName(RoleEnum.USER).orElseThrow();

        User user = User.builder()
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(userRole)
                .build();

        User savedUser = userRepository.save(user);
        return getLoginResponse(savedUser);
    }

    public LoginResponse authenticate(LoginUser input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        User user = userRepository.findByEmail(input.getEmail()).orElseThrow();
        return getLoginResponse(user);
    }

    private LoginResponse getLoginResponse(User user) {
        String jwtToken = jwtService.generateToken(user);

        return LoginResponse.builder()
                .accessToken(jwtToken)
                .expiresIn(jwtService.getJwtExpiration())
                .build();
    }
}
