package com.vaccin.vaccin.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="time_slots")
public class TimeSlot {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch=FetchType.LAZY)
    private VaccineCenter vaccineCenter;

    private LocalDateTime startTime;

    private Boolean full;

    @OneToMany(fetch=FetchType.LAZY)
    private List<VaccineAppointment> vaccineAppointments = new ArrayList<>();

    public TimeSlot() { }

    // dto?

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public VaccineCenter getVaccineCenter() {
        return vaccineCenter;
    }

    public void setVaccineCenter(VaccineCenter vaccineCenter) {
        this.vaccineCenter = vaccineCenter;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Boolean getFull() {
        return full;
    }

    public void setFull(Boolean full) {
        this.full = full;
    }

    public List<VaccineAppointment> getVaccineAppointments() {
        return vaccineAppointments;
    }
}
