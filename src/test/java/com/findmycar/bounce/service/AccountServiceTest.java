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
        accountService.getAccountById(2L);
    }

    @Test
    public void whenFetchingAccount_thenReturnAccount() {
        Account expectedAccount = Account.builder().id(2L).build();
        Optional<Account> optionalAccount = Optional.of(expectedAccount);

        when(accountRepository.findById(2L)).thenReturn(optionalAccount);
        assertThat(accountService.getAccountById(2L).getId()).isEqualTo(optionalAccount.get().getId());
    }
}
