package com.example.demo.controller;

import com.example.demo.dto.FraudRuleDto;
import com.example.demo.model.FraudRule;
import com.example.demo.service.FraudRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rules")
public class FraudRuleController {

    private final FraudRuleService fraudRuleService;

    public FraudRuleController(FraudRuleService fraudRuleService) {
        this.fraudRuleService = fraudRuleService;
    }

    @PostMapping
    public ResponseEntity<FraudRuleDto> addRule(@RequestBody FraudRuleDto dto) {
        FraudRule rule = new FraudRule(dto.getRuleName(), dto.getConditionField(), dto.getOperator(), dto.getValue(), dto.getSeverity());
        FraudRule saved = fraudRuleService.addRule(rule);
        return ResponseEntity.ok(mapToDto(saved));
    }

    @GetMapping
    public ResponseEntity<List<FraudRuleDto>> getAllRules() {
        List<FraudRule> rules = fraudRuleService.getAllRules();
        return ResponseEntity.ok(rules.stream().map(this::mapToDto).collect(Collectors.toList()));
    }

    private FraudRuleDto mapToDto(FraudRule rule) {
        FraudRuleDto dto = new FraudRuleDto();
        dto.setId(rule.getId());
        dto.setRuleName(rule.getRuleName());
        dto.setConditionField(rule.getConditionField());
        dto.setOperator(rule.getOperator());
        dto.setValue(rule.getValue());
        dto.setSeverity(rule.getSeverity());
        return dto;
    }
}
