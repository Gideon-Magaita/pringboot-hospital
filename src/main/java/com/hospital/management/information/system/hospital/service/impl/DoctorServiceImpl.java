package com.hospital.management.information.system.hospital.service.impl;

import com.hospital.management.information.system.hospital.dto.DoctorDto;
import com.hospital.management.information.system.hospital.entity.*;
import com.hospital.management.information.system.hospital.exception.ResourceNotFoundException;
import com.hospital.management.information.system.hospital.repository.*;
import com.hospital.management.information.system.hospital.service.DoctorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private DoctorRepository doctorRepository;
    private DepartmentRepository departmentRepository;
    private DoctorSpecializationRepository doctorSpecializationRepository;
    private ModelMapper modelMapper;


    @Override
    public DoctorDto addDoctor(DoctorDto doctorDto) {

        // =========================
        // CHECK USERNAME
        // =========================
        if(userRepository.existsByUsername(doctorDto.getUsername())) {

            throw new RuntimeException(
                    "Username already exists"
            );
        }

        // =========================
        // CHECK EMAIL
        // =========================
        if(userRepository.existsByEmail(doctorDto.getEmail())) {

            throw new RuntimeException(
                    "Email already exists"
            );
        }

        // =========================
        // CREATE USER ACCOUNT
        // =========================
        User user = new User();

        user.setName(
                doctorDto.getName()
        );

        user.setUsername(
                doctorDto.getUsername()
        );

        user.setEmail(
                doctorDto.getEmail()
        );

        user.setPassword(
                passwordEncoder.encode(
                        doctorDto.getPassword()
                )
        );
//        user.setRoles(Set.of(roleRepository.findByName("DOCTOR")));

        // =========================
        // GET ROLE_DOCTOR
        // =========================
        Role role = roleRepository.findByName(
                "ROLE_DOCTOR"
        );

        // CREATE ROLE IF NOT EXISTS
        if(role == null) {

            role = new Role();

            role.setName("ROLE_DOCTOR");

            role = roleRepository.save(role);
        }

        Set<Role> roles = new HashSet<>();

        roles.add(role);

        user.setRoles(roles);

        // SAVE USER
        User savedUser = userRepository.save(user);

        // =========================
        // DTO -> ENTITY
        // =========================
        Doctor doctor = modelMapper.map(
                doctorDto,
                Doctor.class
        );

        // =========================
        // LOAD DEPARTMENT
        // =========================
        Department department =
                departmentRepository.findById(
                                doctorDto.getDepartmentId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Department id not found"
                                ));

        doctor.setDepartment(department);

        // =========================
        // LOAD SPECIALIZATION
        // =========================
        DoctorSpecialization specialization =
                doctorSpecializationRepository.findById(
                                doctorDto.getDoctorSpecializationId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Specialization id not found"
                                ));

        doctor.setSpecialization(specialization);

        // =========================
        // LINK USER TO DOCTOR
        // =========================
        doctor.setUser(savedUser);

        // =========================
        // SAVE DOCTOR
        // =========================
        Doctor savedDoctor =
                doctorRepository.save(doctor);

        // =========================
        // ENTITY -> DTO
        // =========================
        DoctorDto responseDto = new DoctorDto();

        responseDto.setId(
                savedDoctor.getId()
        );

        responseDto.setName(
                savedDoctor.getName()
        );

        responseDto.setPhone(
                savedDoctor.getPhone()
        );

        responseDto.setStatus(
                savedDoctor.getStatus()
        );

        // DEPARTMENT
        responseDto.setDepartmentId(
                savedDoctor.getDepartment().getId()
        );

        responseDto.setDepartmentName(
                savedDoctor.getDepartment().getName()
        );

        // SPECIALIZATION
        responseDto.setDoctorSpecializationId(
                savedDoctor.getSpecialization().getId()
        );

        responseDto.setSpecializationName(
                savedDoctor.getSpecialization().getSpecialization()
        );

        // LOGIN DETAILS
        responseDto.setUsername(
                savedUser.getUsername()
        );

        responseDto.setEmail(
                savedUser.getEmail()
        );

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

        // =========================
        // DEPARTMENT
        // =========================
        if (doctor.getDepartment() != null) {

            dto.setDepartmentId(
                    doctor.getDepartment().getId()
            );

            dto.setDepartmentName(
                    doctor.getDepartment().getName()
            );
        }

        // =========================
        // SPECIALIZATION
        // =========================
        if (doctor.getSpecialization() != null) {

            dto.setDoctorSpecializationId(
                    doctor.getSpecialization().getId()
            );

            dto.setSpecializationName(
                    doctor.getSpecialization()
                            .getSpecialization()
            );
        }

        // =========================
        // USER LOGIN DETAILS
        // =========================
        if(doctor.getUser() != null) {

            dto.setUsername(
                    doctor.getUser().getUsername()
            );

            dto.setEmail(
                    doctor.getUser().getEmail()
            );
        }

        return dto;
    }


    @Override
    public List<DoctorDto> getAllDoctors() {

        return doctorRepository.findAll().stream().map(doctor -> {

            DoctorDto dto = new DoctorDto();

            dto.setId(doctor.getId());
            dto.setName(doctor.getName());
            dto.setPhone(doctor.getPhone());
            dto.setStatus(doctor.getStatus());

            // department
            if (doctor.getDepartment() != null) {
                dto.setDepartmentId(doctor.getDepartment().getId());
                dto.setDepartmentName(doctor.getDepartment().getName());
            }

            // specialization
            if (doctor.getSpecialization() != null) {
                dto.setDoctorSpecializationId(doctor.getSpecialization().getId());
                dto.setSpecializationName(
                        doctor.getSpecialization().getSpecialization()
                );
            }

            // This fetch username and email and display to frontend
            if (doctor.getUser() != null) {
                dto.setUsername(doctor.getUser().getUsername());
                dto.setEmail(doctor.getUser().getEmail());
            }

            return dto;

        }).toList();
    }



    @Override
    public DoctorDto updateDoctor(DoctorDto doctorDto, Long id) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Doctor id not found: " + id));

        // =========================
        // UPDATE DOCTOR FIELDS
        // =========================
        doctor.setName(doctorDto.getName());
        doctor.setPhone(doctorDto.getPhone());
        doctor.setStatus(doctorDto.getStatus());

        // Department
        Department department = departmentRepository.findById(doctorDto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department id not found"));

        doctor.setDepartment(department);

        // Specialization
        DoctorSpecialization doctorSpecialization =
                doctorSpecializationRepository.findById(doctorDto.getDoctorSpecializationId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Specialization id not found"));

        doctor.setSpecialization(doctorSpecialization);

        // =========================
        // UPDATE USER (USERNAME + EMAIL)
        // =========================
        if (doctor.getUser() != null) {

            User user = doctor.getUser();

            // optional safety checks (avoid duplicates)
            if (doctorDto.getUsername() != null &&
                    !doctorDto.getUsername().equals(user.getUsername())) {

                if (userRepository.existsByUsername(doctorDto.getUsername())) {
                    throw new RuntimeException("Username already exists");
                }

                user.setUsername(doctorDto.getUsername());
            }

            if (doctorDto.getEmail() != null &&
                    !doctorDto.getEmail().equals(user.getEmail())) {

                if (userRepository.existsByEmail(doctorDto.getEmail())) {
                    throw new RuntimeException("Email already exists");
                }

                user.setEmail(doctorDto.getEmail());
            }

            // update name too (optional but recommended)
            if (doctorDto.getName() != null) {
                user.setName(doctorDto.getName());
            }

            userRepository.save(user);
        }

        // =========================
        // SAVE DOCTOR
        // =========================
        Doctor savedDoctor = doctorRepository.save(doctor);

        // =========================
        // RESPONSE DTO
        // =========================
        DoctorDto dto = new DoctorDto();
        dto.setId(savedDoctor.getId());
        dto.setName(savedDoctor.getName());
        dto.setPhone(savedDoctor.getPhone());
        dto.setStatus(savedDoctor.getStatus());

        dto.setDepartmentId(savedDoctor.getDepartment().getId());
        dto.setDepartmentName(savedDoctor.getDepartment().getName());

        dto.setDoctorSpecializationId(savedDoctor.getSpecialization().getId());
        dto.setSpecializationName(savedDoctor.getSpecialization().getSpecialization());

        // =========================
        // USER DATA
        // =========================
        if (savedDoctor.getUser() != null) {
            dto.setUsername(savedDoctor.getUser().getUsername());
            dto.setEmail(savedDoctor.getUser().getEmail());
        }

        return dto;
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Doctor id does not found"+id));
        doctorRepository.deleteById(id);
    }

}
