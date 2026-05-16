package com.hospital.management.information.system.hospital.dto;

import com.hospital.management.information.system.hospital.entity.DoctorSpecialization;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSpecializationDto {

    private Long id;

    @NotBlank(message = "This field is required")
    private String specialization;

    @NotBlank(message = "This field is required")
    private BigDecimal price;

    private LocalDateTime createdAt;
}
