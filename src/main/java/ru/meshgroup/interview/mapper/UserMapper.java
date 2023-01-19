package ru.meshgroup.interview.mapper;

import org.mapstruct.Mapper;
import ru.meshgroup.interview.domain.User;
import ru.meshgroup.interview.model.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {
}
