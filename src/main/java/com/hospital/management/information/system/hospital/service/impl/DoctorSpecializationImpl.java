package com.hospital.management.information.system.hospital.service.impl;

import com.hospital.management.information.system.hospital.dto.DoctorSpecializationDto;
import com.hospital.management.information.system.hospital.entity.DoctorSpecialization;
import com.hospital.management.information.system.hospital.repository.DoctorSpecializationRepository;
import com.hospital.management.information.system.hospital.service.DoctorSpecializationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorSpecializationImpl implements DoctorSpecializationService {

    private DoctorSpecializationRepository doctorSpecializationRepository;
    private ModelMapper modelMapper;

    @Override
    public DoctorSpecializationDto createSpecialization(DoctorSpecializationDto doctorSpecializationDto) {
        //Dto to Jpa entity
        DoctorSpecialization specializations = modelMapper.map(doctorSpecializationDto,DoctorSpecialization.class);

        DoctorSpecialization savedDoctorSpecialization = doctorSpecializationRepository.save(specializations);

        //Saved entity to Dto

        return modelMapper.map(savedDoctorSpecialization,DoctorSpecializationDto.class);
    }

    @Override
    public List<DoctorSpecializationDto> getAllSpecializations() {
        List<DoctorSpecialization> doctor = doctorSpecializationRepository.findAll();
        return doctor.stream()
                .map((doctors)->modelMapper
                        .map(doctors,DoctorSpecializationDto.class))
                .collect(Collectors.toList());
    }
}
