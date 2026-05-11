package com.hospital.management.information.system.hospital.repository;

import com.hospital.management.information.system.hospital.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillingRepository extends JpaRepository<Billing,Long> {

    Optional<Billing> findByAppointmentId(Long appointmentId);
}
