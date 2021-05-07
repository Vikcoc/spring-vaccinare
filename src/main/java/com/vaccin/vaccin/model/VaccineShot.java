package com.vaccin.vaccin.model;

import javax.persistence.*;

@Entity
@Table(name="vaccine_shots")
public class VaccineShot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    private VaccineType vaccineTypeId;

    @ManyToOne(fetch=FetchType.LAZY)
    private VaccineCenter vaccineCenterId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VaccineType getVaccineTypeId() {
        return vaccineTypeId;
    }

    public void setVaccineTypeId(VaccineType vaccineTypeId) {
        this.vaccineTypeId = vaccineTypeId;
    }

    public VaccineCenter getVaccineCenterId() {
        return vaccineCenterId;
    }

    public void setVaccineCenterId(VaccineCenter vaccineCenterId) {
        this.vaccineCenterId = vaccineCenterId;
    }
}
