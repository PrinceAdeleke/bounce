package com.findmycar.bounce.repository;

import com.findmycar.bounce.entity.Transmitter;
import com.findmycar.bounce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransmitterRepository extends JpaRepository<Transmitter, Long> {
    List<Transmitter> findAllByUser(User user);
}
