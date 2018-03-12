package com.batsw.dependencyretriever;

import java.util.ArrayList;
 
public interface IDependencyRetriever {
	boolean add(String interfaceName, IInterface instance);
	<T extends IInterface> T get(String interfaceName);
	<T extends IInterface> ArrayList<T> getAll(String interfaceName);
	boolean remove(String type, IInterface value);
}
