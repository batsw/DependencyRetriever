package com.batsw.dependencyretriever;

public interface IDependencyRetriever {
	boolean addInstance(String interfaceName, Object instance);
	Object get(String interfaceName);

}
