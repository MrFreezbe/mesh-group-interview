package ru.meshgroup.interview.repository;

import org.springframework.stereotype.Repository;
import ru.meshgroup.interview.domain.Account;

@Repository
public interface AccountRepository extends BaseRepository<Account> {
}
