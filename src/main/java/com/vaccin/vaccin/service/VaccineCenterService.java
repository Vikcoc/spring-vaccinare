package com.vaccin.vaccin.service;

import com.vaccin.vaccin.dto.VaccineCenterCreateDto;
import com.vaccin.vaccin.dto.VaccineCenterDto;
import com.vaccin.vaccin.model.VaccineCenter;
import com.vaccin.vaccin.model.VaccineType;
import com.vaccin.vaccin.repository.VaccineCenterRepository;
import com.vaccin.vaccin.repository.VaccineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VaccineCenterService {

    @Autowired
    private VaccineCenterRepository vaccineCenterRepository;
    @Autowired
    private VaccineTypeRepository vaccineTypeRepository;

    public List<VaccineCenterDto> getCentersAroundCoords(Double latitude, Double longitude) {
        List<VaccineCenter> vaccineCenters = vaccineCenterRepository.findAll();

        //todo : toate nu unul singur
        List<VaccineCenterDto> vaccineCenterDtoList = vaccineCenters
                .stream()
                .filter(vc -> Math.abs(vc.getLongitude() - longitude) < 0.1
                && Math.abs(vc.getLatitude() - latitude) < 0.2)
                .map(VaccineCenterDto::new)
                .collect(Collectors.toList());

        return vaccineCenterDtoList;
    }

    public String addCenter(VaccineCenterCreateDto vaccineCenterCreateDto) {
        // Creez un VaccineCenter, dar ii lipsesc: VaccineType si Doctor
        VaccineCenter vaccineCenter = new VaccineCenter(vaccineCenterCreateDto);

        // iau id-ul VaccineType-ului din DTO
        Long vaccineTypeId = vaccineCenterCreateDto.getVaccineTypeId();

        // caut VaccineType-ul cu acel id
        Optional<VaccineType> vaccineTypeOptional = vaccineTypeRepository.findById(vaccineTypeId);

        // il salvez daca l-am gasit
        if (vaccineTypeOptional.isPresent()) {
            vaccineCenter.setVaccineType(vaccineTypeOptional.get());
            vaccineCenterRepository.save(vaccineCenter);
            return "Vaccine Center added";
        } else {
            return "Vaccine Type not found";
        }


    }


}
