package com.hospital.management.information.system.hospital.service.impl;

import com.hospital.management.information.system.hospital.dto.DepartmentDto;
import com.hospital.management.information.system.hospital.entity.Department;
import com.hospital.management.information.system.hospital.repository.DepartmentRepository;
import com.hospital.management.information.system.hospital.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service
@AllArgsConstructor
public class DepartmentImpl implements DepartmentService {
    private ModelMapper modelMapper;
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        //Dto to jpa entity
        Department department = modelMapper.map(departmentDto, Department.class);
        //saved jpa entity
        Department saveddepartment = departmentRepository.save(department);
        //Jpa entity to Dto
        return modelMapper.map(saveddepartment,DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department  = departmentRepository.findById(id)
                .orElseThrow(()->new ResourceAccessException("Department id does not found!"+id));
        return modelMapper.map(department,DepartmentDto.class);
    }
}
