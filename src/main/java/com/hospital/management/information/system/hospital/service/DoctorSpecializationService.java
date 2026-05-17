package com.hospital.management.information.system.hospital.service;

import com.hospital.management.information.system.hospital.dto.DoctorSpecializationDto;

import java.util.List;

public interface DoctorSpecializationService {
    DoctorSpecializationDto createSpecialization(DoctorSpecializationDto doctorSpecializationDto);
    List<DoctorSpecializationDto> getAllSpecializations();
    DoctorSpecializationDto getSpecializationById(Long id);
    DoctorSpecializationDto updateSpecialization(DoctorSpecializationDto doctorSpecializationDto,Long id);
    void deleteSpecilization(Long id);
}
