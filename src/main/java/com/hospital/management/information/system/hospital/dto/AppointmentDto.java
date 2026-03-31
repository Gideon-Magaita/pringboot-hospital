package com.hospital.management.information.system.hospital.dto;

import com.hospital.management.information.system.hospital.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    private Long id;
    private LocalDate appointmentDate;
    private AppointmentStatus status;
    private String notes;

    private Long doctorId;
    private Long patientId;
}
