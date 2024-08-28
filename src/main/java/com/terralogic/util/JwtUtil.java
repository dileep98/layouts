package com.terralogic.util;

import com.terralogic.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtil {
    public static Optional<User> getCurrentLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        return Optional.of((User) auth.getPrincipal());
    }
}
