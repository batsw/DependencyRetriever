package com.batsw.dependencyretriever;

import java.util.ArrayList;
 
public interface IDependencyRetriever {
	boolean add(String interfaceName, Object instance);
	<T> T get(String interfaceName);
	<T> ArrayList<T> getAll(String interfaceName);
	boolean remove(String type, Object value);
}
