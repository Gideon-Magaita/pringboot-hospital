package com.hospital.management.information.system.hospital.entity;

import com.hospital.management.information.system.hospital.enums.AvailabilityStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="doctor_name",nullable = false)
    private String name;

    @Column(name="phone_number",nullable = false,length = 15)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AvailabilityStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    //Doctor specialization
    @ManyToOne
    @JoinColumn(name = "doctor_specialization_id")
    private DoctorSpecialization specialization;

    @CreationTimestamp
    @Column(name="created_at",updatable = false)
    private LocalDateTime createdAt;

    //User for login purposes
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
