package ru.meshgroup.interview.repository;

import org.springframework.stereotype.Repository;
import ru.meshgroup.interview.domain.EmailData;

import java.util.Optional;

@Repository
public interface EmailDataRepository extends BaseRepository<EmailData> {
    Optional<EmailData> findByEmail(String email);
}
