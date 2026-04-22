package com.hospital.management.information.system.hospital.service;

import com.hospital.management.information.system.hospital.dto.DepartmentDto;

import java.util.List;


public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(Long id);
    List<DepartmentDto> getDepartments();
    DepartmentDto updateDepartment(DepartmentDto departmentDto,Long id);
    void deleteDepartment(Long id);
}
