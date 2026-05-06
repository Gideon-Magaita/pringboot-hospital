package com.hospital.management.information.system.hospital.controller;


import com.hospital.management.information.system.hospital.dto.DoctorDto;
import com.hospital.management.information.system.hospital.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/doctor")
public class DoctorController {

    private DoctorService doctorService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DoctorDto>addDoctor(@RequestBody DoctorDto doctorDto){
        DoctorDto savedDoctors = doctorService.addDoctor(doctorDto);
        return new ResponseEntity<>(savedDoctors, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<DoctorDto>getDoctor(@PathVariable("id") Long doctorId){
        DoctorDto doctor = doctorService.getDoctor(doctorId);
        return ResponseEntity.ok(doctor);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<DoctorDto>>getAllDoctors(){
        List<DoctorDto> doctorList = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctorList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<DoctorDto>updateDoctor(@RequestBody DoctorDto doctorDto,@PathVariable("id") Long doctorId){
        DoctorDto updatedDoctor = doctorService.updateDoctor(doctorDto,doctorId);
        return ResponseEntity.ok(updatedDoctor);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteDoctor(@PathVariable("id") Long doctorId){
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.ok("Doctor deleted succesfully!");
    }
}
