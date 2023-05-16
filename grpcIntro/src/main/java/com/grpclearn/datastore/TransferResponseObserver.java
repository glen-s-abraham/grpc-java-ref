package com.grpclearn.datastore;

import java.util.concurrent.CountDownLatch;

import com.grpclearn.models.TransferResponse;

import io.grpc.stub.StreamObserver;

public class TransferResponseObserver implements StreamObserver<TransferResponse> {

	private CountDownLatch latch;

	public TransferResponseObserver(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void onNext(TransferResponse res) {
		System.out.println("Status :" + res.getStatus());
		res.getAccountList().stream().map(acc -> acc.getAccountNumber() + ":" + acc.getAmount())
				.forEach(System.out::println);
		System.out.println("_____________________________________");
	}

	@Override
	public void onError(Throwable t) {
		System.err.println(t.getMessage());
		this.latch.countDown();
	}

	@Override
	public void onCompleted() {
		this.latch.countDown();
	}

}
