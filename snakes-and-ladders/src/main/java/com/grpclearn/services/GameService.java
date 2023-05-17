package com.grpclearn.services;

import com.grpclearn.models.Die;
import com.grpclearn.models.GameState;
import com.grpclearn.models.Player;
import com.grpclearn.models.GameServiceGrpc.GameServiceImplBase;

import io.grpc.stub.StreamObserver;

public class GameService extends GameServiceImplBase{
	
	@Override
	public StreamObserver<Die> roll(StreamObserver<GameState> responseObserver) {
		
		Player client = Player.newBuilder().setName("client").setPosition(0).build();
		Player server = Player.newBuilder().setName("server").setPosition(0).build();
		
		return new DieStreamingRequest(server,client,responseObserver);
	}
}
