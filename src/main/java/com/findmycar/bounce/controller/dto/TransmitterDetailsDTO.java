package com.findmycar.bounce.controller.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TransmitterDetailsDTO {
    private Long transmitterId;
    private Long vehicleId;
    private String make;
    private String model;
    private Integer year;
    private String registrationNumber;
}
