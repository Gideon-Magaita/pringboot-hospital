package com.hospital.management.information.system.hospital.controller;

import com.hospital.management.information.system.hospital.dto.MedicalRecordDto;
import com.hospital.management.information.system.hospital.entity.MedicalRecord;
import com.hospital.management.information.system.hospital.service.MedicalRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/medical")
public class MedicalRecordController {

    private MedicalRecordService medicalRecordService;

    @PostMapping
    public ResponseEntity<MedicalRecordDto>createMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto){
        MedicalRecordDto createdMedical = medicalRecordService.createMedicalRecord(medicalRecordDto);
        return new ResponseEntity<>(createdMedical, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<MedicalRecordDto>getMedicalRecord(@PathVariable("id") Long medicalId){
        MedicalRecordDto medical = medicalRecordService.getMedicalRecord(medicalId);
        return ResponseEntity.ok(medical);
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecordDto>>getAllMedicalRecords(){
        List<MedicalRecordDto> medicallist = medicalRecordService.getAllMedicalRecords();
        return ResponseEntity.ok(medicallist);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<MedicalRecordDto>updateMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto,@PathVariable("id") Long medicalrecordId){
        MedicalRecordDto updatedrecord = medicalRecordService.updateMedicalRecord(medicalRecordDto,medicalrecordId);
        return ResponseEntity.ok(updatedrecord);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteMedicalRecord(@PathVariable("id") Long medicalrecordId){
        medicalRecordService.deleteMedicalRecord(medicalrecordId);
        return ResponseEntity.ok("Medical record deleted!");
    }
}
