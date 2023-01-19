package ru.meshgroup.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.meshgroup.interview.domain.BaseEntity;
import ru.meshgroup.interview.domain.User;
import ru.meshgroup.interview.exception.BadRequestException;
import ru.meshgroup.interview.exception.EntitiesNotFoundException;
import ru.meshgroup.interview.mapper.BaseMapper;
import ru.meshgroup.interview.model.BaseDto;
import ru.meshgroup.interview.repository.BaseRepository;

@Component
public abstract class UserAttributeService<Entity extends BaseEntity, Dto extends BaseDto,
        Repository extends BaseRepository<Entity>, Mapper extends BaseMapper<Entity, Dto>> {

    protected Repository repository;

    private Mapper mapper;

    private UserService userService;

    private Entity getEntity(Long userId, Long attributeId) {
        Entity entity = repository.findById(attributeId)
                .orElseThrow(() -> new EntitiesNotFoundException(attributeId));
        if (!entity.getUser().getId().equals(userId)) {
            throw new BadRequestException("Entity does not belong to the user with id: " + userId);
        }
        return entity;
    }

    public Dto updateAttribute(Long userId, Long attributeId, Dto dto) {
        Entity entity = getEntity(userId, attributeId);
        String attributeValue = dto.getAttributeValue();
        checkIfAttributeExists(attributeValue);
        setAttribute(entity, attributeValue);
        Entity updatedEntity = repository.save(entity);
        return mapper.toDto(updatedEntity);
    }

    public Dto addAttribute(Long userId, Dto dto) {
        checkIfAttributeExists(dto.getAttributeValue());
        User user = userService.getUserById(userId);

        Entity entity = mapper.toEntity(dto);
        entity.setUser(user);

        return mapper.toDto(repository.save(entity));
    }

    public void deleteAttribute(Long userId, Long attributeId) {
        Entity entity = getEntity(userId, attributeId);
        repository.delete(entity);
    }

    @Autowired
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    protected abstract void setAttribute(Entity entity, String attributeValue);

    protected abstract void checkIfAttributeExists(String attribute);
}
