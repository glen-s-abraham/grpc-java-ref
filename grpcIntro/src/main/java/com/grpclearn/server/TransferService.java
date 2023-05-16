package com.grpclearn.server;

import com.grpclearn.datastore.TransferRequestObserver;
import com.grpclearn.models.TransferRequest;
import com.grpclearn.models.TransferResponse;
import com.grpclearn.models.TransferServiceGrpc.TransferServiceImplBase;

import io.grpc.stub.StreamObserver;

public class TransferService extends TransferServiceImplBase{
	
	@Override
	public StreamObserver<TransferRequest> transfer(StreamObserver<TransferResponse> responseObserver) {
		// TODO Auto-generated method stub
		return new TransferRequestObserver(responseObserver);
	}
}
