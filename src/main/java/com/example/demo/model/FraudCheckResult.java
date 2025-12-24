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
    @JoinColumn(name = "claim_id")
    private Claim claim;

    private Boolean isFraudulent;
    private String triggeredRuleName;
    private String rejectionReason;
    private LocalDateTime checkedAt;

    public FraudCheckResult() {
    }

    public FraudCheckResult(Claim claim, Boolean isFraudulent, String triggeredRuleName, String rejectionReason, LocalDateTime checkedAt) {
        this.claim = claim;
        this.isFraudulent = isFraudulent;
        this.triggeredRuleName = triggeredRuleName;
        this.rejectionReason = rejectionReason;
        this.checkedAt = checkedAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setIsFraudulent(Boolean fraudulent) {
        isFraudulent = fraudulent;
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

    public void setCheckedAt(LocalDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }
    
    // Matched rules helper (not in spec but used in test "testFraudCheckResultSnapshotDoesNotBreak3NF")
    // "FraudCheckResult result = new FraudCheckResult(); result.setMatchedRules("Rule1,Rule2");"
    // The test implies there might be a matchedRules field or it's just testing 3NF.
    // The spec does NOT list `matchedRules` in fields. 
    // Spec fields: id, claim, isFraudulent, triggeredRuleName, rejectionReason, checkedAt.
    // The test `testFraudCheckResultSnapshotDoesNotBreak3NF` calls `setMatchedRules` and `getMatchedRules`.
    // This contradicts the spec. I must follow the spec, but if I want the test to pass, I might need to add it or the test is expecting it to be added.
    // The prompt says "Fields ... triggeredRuleName ... rejectionReason".
    // I will add `matchedRules` field to pass the test, assuming it's an oversight in the spec description vs test.
    // OR, I can assume `rejectionReason` or `triggeredRuleName` stores it?
    // No, `triggeredRuleName` is singular.
    // I'll add `private String matchedRules;` to support the provided test case.
    
    private String matchedRules;

    public String getMatchedRules() {
        return matchedRules;
    }

    public void setMatchedRules(String matchedRules) {
        this.matchedRules = matchedRules;
    }
}
