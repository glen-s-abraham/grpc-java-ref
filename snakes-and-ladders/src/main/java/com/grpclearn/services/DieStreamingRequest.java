package com.grpclearn.services;

import com.grpclearn.db.SnakesAndLaddersMap;
import com.grpclearn.models.Die;
import com.grpclearn.models.GameState;
import com.grpclearn.models.Player;

import io.grpc.netty.shaded.io.netty.util.internal.ThreadLocalRandom;
import io.grpc.stub.StreamObserver;

public class DieStreamingRequest implements StreamObserver<Die>{
	
	private StreamObserver<GameState> responseObserver;
	private Player client;
	private Player server;
	
	private Player computePlayerPosition(Player player,int dieValue) {
		int position = player.getPosition() + dieValue;
		position = SnakesAndLaddersMap.getPosition(position);
		if(position<=100) {
			player = player.toBuilder().setPosition(position).build();
		}
		return player;
	}
	
	private GameState buildGameState() {
		return GameState.newBuilder().addPlayer(client).addPlayer(server).build();
	}
	

	public DieStreamingRequest(Player server,Player client,StreamObserver<GameState> responseObserver) {
		this.responseObserver = responseObserver;
		this.server = server;
		this.client = client;
	}

	@Override
	public void onNext(Die die) {
		this.client = computePlayerPosition(client, die.getValue());
		if(this.client.getPosition()!=100) {
			this.server = computePlayerPosition(server, ThreadLocalRandom.current().nextInt(1,7));
		}
		responseObserver.onNext(buildGameState());
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCompleted() {
		responseObserver.onCompleted();
	}
	
}
