package com.vaccin.vaccin.service;

import com.vaccin.vaccin.dto.VaccineCenterCreateDto;
import com.vaccin.vaccin.dto.VaccineCenterDto;
import com.vaccin.vaccin.model.VaccineCenter;
import com.vaccin.vaccin.repository.VaccineCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineCenterService {

    @Autowired
    private VaccineCenterRepository vaccineCenterRepository;

    public VaccineCenterDto getCentersAroundCoords(Double latitude, Double longitude) {
        List<VaccineCenter> vaccineCenters = vaccineCenterRepository.findAll();

        Optional<VaccineCenter> vaccineCenter = vaccineCenters
                .stream()
                .filter(vc -> Math.abs(vc.getLongitude() - longitude) < 0.1
                && Math.abs(vc.getLatitude() - latitude) < 0.2)
                .findFirst();

        return vaccineCenter.map(VaccineCenterDto::new).orElse(null);
    }

    public String addCenter(VaccineCenterCreateDto vaccineCenterCreateDto) {
        VaccineCenter vaccineCenter = new VaccineCenter(vaccineCenterCreateDto);

        vaccineCenterRepository.save(vaccineCenter);

        return "Vaccine Center added";
    }


}
