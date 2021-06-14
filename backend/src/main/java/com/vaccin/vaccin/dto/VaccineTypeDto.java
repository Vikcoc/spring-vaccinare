package com.vaccin.vaccin.dto;

import com.vaccin.vaccin.model.VaccineType;

public class VaccineTypeDto {

    private String brand;

    private int daysBetweenShots;

    public VaccineTypeDto() { }

    public VaccineTypeDto(VaccineType vaccineType) {
        this.brand = vaccineType.getBrand();
        this.daysBetweenShots = vaccineType.getDaysBetweenShots();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDaysBetweenShots() {
        return daysBetweenShots;
    }

    public void setDaysBetweenShots(int daysBetweenShots) {
        this.daysBetweenShots = daysBetweenShots;
    }
}
