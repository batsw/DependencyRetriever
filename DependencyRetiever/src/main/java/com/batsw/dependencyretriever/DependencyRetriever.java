package com.batsw.dependencyretriever;

import java.util.HashMap;
import java.util.Map;

public class DependencyRetriever implements IDependencyRetriever {	
	private Map<String, Object> instances = new HashMap<String, Object>();
	
	public boolean addInstance(String interfaceName, Object instance){
		if (!isValidClass(interfaceName) || instance == null) {
			return false;
		}
			
		instances.put(interfaceName, instance);
		
		return true;
	}
	
	public Object get(String interfaceName){
		if (instances.containsKey(interfaceName)){
			return instances.get(interfaceName);	
		}
		
		return null;
	}
	
	protected boolean isValidClass(String type)
	{
		try {
			Class cls = Class.forName(type);
			return true;
			
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
	
}
