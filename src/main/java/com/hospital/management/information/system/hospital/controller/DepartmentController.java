package com.hospital.management.information.system.hospital.controller;


import com.hospital.management.information.system.hospital.dto.DepartmentDto;
import com.hospital.management.information.system.hospital.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public ResponseEntity<List<DepartmentDto>>getDepartments(){
        List<DepartmentDto> departments = departmentService.getDepartments();
        return ResponseEntity.ok(departments);
    }
    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto>updateDepartment(@RequestBody DepartmentDto departmentDto,@PathVariable("id") Long departmentId){
        DepartmentDto updatedDepartment = departmentService.updateDepartment(departmentDto,departmentId);
        return ResponseEntity.ok(updatedDepartment);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteDepartment(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok("Department deleted successfully!");
    }
}
