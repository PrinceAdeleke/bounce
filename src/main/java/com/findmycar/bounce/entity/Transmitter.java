package com.findmycar.bounce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Transmitter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @NotNull
    @JsonBackReference
    private Account account;

    @OneToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    @NotNull
    private Vehicle vehicle;

    @CreationTimestamp private LocalDateTime created;
}
