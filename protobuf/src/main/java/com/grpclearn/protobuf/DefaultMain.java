package com.grpclearn.protobuf;

import com.grpclelarn.models.Person;

public class DefaultMain {
	public static void main(String[] args) {
		Person person = Person.newBuilder().build();
		System.out.println("City:"+ person.getAddress().getCity());
		System.out.println(person.hasAddress());
		//scalar types like int32 string etc. will not be having has method.will get default values if not set
		//has attributes are assigned to other type inclusions.
	}
}
