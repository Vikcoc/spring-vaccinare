package com.vaccin.vaccin.dto;

import com.vaccin.vaccin.model.Doctor;
import com.vaccin.vaccin.model.VaccineCenter;
import com.vaccin.vaccin.model.VaccineType;

public class VaccineCenterDto {

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;

    private String vaccineTypeBrand;

    // sau:
    // private VaccineType vaccineType;

    public VaccineCenterDto() { }

    public VaccineCenterDto(VaccineCenter vaccineCenter) {

        this.name = vaccineCenter.getName();
        this.address = vaccineCenter.getAddress();
        this.latitude = vaccineCenter.getLatitude();
        this.longitude = vaccineCenter.getLongitude();
        this.vaccineTypeBrand = vaccineCenter.getVaccineType().getBrand();
    }

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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }



    public String getVaccineTypeBrand() {
        return vaccineTypeBrand;
    }

    public void setVaccineTypeBrand(String vaccineTypeBrand) {
        this.vaccineTypeBrand = vaccineTypeBrand;
    }
}
