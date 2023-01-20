package ru.meshgroup.interview.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.meshgroup.interview.config.security.JwtTokenProvider;
import ru.meshgroup.interview.model.AuthRequestDto;

@RestController
@OpenAPIDefinition(info = @Info(title = "Authentication API", version = "1.0"))
@RequiredArgsConstructor
@RequestMapping("/v1/authenticate")
public class AuthController {
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "Authenticates a user and returns a JWT token",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully authenticated and returned token"),
                    @ApiResponse(responseCode = "401", description = "Invalid credentials")
            })
    @PostMapping
    public ResponseEntity<String> authenticate(@Parameter(description = "Contains the email and password for authentication", required = true)
                                               @RequestBody AuthRequestDto request) {
        try {
            String token = jwtTokenProvider.createToken(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
