package com.findmycar.bounce.repository;

import com.findmycar.bounce.domain.GPSLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface GPSLocationRepository extends JpaRepository<GPSLocation, Long> {
    Optional<GPSLocation> findTopByUserIdAndDeviceIdOrderByGpsTimestampDesc(Long userId, String deviceId);
}
