package com.batsw.dependencyretriever;

import java.util.HashMap;
import java.util.Map;

public class DependencyRetriever implements IDependencyRetriever {	
	private Map<String, Object> instances = new HashMap<String, Object>();
	
	public boolean addInstance(String type, Object instance){
		if (!isValidClass(type) || instance == null) {
			return false;
		}
			
		instances.put(type, instance);
		
		return true;
	}
	
	public Object get(String type){
		if (instances.containsKey(type)){
			return instances.get(type);	
		}
		
		return null;
	}
	
	public boolean removeInstance(String type, Object value)
	{
		return instances.remove(type, value);
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
