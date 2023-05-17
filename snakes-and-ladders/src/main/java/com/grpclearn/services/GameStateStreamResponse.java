package com.grpclearn.services;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.grpclearn.models.Die;
import com.grpclearn.models.GameState;
import com.grpclearn.models.Player;

import io.grpc.netty.shaded.io.netty.util.internal.ThreadLocalRandom;
import io.grpc.stub.StreamObserver;

public class GameStateStreamResponse implements StreamObserver<GameState>{
	
	private CountDownLatch latch;
	private StreamObserver<Die> dieStreamObserver;
	
	public GameStateStreamResponse(CountDownLatch latch) {
		this.latch = latch;
	}
	
	public void roll() {
		int die = ThreadLocalRandom.current().nextInt(1,7);
		this.dieStreamObserver.onNext(Die.newBuilder().setValue(die).build());
	}
	
	public void setDieStreamObserver(StreamObserver<Die> dieStreamObserver) {
		this.dieStreamObserver = dieStreamObserver;
	}

	@Override
	public void onNext(GameState gameState) {
		List<Player> list = gameState.getPlayerList();
		list.forEach(p->System.out.println(p.getName()+":"+p.getPosition()));
		boolean anyWinner = list.stream().anyMatch(p->p.getPosition()==100);
		if(anyWinner) {
			System.out.println("Game over");
			this.dieStreamObserver.onCompleted();
		}else {
			this.roll();
		}
		System.out.println("-------------------------------------------------");
	}

	@Override
	public void onError(Throwable t) {
		this.latch.countDown();
	}

	@Override
	public void onCompleted() {
		this.latch.countDown();
	}
	
}
