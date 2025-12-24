package com.example.demo.model;

import jakarta.persistence.*;

@Entity
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

    public Long getId() { return id; }
    public String getRuleName() { return ruleName; }
    public String getConditionField() { return conditionField; }
    public String getOperator() { return operator; }
    public String getValue() { return value; }
    public String getSeverity() { return severity; }

    public void setId(Long id) { this.id = id; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }
    public void setConditionField(String conditionField) { this.conditionField = conditionField; }
    public void setOperator(String operator) { this.operator = operator; }
    public void setValue(String value) { this.value = value; }
    public void setSeverity(String severity) { this.severity = severity; }
}
