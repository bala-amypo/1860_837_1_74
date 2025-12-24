package com.example.demo.controller;

import com.example.demo.model.Claim;
import com.example.demo.service.FraudDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fraud-detection")
public class FraudDetectionController {

    @Autowired
    private FraudDetectionService fraudDetectionService;

    @PostMapping("/check")
    public String detectFraud(@RequestBody Claim claim) {
        boolean fraud = fraudDetectionService.isFraudulent(claim);
        return fraud ? "Fraud detected" : "Claim is safe";
    }
}
