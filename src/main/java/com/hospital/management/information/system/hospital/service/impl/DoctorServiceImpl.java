package com.hospital.management.information.system.hospital.service.impl;

import com.hospital.management.information.system.hospital.dto.DoctorDto;
import com.hospital.management.information.system.hospital.entity.Department;
import com.hospital.management.information.system.hospital.entity.Doctor;
import com.hospital.management.information.system.hospital.exception.ResourceNotFoundException;
import com.hospital.management.information.system.hospital.repository.DepartmentRepository;
import com.hospital.management.information.system.hospital.repository.DoctorRepository;
import com.hospital.management.information.system.hospital.service.DoctorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    @Override
    public DoctorDto addDoctor(DoctorDto doctorDto) {

        // DTO -> Entity
        Doctor doctor = modelMapper.map(doctorDto, Doctor.class);

        // Load Department
        Department department = departmentRepository.findById(doctorDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department id not found"));

        doctor.setDepartment(department);

        // Save entity
        Doctor savedDoctor = doctorRepository.save(doctor);

        // Entity -> DTO (MANUAL FIX HERE)
        DoctorDto responseDto = new DoctorDto();
        responseDto.setId(savedDoctor.getId());
        responseDto.setName(savedDoctor.getName());
        responseDto.setSpecialization(savedDoctor.getSpecialization());
        responseDto.setPhone(savedDoctor.getPhone());
        responseDto.setStatus(savedDoctor.getStatus());

        responseDto.setDepartmentId(savedDoctor.getDepartment().getId());
        responseDto.setDepartmentName(savedDoctor.getDepartment().getName());

        return responseDto;
    }

    @Override
    public DoctorDto getDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Doctor id does not found"+id));
        return modelMapper.map(doctor,DoctorDto.class);
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map((doctor)->modelMapper
                .map(doctor,DoctorDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDto updateDoctor(DoctorDto doctorDto, Long id) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor id not found: " + id));

        doctor.setName(doctorDto.getName());
        doctor.setSpecialization(doctorDto.getSpecialization());
        doctor.setPhone(doctorDto.getPhone());
        doctor.setStatus(doctorDto.getStatus());

        // Handle department FK
        Department department = departmentRepository.findById(doctorDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department id not found"));

        doctor.setDepartment(department);

        // Save updated entity
        Doctor savedDoctor = doctorRepository.save(doctor);

        // MANUAL DTO MAPPING (IMPORTANT)
        DoctorDto dto = new DoctorDto();
        dto.setId(savedDoctor.getId());
        dto.setName(savedDoctor.getName());
        dto.setSpecialization(savedDoctor.getSpecialization());
        dto.setPhone(savedDoctor.getPhone());
        dto.setStatus(savedDoctor.getStatus());

        dto.setDepartmentId(savedDoctor.getDepartment().getId());
        dto.setDepartmentName(savedDoctor.getDepartment().getName());

        return dto;
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Doctor id does not found"+id));
        doctorRepository.deleteById(id);
    }

}
