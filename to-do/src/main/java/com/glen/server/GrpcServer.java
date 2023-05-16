package com.glen.server;

import java.io.IOException;
import com.glen.services.TaskService;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {
	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(6565).addService(new TaskService())
				.build();

		server.start();
		System.out.println("GRPC Server Listening on port 6565");
		server.awaitTermination();
		

	}
}
