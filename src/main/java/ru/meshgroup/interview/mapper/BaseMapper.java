package ru.meshgroup.interview.mapper;

public interface BaseMapper<Entity, Dto> {
    Entity toEntity(Dto dto);

    Dto toDto(Entity entity);
}
