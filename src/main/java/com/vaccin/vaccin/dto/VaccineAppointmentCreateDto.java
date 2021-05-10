package com.vaccin.vaccin.dto;

import com.vaccin.vaccin.model.TimeSlot;
import com.vaccin.vaccin.model.User;

import java.sql.Date;
import java.sql.Time;

public class VaccineAppointmentCreateDto {

    //private Long timeSlotId;

    private Long patientId;

    private String date;
    //private Date date;

    private String time;
    //private Time time;

    private Long vaccineCenterId;


    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public Time getTime() {
//        return time;
//    }
//
//    public void setTime(Time time) {
//        this.time = time;
//    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getVaccineCenterId() {
        return vaccineCenterId;
    }

    public void setVaccineCenterId(Long vaccineCenterId) {
        this.vaccineCenterId = vaccineCenterId;
    }
}
