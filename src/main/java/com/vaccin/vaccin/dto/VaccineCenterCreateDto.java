package com.vaccin.vaccin.dto;

import com.vaccin.vaccin.model.Doctor;
import com.vaccin.vaccin.model.VaccineType;

public class VaccineCenterCreateDto {

    private String name;
    private String address;
    private double latitude;
    private double longitude;

    private long vaccineTypeId;

    private long dosesAvailable;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getVaccineTypeId() {
        return vaccineTypeId;
    }

    public long getDosesAvailable() {
        return dosesAvailable;
    }
    
}
