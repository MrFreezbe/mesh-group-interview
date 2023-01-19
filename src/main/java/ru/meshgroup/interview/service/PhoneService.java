package ru.meshgroup.interview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.meshgroup.interview.domain.PhoneData;
import ru.meshgroup.interview.exception.BadRequestException;
import ru.meshgroup.interview.mapper.PhoneMapper;
import ru.meshgroup.interview.model.PhoneDataDto;
import ru.meshgroup.interview.repository.PhoneDataRepository;

@Service
@RequiredArgsConstructor
public class PhoneService extends UserAttributeService<PhoneData, PhoneDataDto, PhoneDataRepository, PhoneMapper> {
    @Override
    protected void setAttribute(PhoneData phoneData, String attributeValue) {
        phoneData.setPhone(attributeValue);
    }

    @Override
    protected void checkIfAttributeExists(String attribute) {
        if (repository.findByPhone(attribute).isPresent()) {
            throw new BadRequestException("Phone already exists");
        }
    }
}