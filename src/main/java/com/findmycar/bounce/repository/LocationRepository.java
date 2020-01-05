package com.findmycar.bounce.repository;

import com.findmycar.bounce.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
    @Query("SELECT l FROM Location l WHERE l.account.id = ?1 AND l.gpsTimestamp IN (SELECT MAX(o.gpsTimestamp) FROM Location o GROUP BY o.transmitter.id)")
    List<Location> getLastLocations(Long accountId);
}
