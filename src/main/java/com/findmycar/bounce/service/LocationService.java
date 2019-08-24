package com.findmycar.bounce.service;

import com.findmycar.bounce.domain.location.Location;
import com.findmycar.bounce.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
//
@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private static final int MAX_DISTANCE_BETWEEN = 20;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    public Location addNewLocation(Location location) {

        System.out.println(location);
//        Optional<Location> lastGpsLocation = new L
//
//        if (lastGpsLocation.isPresent()) {
//            // Calculate distance between last saved location and new location
//            double distanceBetween = distanceBetween(location, lastGpsLocation.get());
//            if (distanceBetween <= MAX_DISTANCE_BETWEEN) {
//
//                // TODO - SAVE PREVIOUS LOCATION WITH UPDATED STATE (E.G., BATTERY LEVEL, IP ADDRESS AND GPS TIMESTAMP ETC)
//                return locationRepository.save(location);
//            }
//        }

        return locationRepository.save(location);
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
