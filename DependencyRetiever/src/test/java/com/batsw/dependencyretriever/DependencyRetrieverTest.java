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
	
	public class TestClass implements IInterface {
		public String getInterfaceType() {
			return TestClass.class.getName();
		}
		
		public boolean run()
		{
			return true;
		}
	}
	
	public class TestClass2 implements IInterface {
		public int value;
		public TestClass2() {
			value = 0;
		}
		public TestClass2(int value_) {
			value = value_;
		}
		public String getInterfaceType() {
			return TestClass2.class.getName();
		}
		
		public boolean run()
		{
			return true;
		}
	}
	public class DependencyRetrieverTestable extends DependencyRetriever
	{
		public <T extends IInterface> T convertFromObject(IInterface object, String type) {
			return super.convertFromObject(object, type);
		}
	}
	
	@Test
	public void testCannoCastInvalidElement() {
		DependencyRetrieverTestable retriever = new DependencyRetrieverTestable();
		TestClass t1 = new TestClass();
		String s = null;
		try {
			 s = retriever.convertFromObject(t1, TestClass.class.getName());	
		}
		catch (Exception e) {
			assertNull(s);
		}
		
	}
	
	public void testCannoCastInvalidElement1() {
		DependencyRetrieverTestable retriever = new DependencyRetrieverTestable();
		TestClass2 testClass = new TestClass2(7);
		String s = retriever.convertFromObject(testClass, String.class.getName());
		assertNull(s);
	}
	
	public void testCannoCastInvalidElement2() {
		DependencyRetrieverTestable retriever = new DependencyRetrieverTestable();
		TestClass2 testClass = new TestClass2(7);
		String s = retriever.convertFromObject(testClass, String.class.getName());
		assertNull(s);
	}
	DependencyRetriever retriever;

	@Test
	public void testCanCreateInstance() {
		assertNotNull(retriever);
	}
	
	@Test
	public void testCanAddElement() {
		TestClass2 test = new TestClass2();
		
		assertTrue(retriever.add(TestClass2.class.getName(), test));
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
		TestClass2 test = new TestClass2(444);
		retriever.add(TestClass2.class.getName(), test);
		TestClass2 value =  retriever.get(TestClass2.class.getName());
		
	assertEquals(test.value, value.value);
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
		TestClass2 value = retriever.get(TestClass2.class.getName());
		
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
