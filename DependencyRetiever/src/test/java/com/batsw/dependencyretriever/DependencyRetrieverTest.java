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
	public void testCanRetrieveElement() {
		String test = "test";
		retriever.addInstance(String.class.getName(), test);
		String value = (String) retriever.get(String.class.getName());
		assertEquals(test, value);
	}
}
