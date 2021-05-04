package com.vaccin.vaccin.model;

import javax.persistence.*;


@Entity
@Table(name="appointments")
public class VaccineAppointment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Boolean fuffiled;

    @ManyToOne(fetch=FetchType.LAZY)
    private User patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFuffiled() {
        return fuffiled;
    }

    public void setFuffiled(Boolean fuffiled) {
        this.fuffiled = fuffiled;
    }

//    public com.vaccin.vaccin.model.User getUser() {
//        return User;
//    }
//
//    public void setUser(com.vaccin.vaccin.model.User user) {
//        User = user;
//    }
}
