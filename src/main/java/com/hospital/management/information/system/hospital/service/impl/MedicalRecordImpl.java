package com.hospital.management.information.system.hospital.service.impl;

import com.hospital.management.information.system.hospital.dto.MedicalRecordDto;
import com.hospital.management.information.system.hospital.entity.Doctor;
import com.hospital.management.information.system.hospital.entity.MedicalRecord;
import com.hospital.management.information.system.hospital.entity.Patient;
import com.hospital.management.information.system.hospital.exception.ResourceNotFoundException;
import com.hospital.management.information.system.hospital.repository.DoctorRepository;
import com.hospital.management.information.system.hospital.repository.MedicalRecordRepository;
import com.hospital.management.information.system.hospital.repository.PatientRepository;
import com.hospital.management.information.system.hospital.service.MedicalRecordService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicalRecordImpl implements MedicalRecordService {

    private MedicalRecordRepository medicalRecordRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    @Override
    public MedicalRecordDto createMedicalRecord(MedicalRecordDto medicalRecordDto) {
        //Dto->Entity
        MedicalRecord medicalRecord = modelMapper.map(medicalRecordDto,MedicalRecord.class);
        //Handle doctor foreign key
        Doctor doctor = doctorRepository.findById(medicalRecordDto.getDoctorId())
                .orElseThrow(()->new ResourceNotFoundException("Doctor not found!"));
        medicalRecord.setDoctor(doctor);
        //Handle patient foreign key
        Patient patient = patientRepository.findById(medicalRecordDto.getPatientId())
                .orElseThrow(()->new ResourceNotFoundException("Patient not found!"));
        medicalRecord.setPatient(patient);
        //Saved Entity
        MedicalRecord savedMedicalRecord = medicalRecordRepository.save(medicalRecord);

        //Entity->Dto
        return modelMapper.map(savedMedicalRecord,MedicalRecordDto.class);
    }

    @Override
    public MedicalRecordDto getMedicalRecord(Long id) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Medical record id no found!"));
        return modelMapper.map(medicalRecord,MedicalRecordDto.class);
    }

    @Override
    public List<MedicalRecordDto> getAllMedicalRecords() {
        List<MedicalRecord> medicals = medicalRecordRepository.findAll();
        return medicals.stream().map((medical)->modelMapper
                .map(medical,MedicalRecordDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MedicalRecordDto updateMedicalRecord(MedicalRecordDto medicalRecordDto, Long id) {
        MedicalRecord updatedMedical = medicalRecordRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Medical record not found!"));
        updatedMedical.setDiagnosis(medicalRecordDto.getDiagnosis());
        updatedMedical.setPrescription(medicalRecordDto.getPrescription());
        updatedMedical.setVisitDate(medicalRecordDto.getVisitDate());
        //handle doctor foreign key
        Doctor doctor = doctorRepository.findById(medicalRecordDto.getDoctorId())
                .orElseThrow(()->new ResourceNotFoundException("Doctor not found!"));
        updatedMedical.setDoctor(doctor);
        //handle patient foreign key
        Patient patient = patientRepository.findById(medicalRecordDto.getPatientId())
                .orElseThrow(()->new ResourceNotFoundException("Patient not found!"));
        updatedMedical.setPatient(patient);

        MedicalRecord savedMedicalRecord = medicalRecordRepository.save(updatedMedical);
        //To DTO
        MedicalRecordDto dto = modelMapper.map(savedMedicalRecord,MedicalRecordDto.class);
        dto.setDoctorId(savedMedicalRecord.getDoctor().getId());
        dto.setPatientId(savedMedicalRecord.getPatient().getId());
        return dto;
    }

    @Override
    public void deleteMedicalRecord(Long id) {
        medicalRecordRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Medical record id not found"+id));
        medicalRecordRepository.deleteById(id);
    }
}
