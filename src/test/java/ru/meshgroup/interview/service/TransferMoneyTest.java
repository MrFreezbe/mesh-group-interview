package ru.meshgroup.interview.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;
import ru.meshgroup.interview.AbstractTest;
import ru.meshgroup.interview.domain.Account;
import ru.meshgroup.interview.domain.User;
import ru.meshgroup.interview.repository.AccountRepository;
import ru.meshgroup.interview.repository.UserRepository;

import java.math.BigDecimal;

@SpringBootTest
class TransferMoneyTest extends AbstractTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Test
    @Transactional
    void testTransferMoney() {
        // create two users
        User userOne = userRepository.save(new User());
        User userTwo = userRepository.save(new User());

        // create accounts for users
        Account accountOne = new Account();
        accountOne.setBalance(BigDecimal.ONE);
        accountOne.setUser(userOne);
        userOne.setAccount(accountOne);
        accountRepository.save(accountOne);

        Account accountTwo = new Account();
        accountTwo.setBalance(BigDecimal.ONE);
        accountTwo.setUser(userTwo);
        userTwo.setAccount(accountTwo);
        accountRepository.save(accountTwo);

        // transfer money
        accountService.transferMoney(userOne.getId(), userTwo.getId(), BigDecimal.ONE);

        // check that money was transferred
        Assertions.assertEquals(BigDecimal.ZERO, accountOne.getBalance());
        Assertions.assertEquals(BigDecimal.valueOf(2), accountTwo.getBalance());
    }

    @Test
    void testOptimisticLocking() {
        User user = userRepository.save(new User());

        Account account = new Account();
        account.setUser(user);
        account.setBalance(BigDecimal.valueOf(100));
        accountRepository.save(account);

        Account account1 = accountRepository.findById(account.getId()).get();
        Account account2 = accountRepository.findById(account.getId()).get();
        account1.setBalance(BigDecimal.valueOf(200));
        accountRepository.save(account1);
        // this should throw an OptimisticLockException as the version of account2 is out of date
        Assertions.assertThrows(ObjectOptimisticLockingFailureException.class, () -> accountRepository.save(account2));
    }
}
