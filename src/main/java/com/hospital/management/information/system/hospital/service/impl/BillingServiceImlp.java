package com.hospital.management.information.system.hospital.service.impl;

import com.hospital.management.information.system.hospital.dto.BillingDto;
import com.hospital.management.information.system.hospital.entity.Appointment;
import com.hospital.management.information.system.hospital.entity.Billing;
import com.hospital.management.information.system.hospital.entity.Patient;
import com.hospital.management.information.system.hospital.exception.ResourceNotFoundException;
import com.hospital.management.information.system.hospital.repository.AppointmentRepository;
import com.hospital.management.information.system.hospital.repository.BillingRepository;
import com.hospital.management.information.system.hospital.repository.PatientRepository;
import com.hospital.management.information.system.hospital.service.BillingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BillingServiceImlp implements BillingService {
    private BillingRepository billingRepository;
    private PatientRepository patientRepository;
    private AppointmentRepository appointmentRepository;
    private ModelMapper modelMapper;

    @Override
    public BillingDto createBilling(BillingDto billingDto) {
        //Dto->Entity
        Billing billing = modelMapper.map(billingDto, Billing.class);
        //Handle Patient foreignkey
        Patient patient = patientRepository.findById(billingDto.getPatientId())
                .orElseThrow(()->new ResourceNotFoundException("Patient not found!"));
        billing.setPatient(patient);
        //Handle Appointment foregnkey
        Appointment appointment = appointmentRepository.findById(billingDto.getAppointmentId())
                .orElseThrow(()->new ResourceNotFoundException("Appointment not found!"));
        billing.setAppointment(appointment);
        //Saved Entity
        Billing savedBilling = billingRepository.save(billing);
        //Entity->Dto
        return modelMapper.map(savedBilling,BillingDto.class);
    }
}
