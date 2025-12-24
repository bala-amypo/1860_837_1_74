package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Claim;
import com.example.demo.model.FraudCheckResult;
import com.example.demo.model.FraudRule;
import com.example.demo.repository.ClaimRepository;
import com.example.demo.repository.FraudCheckResultRepository;
import com.example.demo.repository.FraudRuleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service   // ⭐ THIS IS CRITICAL
public class FraudDetectionServiceImpl implements FraudDetectionService {

    private final ClaimRepository claimRepository;
    private final FraudRuleRepository fraudRuleRepository;
    private final FraudCheckResultRepository fraudCheckResultRepository;

    // ✅ Constructor Injection (ORDER NOT IMPORTANT HERE)
    public FraudDetectionServiceImpl(
            ClaimRepository claimRepository,
            FraudRuleRepository fraudRuleRepository,
            FraudCheckResultRepository fraudCheckResultRepository) {

        this.claimRepository = claimRepository;
        this.fraudRuleRepository = fraudRuleRepository;
        this.fraudCheckResultRepository = fraudCheckResultRepository;
    }

    @Override
    public FraudCheckResult evaluateClaim(Long claimId) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found"));

        List<FraudRule> rules = fraudRuleRepository.findAll();

        for (FraudRule rule : rules) {
            if ("claimAmount".equalsIgnoreCase(rule.getConditionField())) {

                double claimAmount = claim.getClaimAmount();
                double ruleValue = Double.parseDouble(rule.getValue());

                boolean matched = false;

                switch (rule.getOperator()) {
                    case ">":
                        matched = claimAmount > ruleValue;
                        break;
                    case "<":
                        matched = claimAmount < ruleValue;
                        break;
                    case ">=":
                        matched = claimAmount >= ruleValue;
                        break;
                    case "<=":
                        matched = claimAmount <= ruleValue;
                        break;
                    case "=":
                        matched = claimAmount == ruleValue;
                        break;
                }

                if (matched) {
                    FraudCheckResult result = new FraudCheckResult();
                    result.setClaim(claim);
                    result.setFraudulent(true);
                    result.setTriggeredRuleName(rule.getRuleName());
                    result.setRejectionReason("Matched rule: " + rule.getRuleName());
                    result.setCheckedAt(LocalDateTime.now());

                    return fraudCheckResultRepository.save(result);
                }
            }
        }

        FraudCheckResult cleanResult = new FraudCheckResult();
        cleanResult.setClaim(claim);
        cleanResult.setFraudulent(false);
        cleanResult.setTriggeredRuleName(null);
        cleanResult.setRejectionReason("No fraud rules matched");
        cleanResult.setCheckedAt(LocalDateTime.now());

        return fraudCheckResultRepository.save(cleanResult);
    }

    @Override
    public FraudCheckResult getResultByClaimId(Long claimId) {

        return fraudCheckResultRepository.findByClaimId(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Fraud result not found"));
    }
}
