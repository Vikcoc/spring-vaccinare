package com.vaccin.vaccin.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Time;
import java.sql.Date;

public class TimeSlotCreateDto {

    private Date date;
    private Time time;
    private Long vaccineCenterId;


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

    public Long getVaccineCenterId() {
        return vaccineCenterId;
    }

    public void setVaccineCenterId(Long vaccineCenterId) {
        this.vaccineCenterId = vaccineCenterId;
    }
}
