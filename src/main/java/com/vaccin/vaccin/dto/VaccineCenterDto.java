package com.vaccin.vaccin.dto;

import com.vaccin.vaccin.model.VaccineCenter;

public class VaccineCenterDto {

    private Long id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String vaccineTypeBrand;
    private long dosesAvailable;

    public VaccineCenterDto() { }

    public VaccineCenterDto(VaccineCenter vaccineCenter) {

        this.id = vaccineCenter.getId();
        this.name = vaccineCenter.getName();
        this.address = vaccineCenter.getAddress();
        this.latitude = vaccineCenter.getLatitude();
        this.longitude = vaccineCenter.getLongitude();
        this.vaccineTypeBrand = vaccineCenter.getVaccineType().getBrand();
        this.dosesAvailable = vaccineCenter.getDosesAvailable();
    }


    public Long getId() {
        return id;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getVaccineTypeBrand() {
        return vaccineTypeBrand;
    }

    public void setVaccineTypeBrand(String vaccineTypeBrand) {
        this.vaccineTypeBrand = vaccineTypeBrand;
    }

    public long getDosesAvailable() {
        return dosesAvailable;
    }

    public void setDosesAvailable(long dosesAvailable) {
        this.dosesAvailable = dosesAvailable;
    }
}
