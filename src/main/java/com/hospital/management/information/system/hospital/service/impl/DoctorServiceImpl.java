package com.hospital.management.information.system.hospital.service.impl;

import com.hospital.management.information.system.hospital.dto.DoctorDto;
import com.hospital.management.information.system.hospital.entity.Department;
import com.hospital.management.information.system.hospital.entity.Doctor;
import com.hospital.management.information.system.hospital.entity.DoctorSpecialization;
import com.hospital.management.information.system.hospital.exception.ResourceNotFoundException;
import com.hospital.management.information.system.hospital.repository.DepartmentRepository;
import com.hospital.management.information.system.hospital.repository.DoctorRepository;
import com.hospital.management.information.system.hospital.repository.DoctorSpecializationRepository;
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
    private DoctorSpecializationRepository doctorSpecializationRepository;
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

        // Entity -> DTO
        DoctorDto responseDto = new DoctorDto();
        responseDto.setId(savedDoctor.getId());
        responseDto.setName(savedDoctor.getName());
        responseDto.setPhone(savedDoctor.getPhone());
        responseDto.setStatus(savedDoctor.getStatus());

        responseDto.setDepartmentId(savedDoctor.getDepartment().getId());
        responseDto.setDepartmentName(savedDoctor.getDepartment().getName());

        responseDto.setDoctorSpecializationId(savedDoctor.getSpecialization().getId());
        responseDto.setSpecializationName(savedDoctor.getSpecialization().getSpecialization());

        return responseDto;
    }

    @Override
    public DoctorDto getDoctor(Long id) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor id does not found " + id
                        ));

        DoctorDto dto = new DoctorDto();

        dto.setId(doctor.getId());

        dto.setName(doctor.getName());

        dto.setPhone(doctor.getPhone());

        dto.setStatus(doctor.getStatus());

        // Department
        if (doctor.getDepartment() != null) {

            dto.setDepartmentId(
                    doctor.getDepartment().getId()
            );

            dto.setDepartmentName(
                    doctor.getDepartment().getName()
            );
        }

        // Specialization
        if (doctor.getSpecialization() != null) {

            dto.setDoctorSpecializationId(
                    doctor.getSpecialization().getId()
            );

            dto.setSpecializationName(
                    doctor.getSpecialization().getSpecialization()
            );
        }

        return dto;
    }

    @Override
    public List<DoctorDto> getAllDoctors() {

        List<Doctor> doctors = doctorRepository.findAll();

        return doctors.stream().map(doctor -> {

            DoctorDto dto = new DoctorDto();

            dto.setId(doctor.getId());

            dto.setName(doctor.getName());

            dto.setPhone(doctor.getPhone());

            dto.setStatus(doctor.getStatus());

            // Department
            if (doctor.getDepartment() != null) {

                dto.setDepartmentId(
                        doctor.getDepartment().getId()
                );

                dto.setDepartmentName(
                        doctor.getDepartment().getName()
                );
            }

            // Specialization
            if (doctor.getSpecialization() != null) {

                dto.setDoctorSpecializationId(
                        doctor.getSpecialization().getId()
                );

                dto.setSpecializationName(
                        doctor.getSpecialization().getSpecialization()
                );
            }

            return dto;

        }).collect(Collectors.toList());
    }

    @Override
    public DoctorDto updateDoctor(DoctorDto doctorDto, Long id) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor id not found: " + id));

        doctor.setName(doctorDto.getName());
        doctor.setPhone(doctorDto.getPhone());
        doctor.setStatus(doctorDto.getStatus());

        // Handle department FK
        Department department = departmentRepository.findById(doctorDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department id not found"));

        doctor.setDepartment(department);

        DoctorSpecialization doctorSpecialization = doctorSpecializationRepository.findById(doctorDto.getDoctorSpecializationId())
                .orElseThrow(()->new ResourceNotFoundException("Specialization id does not found"));
        doctor.setSpecialization(doctorSpecialization);


        // Save updated entity
        Doctor savedDoctor = doctorRepository.save(doctor);

        // MANUAL DTO MAPPING
        DoctorDto dto = new DoctorDto();
        dto.setId(savedDoctor.getId());
        dto.setName(savedDoctor.getName());
        dto.setPhone(savedDoctor.getPhone());
        dto.setStatus(savedDoctor.getStatus());

        dto.setDepartmentId(savedDoctor.getDepartment().getId());
        dto.setDepartmentName(savedDoctor.getDepartment().getName());

        dto.setDoctorSpecializationId(savedDoctor.getSpecialization().getId());
        dto.setSpecializationName(savedDoctor.getSpecialization().getSpecialization());

        return dto;
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Doctor id does not found"+id));
        doctorRepository.deleteById(id);
    }

}
