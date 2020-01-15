package com.findmycar.bounce.service;

import com.findmycar.bounce.entity.Account;
import com.findmycar.bounce.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test(expected = EntityNotFoundException.class)
    public void whenFetchingNonExistentAccount_thenThrowEntityNotFoundException() {
        accountService.getAccountById(UUID.randomUUID());
    }

    @Test
    public void whenFetchingAccount_thenReturnAccountFromDB() {
        UUID accountId = UUID.randomUUID();
        Account expectedAccount = Account.builder().id(accountId).build();
        Optional<Account> optionalAccount = Optional.of(expectedAccount);

        when(accountRepository.findById(accountId)).thenReturn(optionalAccount);
        assertThat(accountService.getAccountById(accountId).getId()).isEqualTo(optionalAccount.get().getId());
    }
}
