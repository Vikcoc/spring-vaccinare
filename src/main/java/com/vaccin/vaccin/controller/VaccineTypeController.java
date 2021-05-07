package com.vaccin.vaccin.controller;

import com.vaccin.vaccin.dto.VaccineTypeDto;
import com.vaccin.vaccin.service.VaccineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VaccineTypeController {

    @Autowired
    VaccineTypeService vaccineTypeService;

    @RequestMapping("/vaccines")
    public List<VaccineTypeDto> getAllVaccineTypes() {

        return vaccineTypeService.getAll();
    }
}
