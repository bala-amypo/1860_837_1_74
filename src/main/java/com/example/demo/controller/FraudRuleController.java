package com.example.demo.controller;

import com.example.demo.model.FraudRule;
import com.example.demo.service.FraudRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class FraudRuleController {

    private final FraudRuleService fraudRuleService;

    public FraudRuleController(FraudRuleService fraudRuleService) {
        this.fraudRuleService = fraudRuleService;
    }

    @PostMapping
    public FraudRule add(@RequestBody FraudRule rule) {
        return fraudRuleService.addRule(rule);
    }

    @GetMapping
    public List<FraudRule> list() {
        return fraudRuleService.getAllRules();
    }
}
