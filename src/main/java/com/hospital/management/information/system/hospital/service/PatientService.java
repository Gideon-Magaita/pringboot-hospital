package com.hospital.management.information.system.hospital.service;

import com.hospital.management.information.system.hospital.dto.PatientDto;

public interface PatientService {

    PatientDto addPatient(PatientDto patientDto);
    PatientDto getPatient(Long id);
}
