package com.hospital.management.information.system.hospital.repository;

import com.hospital.management.information.system.hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    //Each doctor should see their assigned patients by username
    Optional<Doctor> findByUserUsername(String username);
}
