package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Claim;
import com.example.demo.model.FraudCheckResult;
import com.example.demo.model.FraudRule;
import com.example.demo.repository.ClaimRepository;
import com.example.demo.repository.FraudCheckResultRepository;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.service.FraudDetectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FraudDetectionServiceImpl implements FraudDetectionService {

    private final ClaimRepository claimRepository;
    private final FraudRuleRepository fraudRuleRepository;
    private final FraudCheckResultRepository resultRepository;

    public FraudDetectionServiceImpl(ClaimRepository claimRepository, FraudRuleRepository fraudRuleRepository, FraudCheckResultRepository resultRepository) {
        this.claimRepository = claimRepository;
        this.fraudRuleRepository = fraudRuleRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public FraudCheckResult evaluateClaim(Long claimId) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found"));

        List<FraudRule> allRules = fraudRuleRepository.findAll();
        boolean isFraudulent = false;
        String triggeredRuleName = null;
        String rejectionReason = null;
        StringBuilder matchedRulesStr = new StringBuilder();

        // Clear existing suspected rules if re-evaluating (optional, but good practice)
        claim.getSuspectedRules().clear();

        for (FraudRule rule : allRules) {
            boolean match = false;
            if ("claimAmount".equals(rule.getConditionField())) {
                double threshold = Double.parseDouble(rule.getValue());
                double amount = claim.getClaimAmount();
                switch (rule.getOperator()) {
                    case ">": match = amount > threshold; break;
                    case "<": match = amount < threshold; break;
                    case ">=": match = amount >= threshold; break;
                    case "<=": match = amount <= threshold; break;
                    case "=": match = amount == threshold; break;
                }
            }

            if (match) {
                isFraudulent = true;
                claim.getSuspectedRules().add(rule);
                if (triggeredRuleName == null) {
                    triggeredRuleName = rule.getRuleName();
                    rejectionReason = "Rule " + rule.getRuleName() + " triggered: " + rule.getConditionField() + " " + rule.getOperator() + " " + rule.getValue();
                }
                if (matchedRulesStr.length() > 0) matchedRulesStr.append(",");
                matchedRulesStr.append(rule.getRuleName());
            }
        }

        FraudCheckResult result = new FraudCheckResult();
        result.setClaim(claim);
        result.setIsFraudulent(isFraudulent);
        result.setTriggeredRuleName(triggeredRuleName);
        result.setRejectionReason(rejectionReason);
        result.setCheckedAt(LocalDateTime.now());
        result.setMatchedRules(matchedRulesStr.toString());

        // claim.setFraudCheckResult(result); // bi-directional
        // Save claim to update suspectedRules
        claimRepository.save(claim);

        return resultRepository.save(result);
    }

    @Override
    public FraudCheckResult getResultByClaim(Long claimId) {
        return resultRepository.findByClaimId(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
    }
}
