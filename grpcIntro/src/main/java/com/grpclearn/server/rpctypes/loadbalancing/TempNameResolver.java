package com.grpclearn.server.rpctypes.loadbalancing;

import java.util.List;

import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;

public class TempNameResolver extends NameResolver{
	
	private final String service;
	
	public TempNameResolver(String service) {
		super();
		this.service = service;
	}

	@Override
	public String getServiceAuthority() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void refresh() {
		
	}
	
	@Override
	public void start(Listener2 listener) {
		List<EquivalentAddressGroup> addressGroups = ServiceRegistry.getInstances(this.service);
		ResolutionResult resolutionResult= ResolutionResult.newBuilder().setAddresses(addressGroups).build();
		listener.onResult(resolutionResult);
		super.start(listener);
	}

}
