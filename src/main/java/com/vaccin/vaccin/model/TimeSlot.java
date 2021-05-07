package com.vaccin.vaccin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="time_slots")
public class TimeSlot {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(columnDefinition = "vaccine_center")
    private VaccineCenter vaccineCenter;

    private String startTime;

    @OneToMany(fetch=FetchType.LAZY)
    private List<VaccineAppointment> vaccineAppointments
            = new ArrayList<>();
}
