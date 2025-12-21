package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fraud_rules", uniqueConstraints = @UniqueConstraint(columnNames = "ruleName"))
public class FraudRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;
    private String conditionField;
    private String operator;
    private String value;
    private String severity;

    public FraudRule() {}

    public FraudRule(String ruleName, String conditionField,
                     String operator, String value, String severity) {
        this.ruleName = ruleName;
        this.conditionField = conditionField;
        this.operator = operator;
        this.value = value;
        this.severity = severity;
    }
    // getters & setters
}
