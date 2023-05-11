package com.grpclearn.protobuf;

import java.util.ArrayList;
import java.util.List;

import com.grpclelarn.models.Address;
import com.grpclelarn.models.Car;
import com.grpclelarn.models.Person;

public class CompositionDemo {

	public static void main(String[] args) {
		Address address = Address.newBuilder()
				.setPostbox(123)
				.setStreet("main street")
				.setCity("Atlanta")
				.build();
		
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
		
		List<Car>cars= new ArrayList<Car>(); 
		cars.add(civic);
		cars.add(accord);
		
		Person person = Person.newBuilder()
				.setName("sam")
				.setAge(22)
				.setAddress(address)
				.addAllCar(cars)
				.build();
		
		System.out.println(person);
		

	}

}
