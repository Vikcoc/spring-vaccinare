package com.vaccin.vaccin.dto;


import com.vaccin.vaccin.model.TimeSlot;

import java.sql.Date;
import java.sql.Time;

public class TimeSlotDto {

    private Date date;
    private Time time;
    private String vaccineCenterName;

    public TimeSlotDto(TimeSlot timeSlot) {
        this.date = timeSlot.getDate();
        this.time = timeSlot.getTime();
        this.vaccineCenterName = timeSlot.getVaccineCenter().getName();
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

    public String getVaccineCenterName() {
        return vaccineCenterName;
    }

    public void setVaccineCenterName(String vaccineCenterName) {
        this.vaccineCenterName = vaccineCenterName;
    }
}