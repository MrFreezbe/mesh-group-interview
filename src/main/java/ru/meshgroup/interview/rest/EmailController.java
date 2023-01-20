package ru.meshgroup.interview.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.meshgroup.interview.model.EmailDataDto;
import ru.meshgroup.interview.service.EmailService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/email")
@OpenAPIDefinition(info =
@Info(title = "Email API", version = "1.0"))
public class EmailController {
    private final EmailService emailService;

    @Operation(summary = "Delete email by id",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Successfully deleted"),
            })
    @DeleteMapping("/{userId}/emails/{emailId}")
    public ResponseEntity<Void> deleteEmail(
            @Parameter(description = "Id of the user", required = true) @PathVariable Long userId,
            @Parameter(description = "Id of the email", required = true) @PathVariable Long emailId) {
        emailService.deleteAttribute(userId, emailId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update email by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated",
                            content = @Content(schema = @Schema(implementation = EmailDataDto.class))),
            })
    @PutMapping("/{userId}/emails/{emailId}")
    public ResponseEntity<EmailDataDto> updateEmail(
            @Parameter(description = "Id of the user", required = true) @PathVariable Long userId,
            @Parameter(description = "Id of the email", required = true) @PathVariable Long emailId,
            @RequestBody @Valid @Parameter(description = "Email data", required = true) EmailDataDto emailDTO) {
        EmailDataDto updatedEmail = emailService.updateAttribute(userId, emailId, emailDTO);
        return ResponseEntity.ok(updatedEmail);
    }

    @Operation(summary = "Create new email",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully created",
                            content = @Content(schema = @Schema(implementation = EmailDataDto.class))),
            })
    @PostMapping("/{userId}/emails")
    public ResponseEntity<EmailDataDto> addEmail(
            @Parameter(description = "Id of the user", required = true) @PathVariable Long userId,
            @RequestBody @Valid @Parameter(description = "Email data", required = true) EmailDataDto emailDTO) {
        EmailDataDto addedEmail = emailService.addAttributeToUser(userId, emailDTO);
        return ResponseEntity.ok(addedEmail);
    }
}

