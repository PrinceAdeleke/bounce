package com.findmycar.bounce.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class TransmitterDetailsDTO {
    private UUID transmitterId;
    private UUID vehicleId;
    private String make;
    private String model;
    private Integer year;
    private String registrationNumber;
}
