package com.findmycar.bounce.service;

import com.findmycar.bounce.entity.Account;
import com.findmycar.bounce.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Find user by id
     * @param id User id
     * @return User object
     * @throws EntityNotFoundException if a user has not been found
     */
    public Account getAccountById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Cannot find user with id %s", id)
                ));
    }
}
