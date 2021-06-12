package com.vaccin.vaccin.model;

import com.vaccin.vaccin.dto.VaccineAppointmentCreateDto;
import com.vaccin.vaccin.dto.VaccineAppointmentDto;

import javax.persistence.*;

@Entity
@Table(name="vaccine_appointments")
public class VaccineAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    private User patient;

    @ManyToOne(fetch=FetchType.LAZY)
    private Doctor doctor;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "time_slot_id")
    private TimeSlot timeSlot;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "initial_appointment_id")
    private VaccineAppointment initialAppointment;

    @Column(columnDefinition = "boolean default false")
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

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public VaccineAppointment getInitialAppointment() {
        return initialAppointment;
    }

    public void setInitialAppointment(VaccineAppointment initialAppointment) {
        this.initialAppointment = initialAppointment;
    }

    public Boolean getFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(Boolean fulfilled) {
        this.fulfilled = fulfilled;
    }
}
