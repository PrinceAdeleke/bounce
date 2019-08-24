package com.findmycar.bounce.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.findmycar.bounce.domain.location.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@NoArgsConstructor @Getter @Setter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "transmitter_id", referencedColumnName = "id")
    private Transmitter transmitter;

    @NotNull
    @JsonIgnore
    private boolean enabled;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Location> locationList;
}
