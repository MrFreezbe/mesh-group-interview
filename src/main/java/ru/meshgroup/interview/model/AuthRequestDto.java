package ru.meshgroup.interview.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "AuthRequestDto", description = "Contains the email and password for authentication")
public class AuthRequestDto {
    @Schema(description = "Email of the user", example = "user@example.com", requiredMode = REQUIRED)
    private String email;
    @Schema(description = "Password of the user", requiredMode = REQUIRED)
    private String password;
}
