package com.vaccin.vaccin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String role;

    @OneToMany(mappedBy="role", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    public Role() {
    }

    public Role(String role) {
        this.role=role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role=role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}