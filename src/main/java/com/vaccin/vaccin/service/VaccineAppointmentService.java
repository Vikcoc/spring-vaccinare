package com.vaccin.vaccin.service;

import com.vaccin.vaccin.dto.VaccineAppointmentDto;
import com.vaccin.vaccin.repository.VaccineAppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineAppointmentService {

    private VaccineAppointmentRepository vaccineAppointmentRepository;

    public List<VaccineAppointmentDto> vaccineAppointmentDtoList() {
        return vaccineAppointmentRepository
                .findAll()
                .stream().map(VaccineAppointmentDto::new)
                .collect(Collectors.toList());
    }
}
