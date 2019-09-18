package com.findmycar.bounce.controller;

import com.findmycar.bounce.entity.response.APIResponse;
import com.findmycar.bounce.entity.Transmitter;
import com.findmycar.bounce.dto.CreateTransmitterRequest;
import com.findmycar.bounce.entity.User;
import com.findmycar.bounce.service.TransmitterService;
import com.findmycar.bounce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.findmycar.bounce.values.APIConstants.API_BASE_URL;

@RestController
@RequestMapping(API_BASE_URL + "/transmitter")
public class TransmitterController {
    private TransmitterService transmitterService;
    private UserService userService;

    @Autowired
    public TransmitterController(TransmitterService transmitterService, UserService userService) {
        this.transmitterService = transmitterService;
        this.userService = userService;
    }

    @PostMapping
    public APIResponse createTransmitter(@RequestBody CreateTransmitterRequest request) {
        User user = userService.findUserById(request.getUserId());
        Transmitter transmitter = transmitterService.createTransmitter(
                user,
                request.getVehicle()
        );

        return new APIResponse(
                String.format("Successfully created transmitter with id %d", transmitter.getId()),
                HttpStatus.OK
        );
    }

    @GetMapping("/users/{id}")
    public APIResponse getAllTransmitters(@PathVariable Long id) {
        List<Transmitter> transmitters = transmitterService.getTransmittersForUser(id);
        return new APIResponse(transmitters, HttpStatus.OK);
    }
}
