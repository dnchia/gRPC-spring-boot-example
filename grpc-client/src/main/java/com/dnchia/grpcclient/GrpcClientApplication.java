package com.dnchia.grpcclient;

import com.dnchia.grpc.CalculatorRequest;
import com.dnchia.grpc.CalculatorResponse;
import com.dnchia.grpc.CalculatorServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class GrpcClientApplication {
	/*
	 * The code of the client is generated by protobuf-maven-plugin on the build stage.
	 *
	 * Classes of grpc-api are used to stablish the connection, then, the grpc-client classes are used
	 * to execute the methods defined on the .proto example.
	 *
	 * For a real use case, CalculatorServiceGrpc, and the related classes, should be extracted, and a library
	 * created, in order to be used from any REST API, etc...
	 */
	public static void main(String[] args) {
		log.info("Opening channel...");

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
				.usePlaintext()
				.build();

		log.info("Channel opened");

		CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);

		CalculatorResponse response = stub.calculator(CalculatorRequest.newBuilder()
				.setOperand1(12.5f)
				.setOperand2(1.5f)
				.setOperator("/")
				.build());

		log.info("Response is: {}", response.getResult());

		channel.shutdown();

		log.info("Channel closed");
	}
}
