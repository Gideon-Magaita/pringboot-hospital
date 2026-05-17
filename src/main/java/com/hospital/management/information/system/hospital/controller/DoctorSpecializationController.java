package com.hospital.management.information.system.hospital.controller;
import com.hospital.management.information.system.hospital.dto.DoctorSpecializationDto;
import com.hospital.management.information.system.hospital.service.DoctorSpecializationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/specialization")
@AllArgsConstructor
public class DoctorSpecializationController {

    private DoctorSpecializationService doctorSpecializationService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DoctorSpecializationDto>createSpecialization(@RequestBody DoctorSpecializationDto doctorSpecializationDto){
        DoctorSpecializationDto savedDoctor = doctorSpecializationService.createSpecialization(doctorSpecializationDto);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<DoctorSpecializationDto>>getAllSpecialization(){
        List<DoctorSpecializationDto> doctorList = doctorSpecializationService.getAllSpecializations();
        return ResponseEntity.ok(doctorList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<DoctorSpecializationDto>getSpecializationById(@PathVariable("id")Long specializationId){
        DoctorSpecializationDto specializations = doctorSpecializationService.getSpecializationById(specializationId);
        return ResponseEntity.ok(specializations);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<DoctorSpecializationDto>updateSpecialization(
            @RequestBody DoctorSpecializationDto doctorSpecializationDto,
            @PathVariable("id") Long specializationId)
    {
        DoctorSpecializationDto updatedDoctor = doctorSpecializationService
                .updateSpecialization(doctorSpecializationDto,specializationId);
        return ResponseEntity.ok(updatedDoctor);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteSpecialization(@PathVariable("id") Long specializationId){
        doctorSpecializationService.deleteSpecilization(specializationId);
        return ResponseEntity.ok("Doctor specialization deleted successfully!!");

    }
}
