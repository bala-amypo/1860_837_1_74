package com.example.demo.controller;

import com.example.demo.model.Claim;
import com.example.demo.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @PostMapping
    public Claim createClaim(@RequestBody Claim claim) {
        return claimService.createClaim(claim);
    }

    @GetMapping
    public List<Claim> getAllClaims() {
        return claimService.getAllClaims();
    }

    @GetMapping("/{id}")
    public Claim getClaim(@PathVariable Long id) {
        return claimService.getClaimById(id);
    }
}
