package com.dnchia.grpcclient.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculatorResponseDTO {

    private String firstOperand;
    private String secondOperand;
    private String operator;
    private String result;

    @Override
    public String toString() {
        return String.format("{ \"first_operand\": %s, \"second_operand\": %s, \"operator\": %s, \"result\": %s }",
                             this.firstOperand,
                             this.secondOperand,
                             this.operator,
                             this.result);
    }
}
