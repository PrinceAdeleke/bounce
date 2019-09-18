package com.findmycar.bounce.repository;

import com.findmycar.bounce.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
//    Optional<Location> findTopByUserIdAndDeviceIdOrderByGpsTimestampDesc(Long userId, String deviceId);

//    List<Location> test(Long userId);
}
