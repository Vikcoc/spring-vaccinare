package com.vaccin.vaccin.model;

import javax.persistence.*;
import java.sql.Time;


@Entity
@Table(name="appointments")
public class VaccineAppointment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    private User patient;

    @ManyToOne(fetch=FetchType.LAZY)
    private Doctor doctor;

    @ManyToOne(fetch=FetchType.LAZY)
    private TimeSlot timeSlot;

    private Boolean fulfilled;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(Boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
