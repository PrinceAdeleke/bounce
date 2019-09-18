package com.findmycar.bounce.service;

import com.findmycar.bounce.dto.CreateLocationRequest;
import com.findmycar.bounce.entity.Location;
import com.findmycar.bounce.entity.Transmitter;
import com.findmycar.bounce.entity.User;
import com.findmycar.bounce.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final TransmitterService transmitterService;

    @Autowired
    public LocationService(LocationRepository locationRepository, TransmitterService transmitterService) {
        this.locationRepository = locationRepository;
        this.transmitterService = transmitterService;
    }

    /**
     * @param user id of the user transmitting the location(s)
     * @param transmitterId id of the transmitter
     * @param locations location request objects
     * @return list of saved gps locations
     */
    public List<Location> addNewLocation(User user, Long transmitterId, List<CreateLocationRequest.Location> locations) {
        Transmitter transmitter = transmitterService.findTransmitterById(transmitterId);

        List<Location> newLocations = locations.stream()
                .map(location -> Location.builder()
                        .transmitter(transmitter)
                        .user(user)
                        .longitude(location.getLongitude())
                        .latitude(location.getLatitude())
                        .gpsTimestamp(location.getGpsTimestamp())
                        .created(LocalDateTime.now())
                        .build()
                ).collect(Collectors.toList());

        return locationRepository.saveAll(newLocations);
    }

    /** TODO - MOVE TO UTILITY METHOD / INTERFACE
    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference.
     * Uses Haversine method as its base.
     *
     * @param location1
     * @param location2
     * @return Distance in Meters
     */
    private static double distanceBetween(Location location1, Location location2) {
        double latitude1 = location1.getLatitude();
        double longitude1 = location1.getLongitude();
        double latitude2 = location2.getLatitude();
        double longitude2 = location2.getLongitude();

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(latitude2 - latitude1);
        double lonDistance = Math.toRadians(longitude2 - longitude1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }
}
