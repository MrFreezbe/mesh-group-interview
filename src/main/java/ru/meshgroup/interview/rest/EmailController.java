package ru.meshgroup.interview.rest;

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
public class EmailController {
    private final EmailService emailService;

    @DeleteMapping("/{userId}/emails/{emailId}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long userId, @PathVariable Long emailId) {
        emailService.deleteAttribute(userId, emailId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}/emails/{emailId}")
    public ResponseEntity<EmailDataDto> updateEmail(@PathVariable Long userId,
                                                    @PathVariable Long emailId,
                                                    @RequestBody @Valid EmailDataDto emailDTO) {
        EmailDataDto updatedEmail = emailService.updateAttribute(userId, emailId, emailDTO);
        return ResponseEntity.ok(updatedEmail);
    }

    @PostMapping("/{userId}/emails")
    public ResponseEntity<EmailDataDto> addEmail(@PathVariable Long userId,
                                                 @RequestBody @Valid EmailDataDto emailDTO) {
        EmailDataDto addedEmail = emailService.addAttributeToUser(userId, emailDTO);
        return ResponseEntity.ok(addedEmail);
    }
}
