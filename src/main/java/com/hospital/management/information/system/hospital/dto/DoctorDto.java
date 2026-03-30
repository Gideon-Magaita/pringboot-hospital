package com.hospital.management.information.system.hospital.dto;

import com.hospital.management.information.system.hospital.enums.AvailabilityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private Long id;
    private String name;
    private String specialization;
    private String phone;
    private AvailabilityStatus status;
}
