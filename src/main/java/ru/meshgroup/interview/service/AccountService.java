package ru.meshgroup.interview.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.meshgroup.interview.domain.Account;
import ru.meshgroup.interview.exception.BadRequestException;
import ru.meshgroup.interview.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository repository;
    private final UserService userService;

    @Scheduled(fixedRate = 30000)
    public void increaseBalance() {
        repository.findAll().forEach(account -> {
            BigDecimal currentBalance = account.getBalance();
            BigDecimal newBalance = currentBalance.add(currentBalance.multiply(BigDecimal.valueOf(0.1)));
            if (newBalance.compareTo(account.getInitialBalance().multiply(BigDecimal.valueOf(2.07))) <= 0) {
                account.setBalance(newBalance);
                repository.save(account);
            }
        });
    }

    /**
     * Simplest solution of money transfer (suppose that we have a few users)
     * @param fromUserId id of user who sends money
     * @param toUserId id of user who receives money
     * @param value amount of money
     */
    public synchronized void transferMoney(Long fromUserId, Long toUserId, BigDecimal value) {
        Account fromAccount = userService.getUserById(fromUserId).getAccount();
        Account toAccount = userService.getUserById(toUserId).getAccount();

        // Validate that the fromAccount has enough balance to transfer
        if (fromAccount.getBalance().compareTo(value) < 0) {
            throw new BadRequestException("Insufficient funds in the account");
        }
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Invalid transfer amount");
        }
        transferMoney(fromAccount, toAccount, value);
    }

    private void transferMoney(Account fromAccount, Account toAccount, BigDecimal value) {
        try {
            fromAccount.setBalance(fromAccount.getBalance().subtract(value));
            toAccount.setBalance(toAccount.getBalance().add(value));
            repository.saveAll(Arrays.asList(fromAccount, toAccount));
        } catch (Exception e) {
            throw new RuntimeException("Transfer failed");
        }
    }
}