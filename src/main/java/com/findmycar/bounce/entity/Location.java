package com.findmycar.bounce.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Location {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @NotNull private Double latitude;
    @NotNull private Double longitude;

    @ManyToOne
    @JoinColumn(name = "transmitter_id", referencedColumnName = "id")
    private Transmitter transmitter;

    @JoinColumn(name = "account_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gpsTimestamp;

    @CreationTimestamp private LocalDateTime created;
}
