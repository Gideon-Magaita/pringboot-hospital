package com.hospital.management.information.system.hospital.service.impl;

import com.hospital.management.information.system.hospital.dto.PatientDto;
import com.hospital.management.information.system.hospital.entity.Patient;
import com.hospital.management.information.system.hospital.exception.ResourceNotFoundException;
import com.hospital.management.information.system.hospital.repository.PatientRepository;
import com.hospital.management.information.system.hospital.service.PatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map((patient)->modelMapper
                        .map(patient,PatientDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PatientDto updatePatient(PatientDto patientDto, Long id) {
        Patient patient=patientRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Patient id does not found"+id));

        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setGender(patientDto.getGender());
        patient.setDateOfBirth(patientDto.getDateOfBirth());
        patient.setPhoneNumber(patientDto.getPhoneNumber());
        patient.setAddress(patientDto.getAddress());

        Patient updatedPatient = patientRepository.save(patient);

        return modelMapper.map(updatedPatient,PatientDto.class);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Patient id does not found"+id));
        patientRepository.deleteById(id);
    }
}
