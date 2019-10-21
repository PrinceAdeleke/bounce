package com.findmycar.bounce.controller.dto;

import lombok.Data;

@Data
public class VehicleRequestDTO {
    private String make;
    private String model;
    private Integer year;
    private String registrationNumber;
}
