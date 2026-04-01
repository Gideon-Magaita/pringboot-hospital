package com.hospital.management.information.system.hospital.service;


import com.hospital.management.information.system.hospital.dto.AppointmentDto;

public interface AppointmentService {
    AppointmentDto addAppointment(AppointmentDto appointmentDto);
    AppointmentDto getAppointment(Long id);
    
}
