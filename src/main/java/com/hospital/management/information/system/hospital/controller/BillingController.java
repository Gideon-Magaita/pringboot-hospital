package com.hospital.management.information.system.hospital.controller;

import com.hospital.management.information.system.hospital.dto.BillingDto;
import com.hospital.management.information.system.hospital.entity.Billing;
import com.hospital.management.information.system.hospital.service.BillingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("{id}")
    public ResponseEntity<BillingDto>getBill(@PathVariable("id") Long billId){
        BillingDto getBills = billingService.getBill(billId);
        return ResponseEntity.ok(getBills);
    }
    @GetMapping
    public ResponseEntity<List<BillingDto>>getAllBills(){
        List<BillingDto> listOfBills = billingService.getAllBills();
        return ResponseEntity.ok(listOfBills);
    }
    @PutMapping("{id}")
    public ResponseEntity<BillingDto>updatedBilling(@RequestBody BillingDto billingDto,@PathVariable("id") Long billingId){
        BillingDto updatedBilling = billingService.updateBilling(billingDto,billingId);
        return ResponseEntity.ok(updatedBilling);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteBilling(@PathVariable("id") Long billingId){
        billingService.deleteBilling(billingId);
        return ResponseEntity.ok("Billing deleted successfully!");
    }
}
