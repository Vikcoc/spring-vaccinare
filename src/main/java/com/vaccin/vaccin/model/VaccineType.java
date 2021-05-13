package com.vaccin.vaccin.model;

import com.vaccin.vaccin.dto.VaccineTypeCreateDto;
import com.vaccin.vaccin.dto.VaccineTypeDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="vaccine_types")
public class VaccineType {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private int daysBetweenShots;

    @OneToMany(mappedBy = "vaccineType", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VaccineCenter> vaccineCenters;

    public VaccineType() { }

    public VaccineType(VaccineTypeCreateDto vaccineTypeCreateDto) {
        this.brand = vaccineTypeCreateDto.getBrand();
        this.daysBetweenShots = vaccineTypeCreateDto.getDaysBetweenShots();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<VaccineCenter> getVaccineCenters() {
        return vaccineCenters;
    }
}
