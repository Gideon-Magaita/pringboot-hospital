package com.hospital.management.information.system.hospital.service;

import com.hospital.management.information.system.hospital.dto.DepartmentDto;


public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(Long id);
}
