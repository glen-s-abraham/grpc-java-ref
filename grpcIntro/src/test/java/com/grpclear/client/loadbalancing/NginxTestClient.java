package com.grpclear.client.loadbalancing;



import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


import com.grpclearn.models.Balance;
import com.grpclearn.models.BalanceCheckRequest;
import com.grpclearn.models.BankServiceGrpc;
import com.grpclearn.models.WithdrawRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.shaded.io.netty.util.internal.ThreadLocalRandom;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NginxTestClient {
	private BankServiceGrpc.BankServiceBlockingStub blockingStub;
	
	@BeforeAll
	public void setup() {
		ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8585")
			.usePlaintext()
			.build();
		
		this.blockingStub = BankServiceGrpc.newBlockingStub(channel);
		
		
	}
	
	@Test
	public void balanceTest() {
		for(int i=1;i<100;i++) {
			BalanceCheckRequest balanceCheckReq = BalanceCheckRequest.newBuilder().setAccountNumber(ThreadLocalRandom.current().nextInt(1,10)).build();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(balanceCheckReq);
			Balance balance= this.blockingStub.getBalance(balanceCheckReq);
			System.out.println("Recieved: "+balance.getAmount());
		}
		
	}
	
//	@Test
//	public void withdrawTest() {
//		this.blockingStub.withdraw(WithdrawRequest.newBuilder().setAccountNumber(4).setAmount(40).build())
//		.forEachRemaining(System.out::println);
//		
//	}

}
