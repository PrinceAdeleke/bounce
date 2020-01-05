package com.findmycar.bounce.service;

import com.findmycar.bounce.entity.Account;
import com.findmycar.bounce.entity.Location;
import com.findmycar.bounce.entity.Transmitter;
import com.findmycar.bounce.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final TransmitterService transmitterService;
    private final AccountService accountService;

    @Autowired
    public LocationService(LocationRepository locationRepository,
                           TransmitterService transmitterService,
                           AccountService accountService) {
        this.locationRepository = locationRepository;
        this.transmitterService = transmitterService;
        this.accountService = accountService;
    }

    /**
     * Create new gps location
     * @param accountId of user
     * @param transmitterId of transmitting device
     * @param location object
     * @return new gps location
     */
    public Location newLocation(UUID accountId, UUID transmitterId, Location location) {
        Account account = accountService.getAccountById(accountId);
        Transmitter transmitter = transmitterService.getTransmitter(accountId, transmitterId);
        Location newLocation = Location.builder()
                .transmitter(transmitter)
                .account(account)
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .gpsTimestamp(location.getGpsTimestamp())
                .build();

        return locationRepository.save(newLocation);
    }

    public List<Location> getLastLocationsByAccountId(Long accountId) {
        return locationRepository.getLastLocations(accountId);
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
