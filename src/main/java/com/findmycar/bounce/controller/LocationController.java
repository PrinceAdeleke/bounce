package com.findmycar.bounce.controller;

import com.findmycar.bounce.controller.dto.LocationRequestDTO;
import com.findmycar.bounce.controller.dto.LocationResponseDTO;
import com.findmycar.bounce.controller.dto.mapper.LocationMapper;
import com.findmycar.bounce.entity.Location;
import com.findmycar.bounce.entity.response.APIResponse;
import com.findmycar.bounce.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/account/{accountId}/location")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public APIResponse newLocation(@PathVariable("accountId") Long accountId,
                                    @RequestBody LocationRequestDTO locationRequest) {
        Location newLocation = locationService.newLocation(
                accountId,
                locationRequest.getTransmitterId(),
                LocationMapper.toLocation(locationRequest)
        );

        LocationResponseDTO locationResponse = LocationMapper.toLocationResponseDTO(newLocation);
        return new APIResponse(locationResponse, "Successfully created new location", HttpStatus.OK);
    }

    @GetMapping
    public APIResponse getLastTransmitterLocations(@PathVariable("accountId") Long accountId) {
        List<Location> locations = locationService.getLastLocationsByAccountId(accountId);

        List<LocationResponseDTO> locationResponseDTOList = locations.stream()
                .map(LocationMapper::toLocationResponseDTO)
                .collect(Collectors.toList());

        return new APIResponse(locationResponseDTOList, HttpStatus.OK);
    }
}
