package com.hospital.management.information.system.hospital.controller;


import com.hospital.management.information.system.hospital.dto.PatientDto;
import com.hospital.management.information.system.hospital.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
