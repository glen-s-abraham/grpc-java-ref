package com.grpclearn.protobuf;

import com.grpclelarn.models.Car;
import com.grpclelarn.models.Dealer;

public class MapDemo {

	public static void main(String[] args) {
		Car accord = Car.newBuilder()
				.setMake("Honda")
				.setModel("Accord")
				.setYear(2020)
				.build();
		
		Car civic = Car.newBuilder()
				.setMake("Honda")
				.setModel("Civic")
				.setYear(2005)
				.build();
		
		Dealer dealer = Dealer.newBuilder().putModel(2020, accord).putModel(2005, civic).build();
		System.out.println(dealer);
		System.out.println(dealer.getModelCount());

	}

}
