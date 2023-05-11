package com.grpclearn.protobuf;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;


import com.grpclelarn.models.Person;

public class PersonMain {

	public static void main(String[] args) throws IOException {
		
		Person sam = Person.newBuilder()
			.setName("sam")
			.setAge(10)
			.build();
		
		Path path=Paths.get("sam.ser");
		
		Files.write(path, sam.toByteArray());
		
		byte[] bytes = Files.readAllBytes(path);
		Person newSam = Person.parseFrom(bytes);
		
		System.out.println(newSam);
	}

}
