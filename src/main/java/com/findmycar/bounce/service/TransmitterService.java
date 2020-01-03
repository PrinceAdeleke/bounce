package com.findmycar.bounce.service;

import com.findmycar.bounce.entity.Account;
import com.findmycar.bounce.entity.Transmitter;
import com.findmycar.bounce.entity.Vehicle;
import com.findmycar.bounce.exception.MaxTransmittersForAccountExceeded;
import com.findmycar.bounce.repository.TransmitterRepository;
import com.findmycar.bounce.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.findmycar.bounce.values.APIConstants.MAX_TRANSMITTERS_ALLOWED;

@Service
public class TransmitterService {
    private AccountService accountService;
    private TransmitterRepository transmitterRepository;
    private VehicleRepository vehicleRepository;

    @Autowired
    public TransmitterService(TransmitterRepository transmitterRepository,
                              AccountService accountService,
                              VehicleRepository vehicleRepository) {
        this.transmitterRepository = transmitterRepository;
        this.accountService = accountService;
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Fetch single transmitter
     * @param accountId of the user
     * @param transmitterId of transmitter
     * @return transmitter
     */
    public Transmitter getTransmitter(Long accountId, Long transmitterId) {
        Account account = accountService.getAccountById(accountId);
        return transmitterRepository.getByAccountAndId(account, transmitterId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Cannot find transmitter with id %s", transmitterId)
                ));
    }

    /**
     * Get all transmitters for user
     * @param accountId of the user
     * @return transmitters belonging to a user
     */
    public List<Transmitter> getTransmittersForAccount(Long accountId) {
        Account account = accountService.getAccountById(accountId);
        return transmitterRepository.getAllByAccount(account);
    }

    /**
     * create transmitter for user
     * @param accountId of the user
     * @param transmitter details
     * @param vehicle details
     * @return new transmitter
     */
    public Transmitter createTransmitter(Long accountId, Transmitter transmitter, Vehicle vehicle) {
        Account account = accountService.getAccountById(accountId);
        List<Transmitter> transmitters = transmitterRepository.getAllByAccount(account);

        if (transmitters.size() == MAX_TRANSMITTERS_ALLOWED) {
            throw new MaxTransmittersForAccountExceeded(String.format(
                    "Cannot create a new transmitter. Transmitter limit (%s) exceeded",
                    MAX_TRANSMITTERS_ALLOWED
            ));
        }

        Vehicle newVehicle = vehicleRepository.save(vehicle);
        transmitter.setVehicle(newVehicle);
        transmitter.setAccount(account);

        return transmitterRepository.save(transmitter);
    }
}
