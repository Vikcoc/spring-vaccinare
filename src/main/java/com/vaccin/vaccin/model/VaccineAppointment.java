package com.vaccin.vaccin.model;

import javax.persistence.*;


@Entity
@Table(name="appointments")
public class VaccineAppointment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Boolean fulfilled;

    @ManyToOne(fetch=FetchType.LAZY)
    private User patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(Boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }
}
