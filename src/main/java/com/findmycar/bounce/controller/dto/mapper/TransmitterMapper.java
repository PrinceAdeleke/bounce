package com.findmycar.bounce.controller.dto.mapper;

import com.findmycar.bounce.controller.dto.TransmitterDetailsDTO;
import com.findmycar.bounce.controller.dto.TransmitterRequestDTO;
import com.findmycar.bounce.entity.Transmitter;
import com.findmycar.bounce.entity.Vehicle;

public class TransmitterMapper {
    public static TransmitterDetailsDTO toTransmitterDTO(Transmitter transmitter) {
        return TransmitterDetailsDTO.builder()
                .transmitterId(transmitter.getId())
                .vehicleId(transmitter.getVehicle().getId())
                .make(transmitter.getVehicle().getMake())
                .model(transmitter.getVehicle().getModel())
                .year(transmitter.getVehicle().getYear())
                .registrationNumber(transmitter.getVehicle().getRegistrationNumber())
                .build();
    }

    public static Vehicle toVehicleFromTransmitterRequestDTO(TransmitterRequestDTO transmitterRequestDTO) {
        return Vehicle.builder()
                .make(transmitterRequestDTO.getVehicle().getMake())
                .model(transmitterRequestDTO.getVehicle().getModel())
                .year(transmitterRequestDTO.getVehicle().getYear())
                .registrationNumber(transmitterRequestDTO.getVehicle().getRegistrationNumber())
                .build();
    }

    public static Transmitter toTransmitterFromTransmitterRequestDTO() {
        return Transmitter.builder()
                .build();
    }
}
