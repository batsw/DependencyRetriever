package com.batsw.main;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.batsw.dependencyretriever.DependencyRetrieverTest;

public class MainTest {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(DependencyRetrieverTest.class);
		
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());
	 }

}
