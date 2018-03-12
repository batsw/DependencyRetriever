package com.batsw.dependencyretriever;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DependencyRetriever implements IDependencyRetriever {	
	private Map< String, ArrayList<IInterface> > instances = new HashMap <String, ArrayList<IInterface> >();
	
	public boolean add(String type, IInterface instance){
		if (!isValidClass(type) || instance == null) {
			return false;
		}
		
		if (!isValueInMap(type)) {
			ArrayList<IInterface> array = new ArrayList<IInterface>();
			instances.put(type, array);
		}
		
		if (instances.get(type).add(instance))
		{
			return true;
		}
		
		return false;
	}
	
	public <T extends IInterface> T get(String type){
		if (instances.containsKey(type)){
			IInterface obj = instances.get(type).get(0);
			T instance =  convertFromObject(obj, type);	
			return instance;
		}
		return null;
	}
	
	
	public<T extends IInterface> ArrayList<T> getAll(String type){		
		if (!instances.containsKey(type)){
			return null; 
		}
		ArrayList<IInterface> retrievedInstance = instances.get(type);
		ArrayList<T> instances = convertFromArrayToTypeArray(retrievedInstance, type);
		return instances;
	}
	
	public boolean remove(String type, IInterface value)
	{
		if (isValueInMap(type)) {
			ArrayList<IInterface> array = instances.get(type);
			return array.remove(value);
		}
		
		return false;
	}
	
	protected<T extends IInterface> ArrayList<T> convertFromArrayToTypeArray(ArrayList<IInterface> objects, String type)
	{
		ArrayList<T> implementations = new ArrayList<T>();
		for( IInterface obj: objects) {
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
	
	protected <T extends IInterface> T convertFromObject(IInterface object, String type) {
		try{
			@SuppressWarnings("unchecked")
			T instance = (T)object;	
			if (instance.getInterfaceType() == type)
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
