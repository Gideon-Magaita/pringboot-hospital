package com.hospital.management.information.system.hospital.dto;

import com.hospital.management.information.system.hospital.entity.Doctor;
import com.hospital.management.information.system.hospital.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordDto {
    private Long id;
    private String diagnosis;
    private String prescription;
    private LocalDate visitDate;
    private Long doctorId;
    private Long patientId;
}
