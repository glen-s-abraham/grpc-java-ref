package com.grpclearn.server;

import java.io.IOException;

import com.grpclearn.services.GameService;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {
	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(6565).addService(new GameService()).build();
		server.start();
		server.awaitTermination();
	}
}
