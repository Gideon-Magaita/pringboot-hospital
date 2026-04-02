package com.hospital.management.information.system.hospital.repository;

import com.hospital.management.information.system.hospital.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing,Long> {
}
