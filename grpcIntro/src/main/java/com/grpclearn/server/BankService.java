package com.grpclearn.server;

import com.grpclearn.datastore.AccountDatabase;
import com.grpclearn.models.Balance;
import com.grpclearn.models.BalanceCheckRequest;
import com.grpclearn.models.BankServiceGrpc.BankServiceImplBase;

import io.grpc.stub.StreamObserver;

public class BankService extends BankServiceImplBase{

	@Override
	public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {
		int accountNumber=request.getAccountNumber();
		Balance balance=Balance.newBuilder().setAmount(AccountDatabase.getBalance(accountNumber)).build();
		responseObserver.onNext(balance);
		responseObserver.onCompleted();
	}
}
