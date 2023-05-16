package com.grpclearn.datastore;

import com.grpclearn.models.Balance;
import com.grpclearn.models.DepositRequest;

import io.grpc.stub.StreamObserver;

public class StreamingDepositReq implements StreamObserver<DepositRequest>{
	
	private StreamObserver<Balance> balanceStreamObserver;
	private int accBalance; 
	
	public StreamingDepositReq(StreamObserver<Balance> balanceStreamObserver) {
		this.balanceStreamObserver = balanceStreamObserver;
	}
	
	@Override
	public void onNext(DepositRequest depositRequest) {
		int account_number = depositRequest.getAccountNumber();
		int amount = depositRequest.getAmount();
		this.accBalance = AccountDatabase.addBalance(account_number, amount);
		
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCompleted() {
		Balance balance = Balance.newBuilder().setAmount(accBalance).build();
		this.balanceStreamObserver.onNext(balance);
		this.balanceStreamObserver.onCompleted();
	}

}
