package com.grpclear.client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.grpclearn.models.Balance;
import com.grpclearn.models.BalanceCheckRequest;
import com.grpclearn.models.BankServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankClientTest {
	private BankServiceGrpc.BankServiceBlockingStub blockingStub;
	@BeforeAll
	public void setup() {
		ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:6566")
			.usePlaintext()
			.build();
		
		this.blockingStub = BankServiceGrpc.newBlockingStub(channel);	
		
	}
	
	@Test
	public void balanceTest() {
		BalanceCheckRequest balanceCheckReq = BalanceCheckRequest.newBuilder().setAccountNumber(1).build();
		System.out.println(balanceCheckReq);
		Balance balance= this.blockingStub.getBalance(balanceCheckReq);
		System.out.println("Recieved: "+balance.getAmount());
	}
	
}
