package com.hospital.management.information.system.hospital.controller;


import com.hospital.management.information.system.hospital.dto.DepartmentDto;
import com.hospital.management.information.system.hospital.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/department")
public class DepartmentController {
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto>createDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto>getDepartmentById(@PathVariable("id") Long departmentId){
        DepartmentDto getDepartment = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(getDepartment);
    }
}
