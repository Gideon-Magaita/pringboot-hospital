package com.hospital.management.information.system.hospital.service;

import com.hospital.management.information.system.hospital.dto.BillingDto;

import java.util.List;

public interface BillingService {
    BillingDto createBilling(BillingDto billingDto);
    BillingDto getBill(Long id);
    List<BillingDto> getAllBills();
    BillingDto updateBilling(BillingDto billingDto,Long id);
    void deleteBilling(Long id);
}
