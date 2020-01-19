package com.findmycar.bounce.controller.dto.mapper;

import com.findmycar.bounce.controller.dto.LocationRequestDTO;
import com.findmycar.bounce.controller.dto.LocationResponseDTO;
import com.findmycar.bounce.entity.Location;

public class LocationMapper {
    public static Location toLocation(LocationRequestDTO locationRequestDTO) {
        return Location.builder()
                .latitude(locationRequestDTO.getLatitude())
                .longitude(locationRequestDTO.getLongitude())
                .gpsTimestamp(locationRequestDTO.getGpsTimestamp())
                .build();
    }

    public static LocationResponseDTO toLocationResponseDTO(Location location) {
        return LocationResponseDTO.builder()
                .id(location.getId())
                .accountId(location.getAccount().getId())
                .transmitterId(location.getTransmitter().getId())
                .gpsTimestamp(location.getGpsTimestamp())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }
}
