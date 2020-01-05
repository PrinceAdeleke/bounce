package com.findmycar.bounce.service;

import com.findmycar.bounce.entity.Account;
import com.findmycar.bounce.entity.Transmitter;
import com.findmycar.bounce.entity.Vehicle;
import com.findmycar.bounce.exception.MaxTransmittersForAccountExceeded;
import com.findmycar.bounce.repository.TransmitterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransmitterServiceTest {

    @Mock
    private AccountService accountService;

    @Mock
    private TransmitterRepository transmitterRepository;

    @InjectMocks
    private TransmitterService transmitterService;

    private List<Transmitter> transmitters;

    @Before
    public void setup() {
        transmitters = LongStream
                .range(0, 3)
                .mapToObj(x -> createTransmitterWithId(UUID.randomUUID()))
                .collect(Collectors.toList());

        when(accountService.getAccountById(any()))
                .thenReturn(new Account());
    }

    @Test(expected = MaxTransmittersForAccountExceeded.class)
    public void whenExceedingTransmitterMaxCount_thenThrowMaxTransmittersForAccountExceededException() {
        when(transmitterRepository.getAllByAccount(any(Account.class)))
                .thenReturn(transmitters);

        transmitterService.createTransmitter(any(), createTransmitterWithId(UUID.randomUUID()), new Vehicle());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenFetchingANonExistentTransmitter_thenThrowEntityNotFoundException() {
        // No need to mock non existent object
        assertThat(transmitterService.getTransmitter(any(), UUID.randomUUID()).getId()).isEqualTo(UUID.randomUUID());
    }

    @Test
    public void whenFetchingTransmitter_thenReturnTransmitter() {
        UUID transmitterId = UUID.randomUUID();
        Transmitter expectedTransmitter = createTransmitterWithId(transmitterId);
        when(transmitterRepository.getByAccountAndId(any(Account.class), any()))
                .thenReturn(Optional.of(expectedTransmitter));

        assertThat(transmitterService.getTransmitter(any(), transmitterId).getId()).isEqualTo(transmitterId);
    }

    @Test
    public void whenFetchingTransmitterList_thenReturnTransmitterList() {
        when(transmitterRepository.getAllByAccount(any()))
                .thenReturn(transmitters);

        List<Transmitter> expectedTransmitters = transmitterService.getTransmittersForAccount(UUID.randomUUID());
        assertThat(expectedTransmitters.size()).isEqualTo(transmitters.size());
    }

    private Transmitter createTransmitterWithId(UUID id) {
        return Transmitter
                .builder()
                .id(id)
                .build();
    }
}