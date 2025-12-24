package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyNumber;
    private String policyType;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private User user;

    public Policy() {}

    public Long getId() { return id; }
    public String getPolicyNumber() { return policyNumber; }
    public String getPolicyType() { return policyType; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public User getUser() { return user; }

    public void setId(Long id) { this.id = id; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }
    public void setPolicyType(String policyType) { this.policyType = policyType; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public void setUser(User user) { this.user = user; }
}
