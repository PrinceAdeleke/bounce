package com.findmycar.bounce.domain.location;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.findmycar.bounce.domain.user.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull private Double latitude;
    @NotNull private Double longitude;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    @Valid
    private User user;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gpsTimestamp;

    @CreationTimestamp private LocalDateTime created;
}
