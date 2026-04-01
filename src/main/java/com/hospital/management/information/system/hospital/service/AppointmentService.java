package com.hospital.management.information.system.hospital.service;


import com.hospital.management.information.system.hospital.dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    AppointmentDto addAppointment(AppointmentDto appointmentDto);
    AppointmentDto getAppointment(Long id);
    List<AppointmentDto> getAllAppointments();
    AppointmentDto updateAppointments(AppointmentDto appointmentDto,Long id);
    void deleteAppointment(Long id);
}
