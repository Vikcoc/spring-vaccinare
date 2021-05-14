package com.vaccin.vaccin.dto;

import java.sql.Date;

public class UserCreateDto {
    private String email;
    private String password;
    private String name;
    private String birthDate;
    private String address;
    private String cnp;

    public String getEmail() {
        return email;
    }

    public String getPassword() { return password; }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public String getCnp() {
        return cnp;
    }

}
