package ru.meshgroup.interview.mapper;

import org.mapstruct.Mapper;
import ru.meshgroup.interview.domain.EmailData;
import ru.meshgroup.interview.model.EmailDataDto;

@Mapper(componentModel = "spring")
public interface EmailMapper extends BaseMapper<EmailData, EmailDataDto> {
}