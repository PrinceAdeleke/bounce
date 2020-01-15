package com.findmycar.bounce.repository;

import com.findmycar.bounce.entity.Account;
import com.findmycar.bounce.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
    @Query("SELECT a FROM Location a WHERE a.account = :account AND CONCAT(a.id, '') IN (SELECT MAX(CONCAT(b.id, '')) FROM Location b GROUP BY b.transmitter.id)")
    List<Location> getLastLocationsByAccount(@Param("account") Account account);
}
