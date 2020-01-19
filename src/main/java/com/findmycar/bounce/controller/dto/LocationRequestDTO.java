package com.findmycar.bounce.controller.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class LocationRequestDTO {
    private UUID transmitterId;
    private Double latitude;
    private Double longitude;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime gpsTimestamp;
}
