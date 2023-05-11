package com.grpclearn.protobuf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.grpclearn.json.JPerson;
import com.grpclelarn.models.Person;

public class PerfTest {
	public static void main(String[] args) {

		JPerson person = new JPerson();
		person.setName("sam");
		person.setAge(10);
		ObjectMapper mapper = new ObjectMapper();

		Runnable json = () -> {
			try {
				byte[] bytes = mapper.writeValueAsBytes(person);
				mapper.readValue(bytes, JPerson.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

		Runnable protobuf = () -> {
			try {
				Person person2 = Person.newBuilder().setName("sam").setAge(10).build();
				byte[] bytes = person2.toByteArray();
				Person person2Parsed = Person.parseFrom(bytes);
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};

		for (int i = 0; i < 5; i++) {
			runPerformanceTest(json, "JSON");
			runPerformanceTest(protobuf, "PROTO");
		}

	}

	private static void runPerformanceTest(Runnable runnable, String method) {
		long time1 = System.currentTimeMillis();
		for (int i = 0; i < 5000000; i++) {
			runnable.run();
		}
		long time2 = System.currentTimeMillis();
		System.out.println(method + ":" + (time2 - time1) + "ms");
	}
}
