package com.vaccin.vaccin.service;

import com.vaccin.vaccin.dto.VaccineTypeCreateDto;
import com.vaccin.vaccin.dto.VaccineTypeDto;
import com.vaccin.vaccin.model.VaccineCenter;
import com.vaccin.vaccin.model.VaccineType;
import com.vaccin.vaccin.repository.VaccineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineTypeService {

    private VaccineTypeRepository vaccineTypeRepository;

    @Autowired
    public VaccineTypeService(VaccineTypeRepository vaccineTypeRepository) {
        this.vaccineTypeRepository = vaccineTypeRepository;
    }

    public List<VaccineTypeDto> getAll() {
        List<VaccineType> vaccineTypes = vaccineTypeRepository.findAll();

        List<VaccineTypeDto> vaccineTypeDtoList = vaccineTypes
                .stream()
                .map(VaccineTypeDto::new)
                .collect(Collectors.toList());

        return vaccineTypeDtoList;
    }

    public VaccineTypeDto addType(VaccineTypeCreateDto vaccineTypeCreateDto) {
        VaccineType vaccineType = new VaccineType(vaccineTypeCreateDto);

        return new VaccineTypeDto(vaccineTypeRepository.save(vaccineType));
    }
}
