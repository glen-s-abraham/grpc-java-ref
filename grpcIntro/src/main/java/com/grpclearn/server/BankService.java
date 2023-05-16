package com.grpclearn.server;

import com.grpclearn.datastore.AccountDatabase;
import com.grpclearn.datastore.StreamingDepositReq;
import com.grpclearn.models.Balance;
import com.grpclearn.models.BalanceCheckRequest;
import com.grpclearn.models.DepositRequest;
import com.grpclearn.models.Money;
import com.grpclearn.models.WithdrawRequest;
import com.grpclearn.models.BankServiceGrpc.BankServiceImplBase;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class BankService extends BankServiceImplBase{

	@Override
	public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {
		int accountNumber=request.getAccountNumber();
		Balance balance=Balance.newBuilder().setAmount(AccountDatabase.getBalance(accountNumber)).build();
		responseObserver.onNext(balance);
		responseObserver.onCompleted();
	}
	
	@Override
	public void withdraw(WithdrawRequest request, StreamObserver<Money> responseObserver) {
		int accountNUmber = request.getAccountNumber();
		int amount = request.getAmount();
		int balance = AccountDatabase.getBalance(accountNUmber);
		
		if(balance<amount) {
			Status status = Status.FAILED_PRECONDITION.withDescription("No enough money. You have only "+balance);
			responseObserver.onError(status.asRuntimeException());
		}
		
		//all the validations passed successfully
		for(int i=0;i<(amount/10);i++) {
			Money money = Money.newBuilder().setValue(10).build();
			responseObserver.onNext(money);
			AccountDatabase.deductBalance(accountNUmber, 10);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		responseObserver.onCompleted();
		
	}
	
	@Override
	public StreamObserver<DepositRequest> cashDeposit(StreamObserver<Balance> responseObserver) {
		return new StreamingDepositReq(responseObserver);
	}
}
