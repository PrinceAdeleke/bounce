package com.findmycar.bounce.service;

import com.findmycar.bounce.dto.VehicleRequest;
import com.findmycar.bounce.entity.Transmitter;
import com.findmycar.bounce.entity.User;
import com.findmycar.bounce.entity.Vehicle;
import com.findmycar.bounce.exception.BadRequestException;
import com.findmycar.bounce.repository.TransmitterRepository;
import com.findmycar.bounce.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.findmycar.bounce.values.APIConstants.MAX_TRANSMITTERS_ALLOWED;

@Service
public class TransmitterService {
    private UserService userService;
    private TransmitterRepository transmitterRepository;
    private VehicleRepository vehicleRepository;

    @Autowired
    public TransmitterService(
            TransmitterRepository transmitterRepository,
            UserService userService,
            VehicleRepository vehicleRepository
    ) {
        this.transmitterRepository = transmitterRepository;
        this.userService = userService;
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Find transmitter by id
     * @param id Transmitter id
     * @return Transmitter object
     * @throws EntityNotFoundException if a transmitter has not been found
     */
    public Transmitter findTransmitterById(Long id) {
        return transmitterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Cannot find transmitter with id %s", id)));
    }

    public List<Transmitter> getTransmittersForUser(Long userId) {
        User user = userService.findUserById(userId);
        List<Transmitter> transmitters = transmitterRepository.findAllByUser(user);
        return transmitters;
    }

    public Transmitter createTransmitter(User user, VehicleRequest vehicleRequest) {
        List<Transmitter> transmitters = transmitterRepository.findAllByUser(user);

        if (transmitters.size() == MAX_TRANSMITTERS_ALLOWED) {
            throw new BadRequestException(String.format(
                    "Cannot create a new transmitter. Transmitter limit (%s) exceeded",
                    MAX_TRANSMITTERS_ALLOWED
            ));
        }

        Vehicle vehicle = Vehicle.builder()
                .make(vehicleRequest.getMake())
                .model(vehicleRequest.getModel())
                .year(vehicleRequest.getYear())
                .registrationNumber(vehicleRequest.getRegistrationNumber())
                .build();
        Vehicle newVehicle = vehicleRepository.save(vehicle);

        Transmitter transmitter = Transmitter.builder()
                .user(user)
                .vehicle(newVehicle)
                .build();
        transmitterRepository.save(transmitter);

        return transmitter;
    }

    public boolean deleteTransmitter() {
        return false;
    }
}
