package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
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
    public void onCreate() {
        checkedAt = LocalDateTime.now();
    }

    public FraudCheckResult() {}

    public Long getId() { return id; }
    public Claim getClaim() { return claim; }
    public Boolean getIsFraudulent() { return isFraudulent; }
    public String getTriggeredRuleName() { return triggeredRuleName; }
    public String getRejectionReason() { return rejectionReason; }
    public LocalDateTime getCheckedAt() { return checkedAt; }

    public void setId(Long id) { this.id = id; }
    public void setClaim(Claim claim) { this.claim = claim; }
    public void setIsFraudulent(Boolean isFraudulent) { this.isFraudulent = isFraudulent; }
    public void setTriggeredRuleName(String triggeredRuleName) { this.triggeredRuleName = triggeredRuleName; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
}
