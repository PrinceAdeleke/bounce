package com.findmycar.bounce.controller;

import com.findmycar.bounce.domain.location.Location;
import com.findmycar.bounce.domain.response.APIResponse;
import com.findmycar.bounce.domain.response.SuccessResponse;
import com.findmycar.bounce.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.findmycar.bounce.values.APIConstants.API_V1_BASE_URL;

@RestController
@RequestMapping(API_V1_BASE_URL + "/location")
public class LocationController {
    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<APIResponse> addNewLocation(@RequestBody Location location) {
        Location newLocation = locationService.addNewLocation(location);

        return new ResponseEntity<>(
                new SuccessResponse("Successfully recorded GPS location", newLocation),
                HttpStatus.CREATED
        );
    }
}
