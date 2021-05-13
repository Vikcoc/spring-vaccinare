package com.vaccin.vaccin.controller;

import com.vaccin.vaccin.dto.VaccineTypeCreateDto;
import com.vaccin.vaccin.dto.VaccineTypeDto;
import com.vaccin.vaccin.service.VaccineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VaccineTypeController {

    VaccineTypeService vaccineTypeService;

    @Autowired
    public VaccineTypeController(VaccineTypeService vaccineTypeService) {
        this.vaccineTypeService = vaccineTypeService;
    }

    @PostMapping("/vaccines/add")
    public String addVaccineType(@RequestBody VaccineTypeCreateDto vaccineTypeCreateDto) {

        return vaccineTypeService.addType(vaccineTypeCreateDto);
    }


    @GetMapping("/vaccines")
    public List<VaccineTypeDto> getAllVaccineTypes() {

        return vaccineTypeService.getAll();
    }
}
