package com.vaccin.vaccin.model;

import com.vaccin.vaccin.dto.VaccineAppointmentCreateDto;
import com.vaccin.vaccin.dto.VaccineAppointmentDto;

import javax.persistence.*;

@Entity
@Table(name="appointments")
public class VaccineAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    private User patient;

//    @ManyToOne(fetch=FetchType.LAZY)
//    private Doctor doctor;

    @ManyToOne(fetch=FetchType.LAZY)
    private TimeSlot timeSlot;

    private Boolean fulfilled;

    public VaccineAppointment() { }

    public VaccineAppointment(VaccineAppointmentCreateDto vaccineAppointmentCreateDto) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

//    public Doctor getDoctor() {
//        return doctor;
//    }
//
//    public void setDoctor(Doctor doctor) {
//        this.doctor = doctor;
//    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Boolean getFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(Boolean fulfilled) {
        this.fulfilled = fulfilled;
    }
}
