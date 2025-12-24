package com.example.demo.controller;

import com.example.demo.model.Claim;
import com.example.demo.service.ClaimService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService claimService;

    // ✅ Constructor Injection (CORRECT)
    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    // ✅ CREATE CLAIM (Policy ID REQUIRED)
    @PostMapping("/policy/{policyId}")
    public ResponseEntity<Claim> createClaim(
            @PathVariable Long policyId,
            @RequestBody Claim claim) {

        Claim savedClaim = claimService.createClaim(policyId, claim);
        return ResponseEntity.ok(savedClaim);
    }

    // ✅ GET CLAIM BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable Long id) {
        return ResponseEntity.ok(claimService.getClaimById(id));
    }

    // ✅ GET ALL CLAIMS
    @GetMapping
    public ResponseEntity<List<Claim>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }
}
