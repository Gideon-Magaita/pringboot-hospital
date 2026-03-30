package com.hospital.management.information.system.hospital.controller;


import com.hospital.management.information.system.hospital.dto.PatientDto;
import com.hospital.management.information.system.hospital.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/hospital")

public class PatientController {

    private PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientDto> addPatient(@RequestBody PatientDto patientDto){
        PatientDto savedPatient = patientService.addPatient(patientDto);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable("id") Long patientId){
        PatientDto patientDto = patientService.getPatient(patientId);
        return ResponseEntity.ok(patientDto);
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>>getAllPatients(){
        List<PatientDto> patientDtos = patientService.getAllPatients();
        return ResponseEntity.ok(patientDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<PatientDto>updatePatient(@RequestBody PatientDto patientDto,@PathVariable("id") Long patientId){
        PatientDto updatedPatient = patientService.updatePatient(patientDto,patientId);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String>deletePatient(@PathVariable("id") Long patientId){
        patientService.deletePatient(patientId);
        return ResponseEntity.ok("Patient deleted successfully!");
    }
}
