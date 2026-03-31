package com.hospital.management.information.system.hospital.repository;

import com.hospital.management.information.system.hospital.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
