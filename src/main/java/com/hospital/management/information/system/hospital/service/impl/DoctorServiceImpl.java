package com.hospital.management.information.system.hospital.service.impl;

import com.hospital.management.information.system.hospital.dto.DoctorDto;
import com.hospital.management.information.system.hospital.entity.Doctor;
import com.hospital.management.information.system.hospital.exception.ResourceNotFoundException;
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
    private ModelMapper modelMapper;

    @Override
    public DoctorDto addDoctor(DoctorDto doctorDto) {
        //Dto->JPA
        Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
        //Save jpa
        Doctor savedDoctor = doctorRepository.save(doctor);
        //JPA entity ->DTO
//        DoctorDto saveddoctorDto = modelMapper.map(savedDoctor,DoctorDto.class);
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

        Doctor savedDoctor = doctorRepository.save(doctor);
        return modelMapper.map(savedDoctor,DoctorDto.class);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Doctor id does not found"+id));
        doctorRepository.deleteById(id);
    }

}
