package com.findmycar.bounce.repository;

import com.findmycar.bounce.entity.Account;
import com.findmycar.bounce.entity.Transmitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransmitterRepository extends JpaRepository<Transmitter, UUID> {
    List<Transmitter> getAllByAccount(Account account);
    Optional<Transmitter> getByAccountAndId(Account account, UUID transmitterId);
}
