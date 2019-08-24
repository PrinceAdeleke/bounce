package com.findmycar.bounce.repository;

import com.findmycar.bounce.domain.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
//    Optional<Location> findTopByUserIdAndDeviceIdOrderByGpsTimestampDesc(Long userId, String deviceId);
}
