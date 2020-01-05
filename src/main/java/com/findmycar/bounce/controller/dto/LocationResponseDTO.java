package com.findmycar.bounce.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class LocationResponseDTO {
    private UUID accountId;
    private UUID transmitterId;
    private Double latitude;
    private Double longitude;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gpsTimestamp;
}
