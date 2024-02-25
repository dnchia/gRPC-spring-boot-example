package com.dnchia.grpcclient.model;

public enum Operator {

    SUM("+"),
    SUBSTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String operandValue;
    Operator(String operandValue) {
        this.operandValue = operandValue;
    }

    public String getValue() {
        return this.operandValue;
    }
}
