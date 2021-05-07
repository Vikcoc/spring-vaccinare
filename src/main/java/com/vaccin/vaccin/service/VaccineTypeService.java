package com.vaccin.vaccin.service;

import com.vaccin.vaccin.dto.VaccineTypeDto;
import com.vaccin.vaccin.model.VaccineType;
import com.vaccin.vaccin.repository.VaccineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineTypeService {

    @Autowired
    private VaccineTypeRepository vaccineTypeRepository;

    public List<VaccineTypeDto> getAll() {
        List<VaccineType> vaccineTypes = vaccineTypeRepository.findAll();

        List<VaccineTypeDto> vaccineTypeDtoList = vaccineTypes
                .stream()
                .map(VaccineTypeDto::new)
                .collect(Collectors.toList());

        return vaccineTypeDtoList;
    }
}
