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
        //Dto->JPA
        Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
        //Handle Department & Doctor relationship
        Department department = departmentRepository.findById(doctorDto.getDepartmentId())
                .orElseThrow(()->new ResourceNotFoundException("Department id not found"));
        doctor.setDepartment(department);
        //Save jpa
        Doctor savedDoctor = doctorRepository.save(doctor);
        //JPA entity ->DTO
        return modelMapper.map(savedDoctor,DoctorDto.class);
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
                .orElseThrow(()->new ResourceNotFoundException("Doctor id does not found!"+id));
        doctor.setName(doctorDto.getName());
        doctor.setSpecialization(doctorDto.getSpecialization());
        doctor.setPhone(doctorDto.getPhone());
        doctor.setStatus(doctorDto.getStatus());

        //Handle department foreign key
        Department department = departmentRepository.findById(doctorDto.getDepartmentId())
                .orElseThrow(()->new ResourceNotFoundException("Deparrment id not found"));
        doctor.setDepartment(department);


        Doctor savedDoctor = doctorRepository.save(doctor);
        //To Dto
        DoctorDto dto = modelMapper.map(savedDoctor, DoctorDto.class);
        dto.setDepartmentId(savedDoctor.getDepartment().getId());

        return dto;
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Doctor id does not found"+id));
        doctorRepository.deleteById(id);
    }

}
