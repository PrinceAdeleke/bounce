package com.findmycar.bounce.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "gps_location")
@Getter @Setter @NoArgsConstructor
public class GPSLocation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull private Long userId;
    @NotNull private Double latitude;
    @NotNull private Double longitude;
    @NotNull private String deviceId;

    private String model;
    private String ipAddress;

    @NotNull private Integer batteryLevel;
    @NotBlank private String gpsTimestamp;
    @CreationTimestamp private LocalDateTime created;

    @Override
    public String toString() {
        return "GPSLocation{" +
                "id=" + id +
                ", userId=" + userId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", deviceId='" + deviceId + '\'' +
                ", model='" + model + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", batteryLevel=" + batteryLevel +
                ", gpsTimestamp='" + gpsTimestamp + '\'' +
                ", created=" + created +
                '}';
    }
}
