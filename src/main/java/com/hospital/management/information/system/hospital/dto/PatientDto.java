package com.hospital.management.information.system.hospital.dto;

import com.hospital.management.information.system.hospital.enums.PatientGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private PatientGender gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String address;
    private LocalDateTime createdAt;
}
