package com.hospital.management.information.system.hospital.service.impl;

import com.hospital.management.information.system.hospital.dto.AppointmentDto;
import com.hospital.management.information.system.hospital.entity.Appointment;
import com.hospital.management.information.system.hospital.entity.Doctor;
import com.hospital.management.information.system.hospital.entity.Patient;
import com.hospital.management.information.system.hospital.exception.ResourceNotFoundException;
import com.hospital.management.information.system.hospital.repository.AppointmentRepository;
import com.hospital.management.information.system.hospital.repository.DoctorRepository;
import com.hospital.management.information.system.hospital.repository.DoctorSpecializationRepository;
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
    private DoctorSpecializationRepository doctorSpecializationRepository;
    private ModelMapper modelMapper;

    @Override
    public AppointmentDto addAppointment(AppointmentDto appointmentDto) {
        //Dto->Entity
        Appointment appointment = modelMapper.map(appointmentDto,Appointment.class);
        //Handle Doctor FK relationship
        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId())
                .orElseThrow(()->new ResourceNotFoundException("Doctor id does not found"));
        appointment.setDoctor(doctor);
        // Handle Patient FK relationship
        Patient patient = patientRepository.findById(appointmentDto.getPatientId())
                .orElseThrow(()->new ResourceNotFoundException("Patient id does not found"));
        appointment.setPatient(patient);
        //Saved Entity
        Appointment savedAppointment = appointmentRepository.save(appointment);

        //Entity->Dto
        AppointmentDto responseDTO = new AppointmentDto();
        responseDTO.setId(savedAppointment.getId());
        responseDTO.setAppointmentDate(savedAppointment.getAppointmentDate());
        responseDTO.setStatus(savedAppointment.getStatus());

        //Specialization price
        responseDTO.setConsultationPrice(
                savedAppointment.
                        getDoctor().
                        getSpecialization().
                        getPrice());

        // SPECIALIZATION NAME
        responseDTO.setSpecializationName(
                savedAppointment.getDoctor()
                        .getSpecialization()
                        .getSpecialization()
        );

        responseDTO.setDoctorId(savedAppointment.getDoctor().getId());
        responseDTO.setDoctorName(savedAppointment.getDoctor().getName());

        responseDTO.setPatientId(savedAppointment.getPatient().getId());
        responseDTO.setPatientName(
                savedAppointment.getPatient().getFirstName()
                        + " " +
                        savedAppointment.getPatient().getLastName()
        );

        return responseDTO;
    }

    @Override
    public AppointmentDto getAppointment(Long id) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Appointment not found " + id
                        ));

        AppointmentDto dto = new AppointmentDto();

        dto.setId(appointment.getId());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setStatus(appointment.getStatus());
        dto.setNotes(appointment.getNotes());


        //set consultation price
        dto.setConsultationPrice(
                appointment
                        .getDoctor()
                        .getSpecialization()
                        .getPrice()
        );

        // SPECIALIZATION NAME
        dto.setSpecializationName(
                appointment.getDoctor()
                        .getSpecialization()
                        .getSpecialization()
        );

        // DOCTOR

        dto.setDoctorId(appointment.getDoctor().getId());
        dto.setDoctorName(appointment.getDoctor().getName());

        // PATIENT

        dto.setPatientId(appointment.getPatient().getId());

        dto.setPatientName(
                appointment.getPatient().getFirstName()
                        + " " +
                appointment.getPatient().getLastName()
        );

        return dto;
    }

    @Override
    public List<AppointmentDto> getAllAppointments() {

        List<Appointment> appointments =
                appointmentRepository.findAll();

        return appointments.stream().map(appointment -> {
            AppointmentDto dto = new AppointmentDto();

            dto.setId(appointment.getId());

            dto.setAppointmentDate(appointment.getAppointmentDate());

            dto.setStatus(appointment.getStatus());

            dto.setNotes(appointment.getNotes());

            //set consultation price
            dto.setConsultationPrice(
                    appointment
                            .getDoctor()
                            .getSpecialization()
                            .getPrice()
            );

            // SPECIALIZATION NAME
            dto.setSpecializationName(
                    appointment.getDoctor()
                            .getSpecialization()
                            .getSpecialization()
            );

            // DOCTOR
            dto.setDoctorId(appointment.getDoctor().getId());
            dto.setDoctorName(appointment.getDoctor().getName());

            // PATIENT
            dto.setPatientId(appointment.getPatient().getId());
            dto.setPatientName(appointment.getPatient().getFirstName()
                            + " " + appointment.getPatient().getLastName());

            return dto;

        }).toList();
    }


    @Override
    public AppointmentDto updateAppointments(AppointmentDto appointmentDto, Long id) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Appointment id not found " + id
                        ));

        appointment.setAppointmentDate(
                appointmentDto.getAppointmentDate()
        );

        appointment.setStatus(
                appointmentDto.getStatus()
        );

        appointment.setNotes(
                appointmentDto.getNotes()
        );


        // HANDLE DOCTOR FOREIGN KEY

        Doctor doctor = doctorRepository.findById(
                        appointmentDto.getDoctorId()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found!"
                        ));

        appointment.setDoctor(doctor);


        // HANDLE PATIENT FOREIGN KEY

        Patient patient = patientRepository.findById(
                        appointmentDto.getPatientId()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient not found!"
                        ));

        appointment.setPatient(patient);

        // SAVE UPDATED APPOINTMENT
        Appointment updatedAppointment =
                appointmentRepository.save(appointment);


        // ENTITY -> DTO

        AppointmentDto dto = new AppointmentDto();

        dto.setId(updatedAppointment.getId());

        dto.setAppointmentDate(
                updatedAppointment.getAppointmentDate()
        );

        dto.setStatus(
                updatedAppointment.getStatus()
        );

        dto.setNotes(
                updatedAppointment.getNotes()
        );

        //Set consultation price
        dto.setConsultationPrice(
                updatedAppointment
                        .getDoctor()
                        .getSpecialization()
                        .getPrice()
        );


        // SPECIALIZATION NAME
        dto.setSpecializationName(
                updatedAppointment.getDoctor()
                        .getSpecialization()
                        .getSpecialization()
        );


        // DOCTOR
        dto.setDoctorId(updatedAppointment.getDoctor().getId());
        dto.setDoctorName(updatedAppointment.getDoctor().getName());

        // PATIENT
        dto.setPatientId(updatedAppointment.getPatient().getId());
        dto.setPatientName(
                updatedAppointment.getPatient().getFirstName()
                        + " " +
                        updatedAppointment.getPatient().getLastName()
        );

        return dto;
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Appointment id does not found!"));
        appointmentRepository.deleteById(id);
    }


}
