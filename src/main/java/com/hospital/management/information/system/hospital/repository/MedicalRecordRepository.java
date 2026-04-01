package com.hospital.management.information.system.hospital.repository;

import com.hospital.management.information.system.hospital.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {
}
