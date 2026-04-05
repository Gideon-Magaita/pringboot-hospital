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

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public BillingDto getBill(Long id) {
        Billing bills = billingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Bill id does not found"+id));
        return modelMapper.map(bills,BillingDto.class);
    }

    @Override
    public List<BillingDto> getAllBills() {
        List<Billing> bills = billingRepository.findAll();
        return bills.stream().map((bill)->modelMapper.map(bill,BillingDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BillingDto updateBilling(BillingDto billingDto, Long id) {
        Billing billing = billingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Billing id does not found!"));
        billing.setTotalAmount(billingDto.getTotalAmount());
        billing.setStatus(billingDto.getStatus());
        //handle patient foreign key
        Patient patient = patientRepository.findById(billingDto.getPatientId())
                .orElseThrow(()->new ResourceNotFoundException("Patient id not found!"));
        billing.setPatient(patient);
        //Handle appointment foreign key
        Appointment appointment = appointmentRepository.findById(billingDto.getAppointmentId())
                .orElseThrow(()->new ResourceNotFoundException("Appointment id not found!"));
        billing.setAppointment(appointment);

        Billing updatedBilling = billingRepository.save(billing);
        //Convert to Dto
        BillingDto savedBilling = modelMapper.map(updatedBilling,BillingDto.class);
        savedBilling.setPatientId(updatedBilling.getPatient().getId());
        savedBilling.setAppointmentId(updatedBilling.getAppointment().getId());
        return savedBilling;
    }

    @Override
    public void deleteBilling(Long id) {
        billingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Billing id does not found!"));
        billingRepository.deleteById(id);

    }
}
