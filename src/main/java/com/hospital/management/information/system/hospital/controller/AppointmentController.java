package com.hospital.management.information.system.hospital.controller;

import com.hospital.management.information.system.hospital.dto.AppointmentDto;
import com.hospital.management.information.system.hospital.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("api/appointment")
public class AppointmentController {

    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentDto>addAppointment(@RequestBody AppointmentDto appointmentDto){
        AppointmentDto savedAppointment = appointmentService.addAppointment(appointmentDto);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }
}
