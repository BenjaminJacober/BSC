package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Place {

    @Id
    @GeneratedValue
    private Long id;

    private Date timeArrived;
    private Date timeLeft;
    // Could me markdown at some point
    private String description;

    // todo: use decimal, maybe?
    private Float latitude;
    private Float longitude;

    @OneToOne
    private Place from;
    @OneToOne
    private TransportationMode arrivedBy;
    @OneToMany
    private Collection<Image> images;

    // coordinates / place name

}
