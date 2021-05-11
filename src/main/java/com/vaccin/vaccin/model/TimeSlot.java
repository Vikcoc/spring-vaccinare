package com.vaccin.vaccin.model;

import com.vaccin.vaccin.dto.TimeSlotCreateDto;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="time_slots")
public class TimeSlot {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    private VaccineCenter vaccineCenter;

    private Date date;

    private Time time;

    private Integer noOfAppointments;

    private Boolean full;

    @OneToMany(fetch=FetchType.LAZY)
    private List<VaccineAppointment> vaccineAppointments = new ArrayList<>();

    public TimeSlot() { }

    public TimeSlot(TimeSlotCreateDto timeSlotCreateDto) {
        this.date = timeSlotCreateDto.getDate();
        this.time = timeSlotCreateDto.getTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public VaccineCenter getVaccineCenter() {
        return vaccineCenter;
    }

    public void setVaccineCenter(VaccineCenter vaccineCenter) {
        this.vaccineCenter = vaccineCenter;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Integer getNoOfAppointments() {
        return noOfAppointments;
    }

    public void setNoOfAppointments(Integer noOfAppointments) {
        this.noOfAppointments = noOfAppointments;
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

    public void setVaccineAppointments(List<VaccineAppointment> vaccineAppointments) {
        this.vaccineAppointments = vaccineAppointments;
    }
}
