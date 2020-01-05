package com.findmycar.bounce.controller;

import com.findmycar.bounce.controller.dto.TransmitterDetailsDTO;
import com.findmycar.bounce.controller.dto.TransmitterRequestDTO;
import com.findmycar.bounce.controller.dto.mapper.TransmitterMapper;
import com.findmycar.bounce.entity.Transmitter;
import com.findmycar.bounce.entity.Vehicle;
import com.findmycar.bounce.entity.response.APIResponse;
import com.findmycar.bounce.service.TransmitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/account/{accountId}/transmitter")
public class TransmitterController {
    private TransmitterService transmitterService;

    @Autowired
    public TransmitterController(TransmitterService transmitterService) {
        this.transmitterService = transmitterService;
    }

    @PostMapping
    public APIResponse createTransmitter(@RequestBody TransmitterRequestDTO transmitterRequest,
                                         @PathVariable("accountId") UUID accountId) {
        Transmitter transmitter = TransmitterMapper.toTransmitterFromTransmitterRequestDTO();
        Vehicle vehicle = TransmitterMapper.toVehicleFromTransmitterRequestDTO(transmitterRequest);
        Transmitter newTransmitter = transmitterService.createTransmitter(accountId, transmitter, vehicle);
        TransmitterDetailsDTO transmitterDetailsDTO = TransmitterMapper.toTransmitterDTO(newTransmitter);
        return new APIResponse(transmitterDetailsDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public APIResponse getAllTransmitters(@PathVariable("accountId") UUID accountId) {
        List<TransmitterDetailsDTO> transmitterDetailsDTOList =
                transmitterService.getTransmittersForAccount(accountId).stream()
                        .map(TransmitterMapper::toTransmitterDTO)
                        .collect(Collectors.toList());
        return new APIResponse(transmitterDetailsDTOList, HttpStatus.OK);
    }

    @GetMapping("/{transmitterId}")
    public APIResponse getTransmitter(@PathVariable("accountId") UUID accountId,
                                      @PathVariable("transmitterId") UUID transmitterId) {
        Transmitter transmitter = transmitterService.getTransmitter(accountId, transmitterId);
        TransmitterDetailsDTO transmitterDetailsDTO = TransmitterMapper.toTransmitterDTO(transmitter);
        return new APIResponse(transmitterDetailsDTO, HttpStatus.OK);
    }
}
