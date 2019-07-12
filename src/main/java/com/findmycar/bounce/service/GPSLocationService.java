package com.findmycar.bounce.service;

import com.findmycar.bounce.domain.GPSLocation;
import com.findmycar.bounce.repository.GPSLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GPSLocationService {
    private final GPSLocationRepository gpsLocationRepository;
    private static final int MAX_DISTANCE_BETWEEN = 20;

    @Autowired
    public GPSLocationService(GPSLocationRepository gpsLocationRepository) {
        this.gpsLocationRepository = gpsLocationRepository;
    }

    public GPSLocation addNewLocation(GPSLocation gpsLocation) {
        Optional<GPSLocation> lastGpsLocation = gpsLocationRepository.findTopByUserIdAndDeviceIdOrderByGpsTimestampDesc(gpsLocation.getUserId(), gpsLocation.getDeviceId());

        if (lastGpsLocation.isPresent()) {
            // Calculate distance between last saved location and new location
            double distanceBetween = distanceBetween(gpsLocation, lastGpsLocation.get());
            if (distanceBetween <= MAX_DISTANCE_BETWEEN) {

                // TODO - SAVE PREVIOUS LOCATION WITH UPDATED STATE (E.G., BATTERY LEVEL, IP ADDRESS AND GPS TIMESTAMP ETC)
                return gpsLocationRepository.save(gpsLocation);
            }
        }
//
        return gpsLocationRepository.save(gpsLocation);
    }

    /** TODO - MOVE TO UTILITY METHOD / INTERFACE
    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference.
     * Uses Haversine method as its base.
     *
     * @param gpsLocation1
     * @param gpsLocation2
     * @return Distance in Meters
     */
    private static double distanceBetween(GPSLocation gpsLocation1, GPSLocation gpsLocation2) {
        double latitude1 = gpsLocation1.getLatitude();
        double longitude1 = gpsLocation1.getLongitude();
        double latitude2 = gpsLocation2.getLatitude();
        double longitude2 = gpsLocation2.getLongitude();

        final int R = 6371; // Radius of the earth
//
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
