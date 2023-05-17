package com.grpclearn.client;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.grpclearn.models.Die;
import com.grpclearn.models.GameServiceGrpc;
import com.grpclearn.services.GameStateStreamResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameClientTest {
	
	private GameServiceGrpc.GameServiceStub stub;
	
	@BeforeAll
	public void setup() {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();
		this.stub = GameServiceGrpc.newStub(channel);
	}
	
	@Test
	public void clientGame() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		GameStateStreamResponse gameStateStreamResponse = new GameStateStreamResponse(latch);
		StreamObserver<Die> dieStreamObserver = this.stub.roll(gameStateStreamResponse);
		gameStateStreamResponse.setDieStreamObserver(dieStreamObserver);
		gameStateStreamResponse.roll();
		latch.await();
		
	}
}
