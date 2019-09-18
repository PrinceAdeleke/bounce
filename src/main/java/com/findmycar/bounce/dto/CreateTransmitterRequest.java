package com.findmycar.bounce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateTransmitterRequest {
    @JsonProperty("user_id")
    private Long userId;
    private VehicleRequest vehicle;
}
