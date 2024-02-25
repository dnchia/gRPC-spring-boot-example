package com.dnchia.grpcclient.services;

import com.dnchia.grpcclient.dtos.CalculatorResponseDTO;
import com.dnchia.grpcclient.model.Operator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/*
 * This integration test needs the GRPC server to be up and running.
 * Disable to avoid the client to need the server to compile.
 */
@SpringBootTest
@Disabled
class GrpcClientServiceIntegrationTest {

    @Autowired
    private GrpcClientService service;

    @Value("${example.grpc.server.host}")
    private String grpcHost;

    @Value("${example.grpc.server.port}")
    private Integer grpcPort;

    @Test
    void contextLoadsWithConfiguredProperties() {
        // Ensures the properties to be loaded and the service created successfully
        Assertions.assertNotNull(this.service);
        Assertions.assertNotNull(this.grpcHost);
        Assertions.assertNotNull(this.grpcPort);

        // Ensures the service loads the properties from the properties file
        Assertions.assertEquals(this.service.getGrpcHost(), this.grpcHost);
        Assertions.assertEquals(this.service.getGrpcPort(), this.grpcPort);
    }

    @Test
    @DisplayName("given_validOperandsAndSumOperator_when_calculate_then_OK")
    void givenValidOperandsAndSumOperatorWhenCalculateThenOK() {
        float validOperand1 = 10f;
        float validOperand2 = 2f;
        Operator operator = Operator.SUM;
        String expectedResult = "12.0";

        CalculatorResponseDTO resultDTO = this.service.calculate(validOperand1, validOperand2, operator);

        Assertions.assertNotNull(resultDTO);
        Assertions.assertNotNull(resultDTO.getResult());

        Assertions.assertEquals(resultDTO.getResult(), expectedResult);
    }

    @Test
    @DisplayName("given_validOperandsAndSubstractOperator_when_calculate_then_OK")
    void givenValidOperandsAndSubstractOperatorWhenCalculateThenOK() {
        float validOperand1 = 10f;
        float validOperand2 = 2f;
        Operator operator = Operator.SUBSTRACT;
        String expectedResult = "8.0";

        CalculatorResponseDTO resultDTO = this.service.calculate(validOperand1, validOperand2, operator);

        Assertions.assertNotNull(resultDTO);
        Assertions.assertNotNull(resultDTO.getResult());

        Assertions.assertEquals(resultDTO.getResult(), expectedResult);
    }

    @Test
    @DisplayName("given_validOperandsAndMultiplyOperator_when_calculate_then_OK")
    void givenValidOperandsAndMultiplyOperatorWhenCalculateThenOK() {
        float validOperand1 = 10f;
        float validOperand2 = 2f;
        Operator operator = Operator.MULTIPLY;
        String expectedResult = "20.0";

        CalculatorResponseDTO resultDTO = this.service.calculate(validOperand1, validOperand2, operator);

        Assertions.assertNotNull(resultDTO);
        Assertions.assertNotNull(resultDTO.getResult());

        Assertions.assertEquals(resultDTO.getResult(), expectedResult);
    }

    @Test
    @DisplayName("given_validOperandsAndDivideOperator_when_calculate_then_OK")
    void givenValidOperandsAndDivideOperatorWhenCalculateThenOK() {
        float validOperand1 = 10f;
        float validOperand2 = 2f;
        Operator operator = Operator.DIVIDE;
        String expectedResult = "5.0";

        CalculatorResponseDTO resultDTO = this.service.calculate(validOperand1, validOperand2, operator);

        Assertions.assertNotNull(resultDTO);
        Assertions.assertNotNull(resultDTO.getResult());

        Assertions.assertEquals(resultDTO.getResult(), expectedResult);
    }

    @Test
    @DisplayName("given_nonValidOperator_when_calculate_then_FAIL")
    void givenNonValidOperatorWhenCalculateThenFAIL() {
        float validOperand1 = 10f;
        float validOperand2 = 2f;

        Assertions.assertThrows(NullPointerException.class, () -> this.service.calculate(validOperand1, validOperand2, null));
    }
}
