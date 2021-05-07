package com.vaccin.vaccin.model;

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

    @OneToMany(fetch =FetchType.LAZY)
    private List<VaccineShot> vaccineShots
            = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDaysBetweenShots() {
        return daysBetweenShots;
    }

    public void setDaysBetweenShots(int daysBetweenShots) {
        this.daysBetweenShots = daysBetweenShots;
    }
}
