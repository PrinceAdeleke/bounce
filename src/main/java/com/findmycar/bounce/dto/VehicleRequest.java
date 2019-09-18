package com.findmycar.bounce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VehicleRequest {
    private String make;
    private String model;
    private Integer year;
    @JsonProperty("registration_number")
    private String registrationNumber;
}
