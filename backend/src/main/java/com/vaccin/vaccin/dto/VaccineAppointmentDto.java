package com.vaccin.vaccin.dto;

import com.vaccin.vaccin.model.TimeSlot;
import com.vaccin.vaccin.model.User;
import com.vaccin.vaccin.model.VaccineAppointment;
import com.vaccin.vaccin.model.VaccineCenter;

public class VaccineAppointmentDto {

    private String date;
    private String time;
    private String vaccineCenterName;
    private String vaccineCenterAddress;
    private String vaccineTypeBrand;
    private Boolean fulfilled;



    public VaccineAppointmentDto(VaccineAppointment vaccineAppointment) {

        TimeSlot timeSlot = vaccineAppointment.getTimeSlot();
        this.date = timeSlot.getDate().toString();
        this.time = timeSlot.getTime().toString();

        VaccineCenter vaccineCenter = timeSlot.getVaccineCenter();
        this.vaccineCenterName = vaccineCenter.getName();
        this.vaccineCenterAddress = vaccineCenter.getAddress();
        this.vaccineTypeBrand = vaccineCenter.getVaccineType().getBrand();

        this.fulfilled = vaccineAppointment.getFulfilled();
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

    public String getVaccineCenterName() {
        return vaccineCenterName;
    }

    public void setVaccineCenterName(String vaccineCenterName) {
        this.vaccineCenterName = vaccineCenterName;
    }

    public String getVaccineCenterAddress() {
        return vaccineCenterAddress;
    }

    public void setVaccineCenterAddress(String vaccineCenterAddress) {
        this.vaccineCenterAddress = vaccineCenterAddress;
    }

    public String getVaccineTypeBrand() {
        return vaccineTypeBrand;
    }

    public void setVaccineTypeBrand(String vaccineTypeBrand) {
        this.vaccineTypeBrand = vaccineTypeBrand;
    }

    public Boolean getFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(Boolean fulfilled) {
        this.fulfilled = fulfilled;
    }
}
