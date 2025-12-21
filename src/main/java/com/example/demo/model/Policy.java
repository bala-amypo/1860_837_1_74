package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "policies", uniqueConstraints = @UniqueConstraint(columnNames = "policyNumber"))
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String policyNumber;
    private String policyType;
    private LocalDate startDate;
    private LocalDate endDate;

    public Policy() {}

    public Policy(User user, String policyNumber, String policyType,
                  LocalDate startDate, LocalDate endDate) {
        this.user = user;
        this.policyNumber = policyNumber;
        this.policyType = policyType;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    // getters & setters
}
