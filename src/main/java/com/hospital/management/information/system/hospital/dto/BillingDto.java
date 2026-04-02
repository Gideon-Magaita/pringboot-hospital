package com.hospital.management.information.system.hospital.dto;

import com.hospital.management.information.system.hospital.entity.Patient;
import com.hospital.management.information.system.hospital.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillingDto {
    private Long id;
    private Long patientId;
    private Long appointmentId;
    private BigDecimal totalAmount;
    private PaymentStatus status;

}
