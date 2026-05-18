package com.hospital.management.information.system.hospital.controller;

import com.hospital.management.information.system.hospital.dto.AppointmentDto;
import com.hospital.management.information.system.hospital.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PreAuthorize("hasAnyRole('ADMIN','RECEPTIONIST')")
    @GetMapping("{id}")
    public ResponseEntity<AppointmentDto> getAppointment(@PathVariable("id") Long appointmentId) {
        AppointmentDto appointment = appointmentService.getAppointment(appointmentId);
        return ResponseEntity.ok(appointment);
    }

    @PreAuthorize("hasAnyRole('ADMIN','RECEPTIONIST')")
    @GetMapping
    public ResponseEntity<List<AppointmentDto>>getAllAppointments(){
        List<AppointmentDto> appointmentList = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointmentList);

    }

    @PutMapping("{id}")
    public ResponseEntity<AppointmentDto>updateAppointments(@RequestBody AppointmentDto appointmentDto,@PathVariable("id") Long appointmentId){
        AppointmentDto updatedAppointment = appointmentService.updateAppointments(appointmentDto,appointmentId);
        return ResponseEntity.ok(updatedAppointment);
    }

    @PreAuthorize("hasAnyRole('ADMIN','RECEPTIONIST')")
    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteAppointment(@PathVariable("id") Long appointmentId){
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.ok("Appointment deleted successfully!");
    }

    //Get patients appointments by doctor username
    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/doctor/appointments")
    public ResponseEntity<List<AppointmentDto>>
    getDoctorAppointments(Authentication authentication) {

        String username = authentication.getName();

        return ResponseEntity.ok(
                appointmentService.getAppointmentsByDoctor(username)
        );
    }
}
