package com.findmycar.bounce.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor @Getter @Setter
public class Transmitter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "transmitter")
    @NotNull
    private User user;

    // TODO - Needs better implementation - Perhaps a class named TransmitterMetadata would be more suited for this type of data
    private Long batteryLevel;

    @NotNull private String model;
    @CreationTimestamp private LocalDateTime created;
}
