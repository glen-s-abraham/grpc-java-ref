package com.grpclear.client;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.grpclearn.datastore.TransferResponseObserver;
import com.grpclearn.models.TransferRequest;
import com.grpclearn.models.TransferServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.shaded.io.netty.util.internal.ThreadLocalRandom;
import io.grpc.stub.StreamObserver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransferClientTest {

	private TransferServiceGrpc.TransferServiceStub transferServiceStub;

	@BeforeAll
	public void setup() {
		ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:6566").usePlaintext().build();
		this.transferServiceStub = TransferServiceGrpc.newStub(channel);
	}

	@Test
	public void transfer() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		TransferResponseObserver res = new TransferResponseObserver(latch);
		StreamObserver<TransferRequest> reqStreamObserver = transferServiceStub.transfer(res);

		for (int i = 0; i < 100; i++) {
			TransferRequest req = TransferRequest.newBuilder()
					.setFromAccount(ThreadLocalRandom.current().nextInt(1, 10))
					.setToAccount(ThreadLocalRandom.current().nextInt(1, 10))
					.setAmount(ThreadLocalRandom.current().nextInt(1, 20)).build();
			reqStreamObserver.onNext(req);
		}
		reqStreamObserver.onCompleted();
		latch.await();
	}

}
