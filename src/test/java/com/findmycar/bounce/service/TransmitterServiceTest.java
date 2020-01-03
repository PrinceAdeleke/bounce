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
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

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
                .mapToObj(this::createTransmitterWithId)
                .collect(Collectors.toList());

        when(accountService.getAccountById(anyLong()))
                .thenReturn(new Account());
    }

    @Test(expected = MaxTransmittersForAccountExceeded.class)
    public void whenExceedingTransmitterMaxCount_thenThrowMaxTransmittersForAccountExceededException() {
        when(transmitterRepository.getAllByAccount(any(Account.class)))
                .thenReturn(transmitters);

        transmitterService.createTransmitter(anyLong(), createTransmitterWithId(2L), new Vehicle());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenFetchingANonExistentTransmitter_thenThrowEntityNotFoundException() {
        // No need to mock non existent object
        assertThat(transmitterService.getTransmitter(anyLong(), 2L).getId()).isEqualTo(2L);
    }

    @Test
    public void whenFetchingTransmitter_thenReturnTransmitter() {
        Transmitter expectedTransmitter = createTransmitterWithId(2L);
        when(transmitterRepository.getByAccountAndId(any(Account.class), anyLong()))
                .thenReturn(Optional.of(expectedTransmitter));

        assertThat(transmitterService.getTransmitter(anyLong(), 2L).getId()).isEqualTo(2L);
    }

    @Test
    public void whenFetchingTransmitterList_thenReturnTransmitterList() {
        List<Transmitter> expectedTransmitters = transmitterService.getTransmittersForAccount(9L);
        System.out.println(expectedTransmitters.size());
        assertThat(expectedTransmitters.size()).isEqualTo(transmitters.size());
    }

    private Transmitter createTransmitterWithId(Long id) {
        return Transmitter
                .builder()
                .id(id)
                .build();
    }
}