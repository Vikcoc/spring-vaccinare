package com.vaccin.vaccin.model;


import com.vaccin.vaccin.dto.UserCreateDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //test
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String address;
    private String cnp;

    private String email;
    private String password;

    @OneToMany(mappedBy="patient", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<VaccineAppointment> appointments = new ArrayList<>();

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @ManyToOne(fetch=FetchType.LAZY)
    private Role role;

    public User() { }

    public User(UserCreateDto userCreateDto){
        this.address = userCreateDto.getAddress();
        this.age = userCreateDto.getAge();
        this.email = userCreateDto.getEmail();
        this.name = userCreateDto.getName();
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<VaccineAppointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<VaccineAppointment> appointments) {
        this.appointments = appointments;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
