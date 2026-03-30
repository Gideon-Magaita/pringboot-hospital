package com.hospital.management.information.system.hospital.service;

import com.hospital.management.information.system.hospital.dto.DoctorDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DoctorService {

    DoctorDto addDoctor(DoctorDto doctorDto);
    DoctorDto getDoctor(Long id);
    List<DoctorDto> getAllDoctors();
}
