package com.batsw.dependencyretriever;

import java.util.HashMap;
import java.util.Map;

public class DependencyRetriever implements IDependencyRetriever {
	public boolean addInstance(String interfaceName, Object instance){
		instances.put(interfaceName, instance);
		return true;
	}
	
	public Object get(String interfaceName){
		if (instances.containsKey(interfaceName)){
			return instances.get(interfaceName);
			
		}
		return null;
	}
	
	private Map<String, Object> instances = new HashMap<String, Object>();
}
