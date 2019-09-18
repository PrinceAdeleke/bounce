package com.findmycar.bounce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateLocationRequest {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("transmitter_id")
    private Long transmitterId;

    private List<Location> locations;

    @Data
    public static class Location {
        private Double latitude;
        private Double longitude;

        @JsonProperty("gps_timestamp")
        private LocalDateTime gpsTimestamp;
    }
}
