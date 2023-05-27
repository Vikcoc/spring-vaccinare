package com.vaccin.vaccin.model;

import com.vaccin.vaccin.dto.VaccineCenterCreateDto;
import com.vaccin.vaccin.dto.VaccineCenterDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="vaccine_centers")
public class VaccineCenter {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private Long dosesAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    private VaccineType vaccineType;

    @OneToMany(mappedBy = "vaccineCenter", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<TimeSlot> timeSlots = new ArrayList<>();

    public VaccineCenter() { }

    public VaccineCenter(VaccineCenterCreateDto vaccineCenterCreateDto) {

        this.name = vaccineCenterCreateDto.getName();
        this.address = vaccineCenterCreateDto.getAddress();
        this.latitude = vaccineCenterCreateDto.getLatitude();
        this.longitude = vaccineCenterCreateDto.getLongitude();
        this.dosesAvailable = vaccineCenterCreateDto.getDosesAvailable();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getDosesAvailable() {
        return dosesAvailable;
    }

    public void setDosesAvailable(Long dosesAvailable) {
        this.dosesAvailable = dosesAvailable;
    }

    public VaccineType getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }
}
