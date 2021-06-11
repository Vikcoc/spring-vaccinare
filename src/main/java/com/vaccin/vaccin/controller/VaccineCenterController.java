package com.vaccin.vaccin.controller;

import com.vaccin.vaccin.dto.VaccineCenterCreateDto;
import com.vaccin.vaccin.dto.VaccineCenterDto;
import com.vaccin.vaccin.exception.CenterCreateException;
import com.vaccin.vaccin.service.VaccineCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VaccineCenterController {

    VaccineCenterService vaccineCenterService;

    @Autowired
    public VaccineCenterController (VaccineCenterService vaccineCenterService) {
        this.vaccineCenterService = vaccineCenterService;
    }

    @GetMapping("/centers/{latitude}/{longitude}")
    public ResponseEntity<List<VaccineCenterDto>> getCentersAround(@PathVariable double latitude,
                                                                  @PathVariable double longitude) {
        List<VaccineCenterDto> vaccineCenters =
                vaccineCenterService.getCentersAroundCoords(latitude, longitude);

        if (vaccineCenters == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(vaccineCenters, HttpStatus.OK);
    }

    @PostMapping("/centers/add")
    public ResponseEntity<VaccineCenterDto> addCenter(@RequestBody VaccineCenterCreateDto vaccineCenterCreateDto) {

        try {
            VaccineCenterDto vaccineCenterDto = vaccineCenterService.addCenter(vaccineCenterCreateDto);
            return new ResponseEntity<>(vaccineCenterDto, HttpStatus.OK);
        } catch (CenterCreateException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
