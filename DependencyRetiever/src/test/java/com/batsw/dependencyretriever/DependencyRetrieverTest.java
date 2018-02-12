package com.batsw.dependencyretriever;

import static org.junit.Assert.*;

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
	
	public class TestClass
	{
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
		
		assertTrue(retriever.addInstance(String.class.getName(), test));
	}
	
	@Test
	public void testCanRetrieveAllreadyAddedInstance() {
		String test = "test";
		retriever.addInstance(String.class.getName(), test);
		String value = (String) retriever.get(String.class.getName());
		
		assertEquals(test, value);
	}
	
	@Test
	public void testNulIsReturnedIfInstanceIsNotRegistered(){
		String value = (String) retriever.get(String.class.getName());
		
		assertNull(value);
	}
	
	@Test
	public void testCannotRegisterInstanceWithInvalidKey() {
		String invalidKey = "InvalidKey";
		TestClass test = new TestClass();
		
		assertFalse(retriever.addInstance(invalidKey, test));
	}
	
	@Test
	public void testCannotAddNullInstance() {
		String key = TestClass.class.getName();
		TestClass test = new TestClass();
		
		assertFalse(retriever.addInstance(key, null));
	}

	@Test
	public void testCanRemoveAllreadyRegisteredInstance() {
		TestClass testClass = new TestClass();
		retriever.addInstance(TestClass.class.getName(), testClass);
		
		assertTrue(retriever.removeInstance(TestClass.class.getName(), testClass));
	}
	
	@Test
	public void tesCannotRemoveInstanceThatWasNotRegistered() {
		TestClass testClass = new TestClass();
			
		assertFalse(retriever.removeInstance(TestClass.class.getName(), testClass));
	}
}
