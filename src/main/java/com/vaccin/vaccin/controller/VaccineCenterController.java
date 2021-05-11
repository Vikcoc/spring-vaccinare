package com.vaccin.vaccin.controller;

import com.vaccin.vaccin.dto.VaccineCenterCreateDto;
import com.vaccin.vaccin.dto.VaccineCenterDto;
import com.vaccin.vaccin.model.VaccineCenter;
import com.vaccin.vaccin.service.VaccineCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class VaccineCenterController {

    @Autowired
    VaccineCenterService vaccineCenterService;

    @GetMapping("/centers/{latitude}/{longitude}")
    public List<VaccineCenterDto> getCentersAround(@PathVariable double latitude,
                       @PathVariable double longitude) {
        List<VaccineCenterDto> vaccineCenters =
                vaccineCenterService.getCentersAroundCoords(latitude, longitude);

        return vaccineCenters;
    }

    @PostMapping("/centers/add")
    public String addCenter(@RequestBody VaccineCenterCreateDto vaccineCenterCreateDto) {

        return vaccineCenterService.addCenter(vaccineCenterCreateDto);
    }
}
