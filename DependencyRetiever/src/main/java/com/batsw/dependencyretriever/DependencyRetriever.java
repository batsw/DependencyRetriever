package com.batsw.dependencyretriever;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DependencyRetriever implements IDependencyRetriever {	
	private Map< String, ArrayList<Object> > instances = new HashMap < String, ArrayList<Object> >();
	
	public boolean add(String type, Object instance){
		if (!isValidClass(type) || instance == null) {
			return false;
		}
		
		if (!isValueInMap(type)) {
			ArrayList<Object> array = new ArrayList<Object>();
			instances.put(type, array);
		}
		
		if (instances.get(type).add(instance))
		{
			return true;
		}
		
		return false;
	}
	
	public <T> T get(String type){
		if (instances.containsKey(type)){
			Object obj = instances.get(type).get(0);
			T instance =  convertFromObject(obj, type);	
			return instance;
		}
		return null;
	}
	
	
	public<T> ArrayList<T> getAll(String type){		
		if (!instances.containsKey(type)){
			return null; 
		}
		ArrayList<Object> retrievedInstance = instances.get(type);
		ArrayList<T> instances = convertFromObjectArrayToTypeArray(retrievedInstance, type);
		return instances;
	}
	
	public boolean remove(String type, Object value)
	{
		if (isValueInMap(type)) {
			ArrayList<Object> array = instances.get(type);
			return array.remove(value);
		}
		
		return false;
	}
	
	protected<T> ArrayList<T> convertFromObjectArrayToTypeArray(ArrayList<Object> objects, String type)
	{
		ArrayList<T> implementations = new ArrayList<T>();
		for( Object obj: objects) {
			T instance = convertFromObject(obj, type);
			if (instance ==  null)
			{
				continue;
			}
			implementations.add(instance);
		}
		if (implementations.isEmpty())
		{
			return null;
		}
		return implementations;
	}
	
	protected <T> T convertFromObject(Object object, String type) {
		try{
			@SuppressWarnings("unchecked")
			T instance = (T)object;	
			if (instance.getClass().getName() == type)
			{
				return instance;
			}
			return null;
				
		}catch(Exception e){
			return null;
		}
	}
	
	protected boolean isValueInMap(String type) {
		if (instances.containsKey(type)){
			return true;	
		}
		
		return false;
	}
	protected boolean isValidClass(String type) {
		try {
			Class cls = Class.forName(type);
			return true;	
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
	
}
