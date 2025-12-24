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
    @JoinColumn(name = "claim_id", nullable = false)
    private Claim claim;

    @Column(nullable = false)
    private Boolean isFraudulent;

    private String triggeredRuleName;

    private String rejectionReason;

    private LocalDateTime checkedAt;

    // ✅ Auto-generate timestamp
    @PrePersist
    public void onCreate() {
        this.checkedAt = LocalDateTime.now();
    }

    // =====================
    // GETTERS & SETTERS
    // =====================

    public Long getId() {
        return id;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }

    public Boolean getIsFraudulent() {
        return isFraudulent;
    }

    // 🔥 REQUIRED BY SERVICE
    public void setFraudulent(Boolean fraudulent) {
        this.isFraudulent = fraudulent;
    }

    public String getTriggeredRuleName() {
        return triggeredRuleName;
    }

    public void setTriggeredRuleName(String triggeredRuleName) {
        this.triggeredRuleName = triggeredRuleName;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public LocalDateTime getCheckedAt() {
        return checkedAt;
    }

    // 🔥 REQUIRED BY SERVICE
    public void setCheckedAt(LocalDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }
}
