package com.findmycar.bounce.controller;

import com.findmycar.bounce.dto.CreateLocationRequest;
import com.findmycar.bounce.entity.Location;
import com.findmycar.bounce.entity.response.APIResponse;
import com.findmycar.bounce.exception.BadRequestException;
import com.findmycar.bounce.service.LocationService;
import com.findmycar.bounce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.findmycar.bounce.values.APIConstants.API_BASE_URL;

@RestController
@RequestMapping(API_BASE_URL + "/location")
public class LocationController {
    private final LocationService locationService;
    private final UserService userService;

    @Autowired
    public LocationController(LocationService locationService, UserService userService) {
        this.locationService = locationService;
        this.userService = userService;
    }

    @PostMapping
    public APIResponse addLocations(@RequestBody CreateLocationRequest request) {
        if (request.getLocations().size() == 0) {
            throw new BadRequestException("0 location objects sent in request");
        }

        List<Location> newLocations = locationService.addNewLocation(
                userService.findUserById(request.getUserId()),
                request.getTransmitterId(),
                request.getLocations()
        );

        return new APIResponse(String.format("Successfully saved %s locations", newLocations.size()), HttpStatus.OK);
    }

    @GetMapping("/last/all")
    public APIResponse getLastLocation() {
        return null;
    }
}
