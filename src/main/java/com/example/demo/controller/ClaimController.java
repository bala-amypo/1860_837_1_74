package com.example.demo.controller;

import com.example.demo.model.Claim;
import com.example.demo.service.ClaimService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping("/{policyId}")
    public Claim create(@PathVariable Long policyId, @RequestBody Claim claim) {
        return claimService.createClaim(policyId, claim);
    }

    @GetMapping("/{id}")
    public Claim get(@PathVariable Long id) {
        return claimService.getClaimById(id);
    }
}
