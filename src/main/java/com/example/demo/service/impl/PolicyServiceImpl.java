package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Policy;
import com.example.demo.model.User;
import com.example.demo.repository.PolicyRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PolicyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;
    private final UserRepository userRepository;

    public PolicyServiceImpl(PolicyRepository policyRepository, UserRepository userRepository) {
        this.policyRepository = policyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Policy createPolicy(Long userId, Policy policy) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (policyRepository.existsByPolicyNumber(policy.getPolicyNumber())) {
            throw new IllegalArgumentException("Policy number already exists");
        }
        if (!policy.getEndDate().isAfter(policy.getStartDate())) {
            throw new IllegalArgumentException("Invalid dates: End date must be after Start date");
        }

        policy.setUser(user);
        return policyRepository.save(policy);
    }

    @Override
    public List<Policy> getPoliciesByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            // Per spec "If associated user is not found... throw ResourceNotFoundException"
            // Wait, spec says "If associated user is not found for given userId, throw ResourceNotFoundException"
            // The method logic should check user existence if required, but list usually just returns empty.
            // However, the spec says "Load user by userId; if absent, throw ResourceNotFoundException".
            // So I should check.
             if (!userRepository.existsById(userId)) {
                 // Or actually load it to be sure.
                 // But the method signature just returns List<Policy>.
                 // I will strictly follow spec: "Load user by userId; if absent, throw ResourceNotFoundException".
                 // Actually the spec point "If associated user is not found for given userId..." was under "Rules (PolicyService)".
                 // So I should implement that check.
                 throw new ResourceNotFoundException("User not found");
             }
        }
        return policyRepository.findByUserId(userId);
    }

    @Override
    public Policy getPolicy(Long id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found"));
    }
}
