package com.vaccin.vaccin.dto;

import java.sql.Date;

public class UserCreateDto {
    private String email;
    private String name;
    //private Date birthDate;
    private int age;
    private String address;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

//    public Date getBirthDate() {
//        return birthDate;
//    }


    public int getAge() {
        return age;
    }


    public String getAddress() {
        return address;
    }
}
