package com.grpclearn.datastore;

import com.grpclearn.models.Account;
import com.grpclearn.models.TransferRequest;
import com.grpclearn.models.TransferResponse;
import com.grpclearn.models.TransferStatus;

import io.grpc.stub.StreamObserver;

public class TransferRequestObserver implements StreamObserver<TransferRequest> {

	private StreamObserver<TransferResponse> transferResObserver;

	public TransferRequestObserver(StreamObserver<TransferResponse> transferResObserver) {
		this.transferResObserver = transferResObserver;
	}

	@Override
	public void onNext(TransferRequest req) {
		int fromAccount = req.getFromAccount();
		int toAccount = req.getToAccount();
		int amount = req.getAmount();

		int balance = AccountDatabase.getBalance(fromAccount);

		TransferStatus status = TransferStatus.FAILED;
		if (balance >= amount && fromAccount != toAccount) {
			AccountDatabase.deductBalance(fromAccount, amount);
			AccountDatabase.addBalance(toAccount, amount);
			status = TransferStatus.SUCCESS;
		}
		TransferResponse res = TransferResponse.newBuilder().setStatus(status)
				.addAccount(Account.newBuilder().setAccountNumber(fromAccount)
						.setAmount(AccountDatabase.getBalance(fromAccount)))
				.addAccount(Account.newBuilder().setAccountNumber(toAccount)
						.setAmount(AccountDatabase.getBalance(toAccount)))
				.build();
		transferResObserver.onNext(res);

	}

	@Override
	public void onError(Throwable t) {
		System.err.println(t.getMessage());

	}

	@Override
	public void onCompleted() {
		AccountDatabase.printAccDetails();
		transferResObserver.onCompleted();
	}

}
