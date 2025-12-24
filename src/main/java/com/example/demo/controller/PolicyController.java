package com.example.demo.controller;

import com.example.demo.dto.PolicyDto;
import com.example.demo.model.Policy;
import com.example.demo.service.PolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<PolicyDto> createPolicy(@PathVariable Long userId, @RequestBody PolicyDto dto) {
        Policy policy = new Policy();
        policy.setPolicyNumber(dto.getPolicyNumber());
        policy.setPolicyType(dto.getPolicyType());
        policy.setStartDate(dto.getStartDate());
        policy.setEndDate(dto.getEndDate());

        Policy saved = policyService.createPolicy(userId, policy);
        return ResponseEntity.ok(mapToDto(saved));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PolicyDto>> getPoliciesByUser(@PathVariable Long userId) {
        List<Policy> policies = policyService.getPoliciesByUser(userId);
        List<PolicyDto> dtos = policies.stream().map(this::mapToDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    private PolicyDto mapToDto(Policy policy) {
        PolicyDto dto = new PolicyDto();
        dto.setId(policy.getId());
        dto.setUserId(policy.getUser().getId());
        dto.setPolicyNumber(policy.getPolicyNumber());
        dto.setPolicyType(policy.getPolicyType());
        dto.setStartDate(policy.getStartDate());
        dto.setEndDate(policy.getEndDate());
        return dto;
    }
}
