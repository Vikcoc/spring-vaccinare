package com.vaccin.vaccin.repository;

import com.vaccin.vaccin.dto.VaccineCenterDto;
import com.vaccin.vaccin.model.VaccineCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineCenterRepository extends JpaRepository<VaccineCenter, Long> {

}
