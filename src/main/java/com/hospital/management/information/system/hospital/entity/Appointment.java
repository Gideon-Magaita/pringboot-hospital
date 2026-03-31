package com.hospital.management.information.system.hospital.entity;

import com.hospital.management.information.system.hospital.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="appointment_date",nullable = false)
    private LocalDate appointmentDate;

    @Enumerated(EnumType.STRING)
    @Column(name="status",nullable = false)
    private AppointmentStatus status;

    @Column(name="notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="patient_id")
    private Patient patient;
}
