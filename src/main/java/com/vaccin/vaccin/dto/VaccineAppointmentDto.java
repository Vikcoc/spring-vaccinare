package com.vaccin.vaccin.dto;

import com.vaccin.vaccin.model.TimeSlot;
import com.vaccin.vaccin.model.User;
import com.vaccin.vaccin.model.VaccineAppointment;

public class VaccineAppointmentDto {

    private User patient;

    private TimeSlot timeSlot;

    private Boolean fullfiled;


    public VaccineAppointmentDto(VaccineAppointment vaccineAppointment) {
        this.timeSlot = vaccineAppointment.getTimeSlot();
        this.fullfiled = vaccineAppointment.getFulfilled();
    }


    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public Boolean getFullfiled() {
        return fullfiled;
    }

    public void setFullfiled(Boolean fullfiled) {
        this.fullfiled = fullfiled;
    }
}
