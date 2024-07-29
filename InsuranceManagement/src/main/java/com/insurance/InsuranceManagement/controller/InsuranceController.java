package com.insurance.InsuranceManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.InsuranceManagement.model.Policy;
import com.insurance.InsuranceManagement.repository.PolicyRepository;
import com.insurance.InsuranceManagement.service.InsuranceService;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private PolicyRepository policyRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPolicies(@RequestBody List<Policy> policies) {
        policyRepository.saveAll(policies);
        return ResponseEntity.ok("Policies uploaded successfully");
    }

    @PostMapping("/claim/life")
    public ResponseEntity<String> processLifeInsuranceClaim(@RequestParam String policyNumber) {
        insuranceService.processLifeInsuranceClaim(policyNumber);
        return ResponseEntity.ok("Life insurance claim processed successfully");
    }

    @PostMapping("/claim/motor")
    public ResponseEntity<String> processMotorInsuranceClaim(@RequestParam String policyNumber) {
        insuranceService.processMotorInsuranceClaim(policyNumber);
        return ResponseEntity.ok("Motor insurance claim processed successfully");
    }

    @GetMapping("/report")
    public ResponseEntity<List<Policy>> generatePolicyReport() {
        List<Policy> policyReport = insuranceService.generatePolicyReport();
        return ResponseEntity.ok(policyReport);
    }
}