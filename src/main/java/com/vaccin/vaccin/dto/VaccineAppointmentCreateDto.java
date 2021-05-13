package com.vaccin.vaccin.dto;

import java.sql.Date;
import java.sql.Time;

public class VaccineAppointmentCreateDto {

    private Long patientId;

    private String date;

    private String time;

    private Long vaccineCenterId;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

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
