package com.batsw.dependencyretriever;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DependencyRetrieverTest {

	@Before
	public void setUp() throws Exception {
		retriever = new DependencyRetriever();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	public class TestClass {
		public boolean run()
		{
			return true;
		}
	}
	
	DependencyRetriever retriever;

	@Test
	public void testCanCreateInstance() {
		assertNotNull(retriever);
	}
	
	@Test
	public void testCanAddElement() {
		String test = "test";
		
		assertTrue(retriever.add(String.class.getName(), test));
	}
	
	@Test
	public void testCanAddMultipleElements() {
		TestClass testClass1 = new TestClass();
		TestClass testClass2 = new TestClass();
		
		retriever.add(TestClass.class.getName(), testClass1);
		
		assertTrue(retriever.add(TestClass.class.getName(), testClass2));
	}
	
	@Test
	public void testCannotAddNullElement(){
		
		assertFalse(retriever.add(TestClass.class.getName(), null));
	}
	
	@Test
	public void testCannotAddInstanceWithInvalidKey() {
		String invalidKey = "InvalidKey";
		TestClass test = new TestClass();
		
		assertFalse(retriever.add(invalidKey, test));
	}
	
	@Test
	public void testCanGetAddedInstance() {
		String test = "test";
		retriever.add(String.class.getName(), test);
		String value = (String) retriever.get(String.class.getName());
		
		assertEquals(test, value);
	}
	
	@Test
	public void testCanGetMultipleAddedInstances() {
		TestClass testClass1 = new TestClass();
		TestClass testClass2 = new TestClass();
		
		retriever.add(TestClass.class.getName(), testClass1);
		retriever.add(TestClass.class.getName(), testClass2);
		
		List<DependencyRetrieverTest.TestClass> instances = retriever.getAll(TestClass.class.getName());
		
		for (TestClass instance: instances)
		{
			assertTrue(instance.run());
		}
	}
	
	@Test
	public void testCannotGetMultipleAddedInstancesIfInvalidKey() {
		TestClass testClass1 = new TestClass();
		TestClass testClass2 = new TestClass();
		
		retriever.add(TestClass.class.getName(), testClass1);
		retriever.add(TestClass.class.getName(), testClass2);
		
		List<DependencyRetrieverTest.TestClass> instances = retriever.getAll("I am key");
		assertNull(instances);
	}
	
	@Test
	public void testNulIsReturnedIfInstanceIsNotRegistered(){
		String value = (String) retriever.get(String.class.getName());
		
		assertNull(value);
	}
	
	@Test
	public void testCanRemoveAllreadyRegisteredInstance() {
		TestClass testClass = new TestClass();
		retriever.add(TestClass.class.getName(), testClass);
		
		assertTrue(retriever.remove(TestClass.class.getName(), testClass));
	}
	
	@Test
	public void tesCannotRemoveInstanceThatWasNotRegistered() {
		TestClass testClass = new TestClass();
			
		assertFalse(retriever.remove(TestClass.class.getName(), testClass));
	}
}
