package com.vaccin.vaccin.repository;

import com.vaccin.vaccin.model.VaccineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VaccineTypeRepository extends JpaRepository<VaccineType, Long> {
}
