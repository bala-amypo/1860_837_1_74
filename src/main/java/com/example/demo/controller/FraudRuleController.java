package com.example.demo.controller;

import com.example.demo.model.FraudRule;
import com.example.demo.service.FraudRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fraud-rules")
public class FraudRuleController {

    @Autowired
    private FraudRuleService fraudRuleService;

    @PostMapping
    public FraudRule addRule(@RequestBody FraudRule rule) {
        return fraudRuleService.addRule(rule);
    }

    @GetMapping
    public List<FraudRule> getAllRules() {
        return fraudRuleService.getAllRules();
    }
}
