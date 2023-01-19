package ru.meshgroup.interview.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.meshgroup.interview.domain.EmailData;
import ru.meshgroup.interview.exception.BadRequestException;
import ru.meshgroup.interview.mapper.EmailMapper;
import ru.meshgroup.interview.model.EmailDataDto;
import ru.meshgroup.interview.repository.EmailDataRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailService extends UserAttributeService<EmailData, EmailDataDto, EmailDataRepository, EmailMapper> {

    @Override
    protected void setAttribute(EmailData emailData, String attributeValue) {
        emailData.setEmail(attributeValue);
    }

    @Override
    protected void checkIfAttributeExists(String attribute) {
        if (repository.findByEmail(attribute).isPresent()) {
            throw new BadRequestException("Email already exists");
        }
    }
}
