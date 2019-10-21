package com.findmycar.bounce.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LocationResponseDTO {
    private Long accountId;
    private Long transmitterId;
    private Double latitude;
    private Double longitude;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gpsTimestamp;
}
