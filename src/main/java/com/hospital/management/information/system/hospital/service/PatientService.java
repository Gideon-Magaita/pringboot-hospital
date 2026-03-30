package com.hospital.management.information.system.hospital.service;

import com.hospital.management.information.system.hospital.dto.PatientDto;

import java.util.List;

public interface PatientService {

    PatientDto addPatient(PatientDto patientDto);
    PatientDto getPatient(Long id);
    List<PatientDto> getAllPatients();
    PatientDto updatePatient(PatientDto patientDto,Long id);
    void deletePatient(Long id);
}
