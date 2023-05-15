package com.grpclearn.datastore;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountDatabase {
	private static final Map<Integer,Integer> data = IntStream
			.range(1, 10)
			.boxed()
			.collect(Collectors.toMap(Function.identity(),v->v*10)); 

	public static int getBalance(int accountId) {
		return data.get(accountId);
	}
	
	public static Integer addBalance(int accountId,int amount) {
		return data.computeIfPresent(accountId,(k,v)->v+amount );
	}
	
	public static Integer deductBalance(int accountId,int amount) {
		return data.computeIfPresent(accountId,(k,v)->v-amount );
	}
	
}
