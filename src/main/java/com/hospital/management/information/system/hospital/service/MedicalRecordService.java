package com.hospital.management.information.system.hospital.service;

import com.hospital.management.information.system.hospital.dto.MedicalRecordDto;
import com.hospital.management.information.system.hospital.entity.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {
    MedicalRecordDto createMedicalRecord(MedicalRecordDto medicalRecordDto);
    MedicalRecordDto getMedicalRecord(Long id);
    List<MedicalRecordDto> getAllMedicalRecords();
    MedicalRecordDto updateMedicalRecord(MedicalRecordDto medicalRecordDto,Long id);
    void deleteMedicalRecord(Long id);
}
