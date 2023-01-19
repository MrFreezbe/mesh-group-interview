package ru.meshgroup.interview.mapper;


import org.mapstruct.Mapper;
import ru.meshgroup.interview.domain.PhoneData;
import ru.meshgroup.interview.model.PhoneDataDto;

@Mapper(componentModel = "spring")
public interface PhoneMapper extends BaseMapper<PhoneData, PhoneDataDto> {
}