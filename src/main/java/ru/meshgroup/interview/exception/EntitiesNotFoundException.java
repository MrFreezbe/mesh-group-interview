package ru.meshgroup.interview.exception;

public class EntitiesNotFoundException extends BaseException {
    public EntitiesNotFoundException(Class<?> entityClass, long id) {
        super(String.format("Entity %s with id %d not found", entityClass.getSimpleName(), id));
    }

    public EntitiesNotFoundException(long id) {
        super(String.format("Entity with id %d not found", id));
    }

    public EntitiesNotFoundException(String message) {
        super(message);
    }
}
