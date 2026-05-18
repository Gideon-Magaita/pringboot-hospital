package com.hospital.management.information.system.hospital.repository;

import com.hospital.management.information.system.hospital.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    //Each doctor should see their patients
    List<Appointment> findByDoctorId(Long doctorId);
}
