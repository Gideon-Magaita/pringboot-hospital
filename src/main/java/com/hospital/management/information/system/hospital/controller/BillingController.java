package com.hospital.management.information.system.hospital.controller;

import com.hospital.management.information.system.hospital.dto.BillingDto;
import com.hospital.management.information.system.hospital.service.BillingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/billing")
public class BillingController {
    private BillingService billingService;

    @PostMapping
    public ResponseEntity<BillingDto>createBilling(@RequestBody BillingDto billingDto){
        BillingDto savedBilling = billingService.createBilling(billingDto);
        return new ResponseEntity<>(savedBilling, HttpStatus.CREATED);
    }
}
