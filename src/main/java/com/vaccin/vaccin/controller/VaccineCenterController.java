package com.vaccin.vaccin.controller;

import com.vaccin.vaccin.dto.VaccineCenterCreateDto;
import com.vaccin.vaccin.dto.VaccineCenterDto;
import com.vaccin.vaccin.service.VaccineCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// as putea muta ruta /centers/ aici
@RestController
public class VaccineCenterController {

    @Autowired
    VaccineCenterService vaccineCenterService;

    @GetMapping("/centers/{latitude}/{longitude}")
    public String getCentersAround(@PathVariable double latitude,
                       @PathVariable double longitude) {
        VaccineCenterDto vaccineCenterDto =
                vaccineCenterService.getCentersAroundCoords(latitude, longitude);

        return "Got center by coords";
    }

    @PostMapping("/centers/add")
    public String addCenter(@RequestBody VaccineCenterCreateDto vaccineCenterCreateDto) {

        // partea cu doctorul? doctorul e user, cum facem?
        return vaccineCenterService.addCenter(vaccineCenterCreateDto);
    }
}
