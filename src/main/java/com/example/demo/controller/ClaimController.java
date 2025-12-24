package com.example.demo.controller;

import com.example.demo.dto.ClaimDto;
import com.example.demo.model.Claim;
import com.example.demo.service.ClaimService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping("/{policyId}")
    public ResponseEntity<ClaimDto> createClaim(@PathVariable Long policyId, @RequestBody ClaimDto dto) {
        Claim claim = new Claim();
        claim.setClaimDate(dto.getClaimDate());
        claim.setClaimAmount(dto.getClaimAmount());
        claim.setDescription(dto.getDescription());
        // status is set in service to PENDING if null

        Claim saved = claimService.createClaim(policyId, claim);
        return ResponseEntity.ok(mapToDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaimDto> getClaim(@PathVariable Long id) {
        Claim claim = claimService.getClaim(id);
        return ResponseEntity.ok(mapToDto(claim));
    }

    @GetMapping
    public ResponseEntity<List<ClaimDto>> getAllClaims() {
        List<Claim> claims = claimService.getAllClaims();
        return ResponseEntity.ok(claims.stream().map(this::mapToDto).collect(Collectors.toList()));
    }

    private ClaimDto mapToDto(Claim claim) {
        ClaimDto dto = new ClaimDto();
        dto.setId(claim.getId());
        dto.setPolicyId(claim.getPolicy().getId());
        dto.setClaimDate(claim.getClaimDate());
        dto.setClaimAmount(claim.getClaimAmount());
        dto.setDescription(claim.getDescription());
        dto.setStatus(claim.getStatus());
        return dto;
    }
}
