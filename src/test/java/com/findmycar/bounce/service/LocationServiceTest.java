package com.findmycar.bounce.service;

import com.findmycar.bounce.entity.Account;
import com.findmycar.bounce.entity.Location;
import com.findmycar.bounce.repository.LocationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceTest {

    @Mock
    private AccountService accountService;

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    private final UUID accountId = UUID.randomUUID();

    @Test(expected = EntityNotFoundException.class)
    public void whenFetchingLocationsForANonExistentAccount_thenThrowEntityNotFoundException() {
        when(accountService.getAccountById(accountId))
                .thenThrow(EntityNotFoundException.class);

        locationService.getLastLocationsByAccountId(accountId);
    }

    @Test
    public void whenFetchingALocationForANonExistentAccount_thenThrowEntityNotFoundException() {
        Account account = createAccount(accountId);
        when(accountService.getAccountById(accountId))
                .thenReturn(account);

        List<Location> locations = IntStream.range(0, 10)
                .mapToObj(x -> Location.builder().build())
                .collect(Collectors.toList());

        when(locationRepository.getLastLocationsByAccount(account))
                .thenReturn(locations);

        List<Location> locationsForAccount = locationService.getLastLocationsByAccountId(accountId);
        assertThat(locationsForAccount).isEqualTo(locations);
    }

    private Account createAccount(UUID accountId) {
        return Account.builder().id(accountId).build();
    }

}
