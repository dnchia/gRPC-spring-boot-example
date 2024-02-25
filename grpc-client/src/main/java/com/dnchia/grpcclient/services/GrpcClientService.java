package com.dnchia.grpcclient.services;

import com.dnchia.grpc.CalculatorRequest;
import com.dnchia.grpc.CalculatorResponse;
import com.dnchia.grpc.CalculatorServiceGrpc;
import com.dnchia.grpcclient.dtos.CalculatorResponseDTO;
import com.dnchia.grpcclient.model.Operator;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Getter
@Setter
public class GrpcClientService {

    @Value("${example.grpc.server.host}")
    private String grpcHost;

    @Value("${example.grpc.server.port}")
    private Integer grpcPort;

    public CalculatorResponseDTO calculate(float firstOperand, float secondOperand, @NonNull Operator operator) {
        log.info("Opening channel...");

        ManagedChannel channel = ManagedChannelBuilder.forAddress(grpcHost, grpcPort)
                                                      .usePlaintext()
                                                      .build();

        log.info("Channel opened");

        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);

        CalculatorResponse response = stub.calculator(CalculatorRequest.newBuilder()
                .setOperand1(firstOperand)
                .setOperand2(secondOperand)
                .setOperator(operator.getValue())
                .build());

        log.info("Response is: {}", response.getResult());

        channel.shutdown();

        log.info("Channel closed");

        CalculatorResponseDTO result = new CalculatorResponseDTO();
        result.setResult(response.getResult());
        result.setFirstOperand(String.valueOf(firstOperand));
        result.setSecondOperand(String.valueOf(secondOperand));
        result.setOperator(operator.toString());

        return result;
    }
}
