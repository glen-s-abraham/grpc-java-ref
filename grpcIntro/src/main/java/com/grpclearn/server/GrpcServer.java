package com.grpclearn.server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {

	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(6566).addService(new BankService())
		.build();
		
		server.start();
		System.out.println("GRPC Server Listening on port 6566");
		server.awaitTermination();
		
	}

}
