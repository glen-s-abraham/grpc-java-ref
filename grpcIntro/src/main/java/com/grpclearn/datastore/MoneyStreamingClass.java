package com.grpclearn.datastore;

import com.grpclearn.models.Money;

import io.grpc.stub.StreamObserver;

public class MoneyStreamingClass implements StreamObserver<Money>{

	@Override
	public void onNext(Money money) {
		System.out.println("Recieved async: "+money.getValue());
		
	}

	@Override
	public void onError(Throwable t) {
		System.out.println(t.getMessage());
		
	}

	@Override
	public void onCompleted() {
		System.out.println("Server is done with this work");
	}

}
