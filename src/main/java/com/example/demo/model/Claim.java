package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate claimDate;
    private Double claimAmount;
    private String description;

    @ManyToOne
    private Policy policy;

    public Claim() {}

    public Long getId() { return id; }
    public LocalDate getClaimDate() { return claimDate; }
    public Double getClaimAmount() { return claimAmount; }
    public String getDescription() { return description; }
    public Policy getPolicy() { return policy; }

    public void setId(Long id) { this.id = id; }
    public void setClaimDate(LocalDate claimDate) { this.claimDate = claimDate; }
    public void setClaimAmount(Double claimAmount) { this.claimAmount = claimAmount; }
    public void setDescription(String description) { this.description = description; }
    public void setPolicy(Policy policy) { this.policy = policy; }
}
