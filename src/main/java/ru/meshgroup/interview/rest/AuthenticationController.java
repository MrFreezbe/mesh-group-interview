package ru.meshgroup.interview.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.meshgroup.interview.config.security.JwtTokenProvider;
import ru.meshgroup.interview.model.AuthenticationRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/authenticate")
public class AuthenticationController {
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            String token = jwtTokenProvider.createToken(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
