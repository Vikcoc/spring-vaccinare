package com.vaccin.vaccin.dto;

import com.vaccin.vaccin.model.Doctor;
import com.vaccin.vaccin.model.VaccineType;

public class VaccineCenterCreateDto {

    private String name;
    private String address;
    private Double longitude;
    private Double latitude;

    private Doctor doctor;

    private VaccineType vaccineType;

    private Integer dosesAvailable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public VaccineType getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }

    public Integer getDosesAvailable() {
        return dosesAvailable;
    }

    public void setDosesAvailable(Integer dosesAvailable) {
        this.dosesAvailable = dosesAvailable;
    }

}
