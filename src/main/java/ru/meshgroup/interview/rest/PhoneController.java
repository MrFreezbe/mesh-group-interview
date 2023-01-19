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
import ru.meshgroup.interview.model.PhoneDataDto;
import ru.meshgroup.interview.service.PhoneService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/phone")
public class PhoneController {

    private final PhoneService phoneService;

    @DeleteMapping("/{userId}/phones/{phoneId}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long userId, @PathVariable Long phoneId) {
        phoneService.deleteAttribute(userId, phoneId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}/phones/{phoneId}")
    public ResponseEntity<PhoneDataDto> updatePhone(@PathVariable Long userId,
                                                    @PathVariable Long phoneId,
                                                    @RequestBody @Valid PhoneDataDto phoneDTO) {
        PhoneDataDto updatedPhone = phoneService.updateAttribute(userId, phoneId, phoneDTO);
        return ResponseEntity.ok(updatedPhone);
    }

    @PostMapping("/{userId}/phones")
    public ResponseEntity<PhoneDataDto> addPhone(@PathVariable Long userId,
                                                 @RequestBody @Valid PhoneDataDto phoneDTO) {
        PhoneDataDto addedPhone = phoneService.addAttributeToUser(userId, phoneDTO);
        return ResponseEntity.ok(addedPhone);
    }
}
