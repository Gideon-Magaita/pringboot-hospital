package com.hospital.management.information.system.hospital.entity;

import com.hospital.management.information.system.hospital.enums.PatientGender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PatientGender gender;

    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name="phone_number",length = 13)
    private String phoneNumber;

    @Column(name="address",nullable = false,columnDefinition = "TEXT")
    private String address;

    @CreationTimestamp
    @Column(name="created_at",updatable = false)
    private LocalDateTime createdAt;
}
