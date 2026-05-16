package com.hospital.management.information.system.hospital.dto;

import com.hospital.management.information.system.hospital.enums.PatientCategory;
import com.hospital.management.information.system.hospital.enums.PatientGender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Gender is required")
    private PatientGender gender;

    @NotNull(message = "Patient category is required")
    private PatientCategory category;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @NotBlank(message="Phone number is required")
    @Pattern(
            regexp = "^0[0-9]{9}$",
            message = "Phone number must start with 0 and contain exactly 10 digits"
    )
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;

    private LocalDateTime createdAt;
}
