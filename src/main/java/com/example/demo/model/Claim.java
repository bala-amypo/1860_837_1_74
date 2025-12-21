package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "claims")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Policy policy;

    private LocalDate claimDate;
    private Double claimAmount;
    private String description;
    private String status = "PENDING";

    public Claim() {}

    public Claim(Policy policy, LocalDate claimDate,
                 Double claimAmount, String description) {
        this.policy = policy;
        this.claimDate = claimDate;
        this.claimAmount = claimAmount;
        this.description = description;
    }
    // getters & setters
}
