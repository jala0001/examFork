package com.example.carrentalexam.services;

import com.example.carrentalexam.repositories.DamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DamageService {
    @Autowired
    private DamageRepository damageRepository;
    public void createDamageReport(int rentalContractId, String descriptionOfDamage, double repairCost, String status) {
        damageRepository.createDamageReport(rentalContractId, descriptionOfDamage, repairCost, status);
    }
}
