package ru.meshgroup.interview.repository;

import org.springframework.stereotype.Repository;
import ru.meshgroup.interview.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByEmailData_EmailAndPassword(String email, String password);
}

