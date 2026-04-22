package com.hospital.management.information.system.hospital.service.impl;

import com.hospital.management.information.system.hospital.dto.DepartmentDto;
import com.hospital.management.information.system.hospital.entity.Department;
import com.hospital.management.information.system.hospital.exception.ResourceNotFoundException;
import com.hospital.management.information.system.hospital.repository.DepartmentRepository;
import com.hospital.management.information.system.hospital.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<DepartmentDto> getDepartments() {
        List<Department> department = departmentRepository.findAll();
        return department.stream()
                .map((departments)->modelMapper
                        .map(departments,DepartmentDto.class)).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto, Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Department id does not found!"+id));
        department.setName(departmentDto.getName());
        Department updatedDepartment = departmentRepository.save(department);
        return modelMapper.map(updatedDepartment,DepartmentDto.class);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Department id not found!"+id));
        departmentRepository.deleteById(id);
    }
}
