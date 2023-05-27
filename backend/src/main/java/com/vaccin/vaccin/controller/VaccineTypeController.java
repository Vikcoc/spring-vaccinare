package com.vaccin.vaccin.controller;

import com.vaccin.vaccin.dto.VaccineTypeCreateDto;
import com.vaccin.vaccin.dto.VaccineTypeDto;
import com.vaccin.vaccin.service.VaccineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<VaccineTypeDto> addVaccineType(@RequestBody VaccineTypeCreateDto vaccineTypeCreateDto) {

        VaccineTypeDto vaccineTypeDto = vaccineTypeService.addType(vaccineTypeCreateDto);
        return new ResponseEntity<VaccineTypeDto>(vaccineTypeDto, HttpStatus.CREATED);
    }

    @GetMapping("/vaccines")
    public ResponseEntity<List<VaccineTypeDto>> getAllVaccineTypes() {

        List<VaccineTypeDto> vaccineTypes = vaccineTypeService.getAll();
        if (vaccineTypes != null) {
            return new ResponseEntity<>(vaccineTypes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
