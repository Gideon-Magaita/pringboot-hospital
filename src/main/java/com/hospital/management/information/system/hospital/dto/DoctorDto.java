package com.hospital.management.information.system.hospital.dto;

import com.hospital.management.information.system.hospital.enums.AvailabilityStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "Doctor name is required")
    private String name;

    @NotBlank(message="Phone number is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Invalid phone number")
    private String phone;

    @NotNull(message = "Status is required")
    private AvailabilityStatus status;

    @NotNull(message = "Department is required")
    private Long departmentId;

    private String departmentName;

    private Long doctorSpecializationId;

    private String specializationName;

}
