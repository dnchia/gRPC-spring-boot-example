package com.dnchia.grpc.services;

import com.dnchia.grpc.CalculatorRequest;
import com.dnchia.grpc.CalculatorResponse;
import com.dnchia.grpc.CalculatorServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CalculatorService extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public void calculator(CalculatorRequest request, StreamObserver<CalculatorResponse> responseObserver) {
        float operand1 = request.getOperand1();
        float operand2 = request.getOperand2();

        String operator = request.getOperator();

        CalculatorResponse.Builder response = CalculatorResponse.newBuilder();

        if (operator.equals(Operator.SUM.toString())) {
            response.setResult(Float.toString(operand1 + operand2));
        }
        else if (operator.equals(Operator.MULTIPLICATION.toString())) {
            response.setResult(Float.toString(operand1 * operand2));
        }
        else if (operator.equals(Operator.DIVISION.toString())) {
            response.setResult(Float.toString(operand1 / operand2));
        }
        else {
            response.setResult(String.format("Invalid operator: %s, must be one of the following: %s", operator, Operator.values().toString()));
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    enum Operator {
        SUM("+"),
        MULTIPLICATION("*"),
        DIVISION("/");

        private String operator;
        Operator(String operator) {
            this.operator = operator;
        }

        @Override
        public String toString() {
            return operator;
        }
    }
}
