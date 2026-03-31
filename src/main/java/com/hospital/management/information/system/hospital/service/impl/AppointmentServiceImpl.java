package com.hospital.management.information.system.hospital.service.impl;

import com.hospital.management.information.system.hospital.dto.AppointmentDto;
import com.hospital.management.information.system.hospital.entity.Appointment;
import com.hospital.management.information.system.hospital.entity.Doctor;
import com.hospital.management.information.system.hospital.entity.Patient;
import com.hospital.management.information.system.hospital.exception.ResourceNotFoundException;
import com.hospital.management.information.system.hospital.repository.AppointmentRepository;
import com.hospital.management.information.system.hospital.repository.DoctorRepository;
import com.hospital.management.information.system.hospital.repository.PatientRepository;
import com.hospital.management.information.system.hospital.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    @Override
    public AppointmentDto addAppointment(AppointmentDto appointmentDto) {
        //Dto->Entity
        Appointment appointment = modelMapper.map(appointmentDto,Appointment.class);
        //Handle Doctor relationship
        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId())
                .orElseThrow(()->new ResourceNotFoundException("Doctor id does not found"));
        appointment.setDoctor(doctor);
        // Handle Patient relationship
        Patient patient = patientRepository.findById(appointmentDto.getPatientId())
                .orElseThrow(()->new ResourceNotFoundException("Patient id does not found"));
        appointment.setPatient(patient);
        //Saved Entity
        Appointment savedAppointment = appointmentRepository.save(appointment);
        //Entity->Dto
        return modelMapper.map(savedAppointment,AppointmentDto.class);
    }
}
