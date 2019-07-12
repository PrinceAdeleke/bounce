package com.findmycar.bounce.controller;

import com.findmycar.bounce.domain.GPSLocation;
import com.findmycar.bounce.domain.response.APIResponse;
import com.findmycar.bounce.domain.response.SuccessResponse;
import com.findmycar.bounce.service.GPSLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.findmycar.bounce.values.APIConstants.API_V1_BASE_URL;

@RestController(API_V1_BASE_URL + "location")
public class GPSLocationController {
    private GPSLocationService gpsLocationService;

    @Autowired
    public GPSLocationController(GPSLocationService gpsLocationService) {
        this.gpsLocationService = gpsLocationService;
    }

    @PostMapping
    public ResponseEntity<APIResponse> addNewLocation(@RequestBody GPSLocation gpsLocation) {
        GPSLocation newGpsLocation = gpsLocationService.addNewLocation(gpsLocation);
        return new ResponseEntity<>(new SuccessResponse("Successfully recorded GPS location", newGpsLocation), HttpStatus.OK);
    }
}
