package com.vaccin.vaccin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="centers")
public class VaccineCenter {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private Double longitude;
    private Double latitude;

    @OneToOne(fetch=FetchType.LAZY)
    private Doctor doctor;

    @OneToMany(mappedBy = "id", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<VaccineShot> vaccineShots =
            new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<TimeSlot> timeSlots =
            new ArrayList<>();
}
