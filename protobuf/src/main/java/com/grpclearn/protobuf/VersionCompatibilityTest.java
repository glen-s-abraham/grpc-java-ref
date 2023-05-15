package com.grpclearn.protobuf;

import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.grpclelarn.models.Television;
import com.grpclelarn.models.Type;

public class VersionCompatibilityTest {

	public static void main(String[] args) throws IOException {
		
		
		
		Path path = Paths.get("tv-v1");
		Path path2 = Paths.get("tv-v2");
		
		Television sony = Television.newBuilder().setBrand("sony").setModel(2015).setType(Type.UHD).build();
		
		Files.write(path2,sony.toByteArray());
		
		byte[] bytes=Files.readAllBytes(path2);
		System.out.println(Television.parseFrom(bytes));
		

	}

}
