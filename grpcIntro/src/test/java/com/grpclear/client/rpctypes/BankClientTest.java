package com.grpclear.client.rpctypes;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.google.common.util.concurrent.Uninterruptibles;
import com.grpclearn.datastore.BalanceStreamObserver;
import com.grpclearn.datastore.MoneyStreamingClass;
import com.grpclearn.models.Balance;
import com.grpclearn.models.BalanceCheckRequest;
import com.grpclearn.models.BankServiceGrpc;
import com.grpclearn.models.DepositRequest;
import com.grpclearn.models.WithdrawRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankClientTest {
	private BankServiceGrpc.BankServiceBlockingStub blockingStub;
	private BankServiceGrpc.BankServiceStub  bankServiceStub;
	@BeforeAll
	public void setup() {
		ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:6566")
			.usePlaintext()
			.build();
		
		this.blockingStub = BankServiceGrpc.newBlockingStub(channel);
		this.bankServiceStub = BankServiceGrpc.newStub(channel);
		
	}
	
	@Test
	public void balanceTest() {
		BalanceCheckRequest balanceCheckReq = BalanceCheckRequest.newBuilder().setAccountNumber(1).build();
		System.out.println(balanceCheckReq);
		Balance balance= this.blockingStub.getBalance(balanceCheckReq);
		System.out.println("Recieved: "+balance.getAmount());
	}
	
	@Test
	public void withdrawTest() {
		this.blockingStub.withdraw(WithdrawRequest.newBuilder().setAccountNumber(4).setAmount(40).build())
		.forEachRemaining(System.out::println);
		
	}
	
	@Test
	public void withdrawAsyncTest() {
		this.bankServiceStub.withdraw(WithdrawRequest.newBuilder().setAccountNumber(4).setAmount(40).build(),new MoneyStreamingClass());
		Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
	}
	
	@Test
	public void cashStreamingRequest() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		StreamObserver<DepositRequest> streamObserver = this.bankServiceStub.cashDeposit(new BalanceStreamObserver(latch));
		for(int i=0;i<10;i++) {
			streamObserver.onNext(DepositRequest.newBuilder().setAccountNumber(8).setAmount(10).build());
		}
		streamObserver.onCompleted();
		latch.await();
		
	}
	
}
