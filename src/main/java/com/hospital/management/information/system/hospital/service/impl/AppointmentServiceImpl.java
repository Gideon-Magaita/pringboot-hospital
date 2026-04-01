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

import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public AppointmentDto getAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Appointment not found"+id));

        return modelMapper.map(appointment,AppointmentDto.class);
    }

    @Override
    public List<AppointmentDto> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map((appointment)->modelMapper
                .map(appointment,AppointmentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDto updateAppointments(AppointmentDto appointmentDto, Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Appointment id not found"+id));
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setStatus(appointmentDto.getStatus());
        appointment.setNotes(appointmentDto.getNotes());

        //handle doctor foreign key
        Doctor doctor =doctorRepository.findById(appointmentDto.getDoctorId())
                .orElseThrow(()->new ResourceNotFoundException("Doctor not found!"));
        appointment.setDoctor(doctor);

        //handle patient foreign key
        Patient patient = patientRepository.findById(appointmentDto.getPatientId())
                .orElseThrow(()->new ResourceNotFoundException("Patient not found!"));
        appointment.setPatient(patient);

        Appointment updatedAppointment = appointmentRepository.save(appointment);
        //To Dto
        AppointmentDto dto = modelMapper.map(updatedAppointment,AppointmentDto.class);
        dto.setDoctorId(updatedAppointment.getDoctor().getId());
        dto.setPatientId(updatedAppointment.getPatient().getId());

        return dto;
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Appointment id does not found!"));
        appointmentRepository.deleteById(id);
    }


}
