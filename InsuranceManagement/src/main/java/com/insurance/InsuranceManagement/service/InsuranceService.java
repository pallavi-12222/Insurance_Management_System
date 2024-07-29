package com.insurance.InsuranceManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.InsuranceManagement.model.Claim;
import com.insurance.InsuranceManagement.model.Policy;
import com.insurance.InsuranceManagement.repository.ClaimRepository;
import com.insurance.InsuranceManagement.repository.PolicyRepository;

@Service
public class InsuranceService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private ClaimRepository claimRepository;

    public void processLifeInsuranceClaim(String policyNumber) {
        Policy policy = policyRepository.findByPolicyNumber(policyNumber);
        if (policy != null && "Life".equalsIgnoreCase(policy.getPolicyType())) {
            Claim claim = claimRepository.findByPolicy(policy);
            if (claim != null && "Death Certificate".equalsIgnoreCase(claim.getAdditionalDocs())) {
                // Process life insurance claim logic
                claim.setClaimStatus("Completed");
                claimRepository.save(claim);
            } else {
                throw new RuntimeException("Life insurance claim requires a Death Certificate.");
            }
        } else {
            throw new RuntimeException("Invalid policy for life insurance claim.");
        }
    }

    public void processMotorInsuranceClaim(String policyNumber) {
        Policy policy = policyRepository.findByPolicyNumber(policyNumber);
        if (policy != null && "Motor".equalsIgnoreCase(policy.getPolicyType())) {
            Claim claim = claimRepository.findByPolicy(policy);
            if (claim != null && "Vehicle Report".equalsIgnoreCase(claim.getAdditionalDocs())) {
                // Process motor insurance claim logic
                claim.setClaimStatus("Completed");
                claimRepository.save(claim);
            } else {
                throw new RuntimeException("Motor insurance claim requires a Vehicle Report.");
            }
        } else {
            throw new RuntimeException("Invalid policy for motor insurance claim.");
        }
    }

    public List<Policy> generatePolicyReport() {
        return policyRepository.findByClaimStatus("Claimed");
    }
}
