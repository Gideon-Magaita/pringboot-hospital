package com.hospital.management.information.system.hospital.service.impl;

import com.hospital.management.information.system.hospital.dto.PatientDto;
import com.hospital.management.information.system.hospital.entity.Patient;
import com.hospital.management.information.system.hospital.exception.ResourceNotFoundException;
import com.hospital.management.information.system.hospital.repository.PatientRepository;
import com.hospital.management.information.system.hospital.service.PatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    private ModelMapper modelMapper;

    @Override
    public PatientDto addPatient(PatientDto patientDto) {

        // DTO → Entity
        Patient patient = modelMapper.map(patientDto,Patient.class);

        // Save entity
        Patient savedPatient = patientRepository.save(patient);

        // Entity → DTO

        return modelMapper.map(savedPatient,PatientDto.class);
    }

    @Override
    public PatientDto getPatient(Long id) {
        Patient patients = patientRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Patient id does not found"+id));
        return modelMapper.map(patients,PatientDto.class);
    }
}
