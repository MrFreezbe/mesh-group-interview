package ru.meshgroup.interview.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.meshgroup.interview.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    @Query("SELECT u FROM User u JOIN u.emailData e WHERE e.email = :email AND u.password = :password")
    Optional<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}

