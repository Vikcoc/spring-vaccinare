package com.vaccin.vaccin.dto;

import com.vaccin.vaccin.model.User;

import java.sql.Date;

public class UserDto {
    private Long id;

    private String email;
    private String name;
    private int age;
    //private Date birthDate;
    private String address;
    private String password;


    public UserDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.age = user.getAge();
        //this.birthDate = user.getBirthDate();
        this.address = user.getAddress();
        this.password = user.getPassword();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }
}
