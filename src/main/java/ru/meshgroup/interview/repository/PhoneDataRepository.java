package ru.meshgroup.interview.repository;

import org.springframework.stereotype.Repository;
import ru.meshgroup.interview.domain.PhoneData;

import java.util.Optional;

@Repository
public interface PhoneDataRepository extends BaseRepository<PhoneData> {
    Optional<PhoneData> findByPhone(String phone);
}
