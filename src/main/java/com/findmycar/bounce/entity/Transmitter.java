package com.findmycar.bounce.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Transmitter {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @NotNull
    private Account account;

    @OneToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    @NotNull
    private Vehicle vehicle;

    @CreationTimestamp private LocalDateTime created;
}
