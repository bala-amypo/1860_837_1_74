package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_check_results")
public class FraudCheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Claim claim;

    private Boolean isFraudulent;
    private String triggeredRuleName;
    private String rejectionReason;
    private LocalDateTime checkedAt;

    @PrePersist
    public void prePersist() {
        this.checkedAt = LocalDateTime.now();
    }

    public FraudCheckResult() {}

    public FraudCheckResult(Claim claim, Boolean isFraudulent,
                            String triggeredRuleName, String rejectionReason) {
        this.claim = claim;
        this.isFraudulent = isFraudulent;
        this.triggeredRuleName = triggeredRuleName;
        this.rejectionReason = rejectionReason;
    }
    // getters & setters
}
