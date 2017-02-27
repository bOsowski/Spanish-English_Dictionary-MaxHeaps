package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.MaxHeap;

public class testHeap {

	private MaxHeap<String> heap = new MaxHeap<String>();
	
	@Before
	public void setUp() throws Exception {
		heap.add("Z");
		heap.add("A");
		heap.add("B");
		heap.add("C");
	}

	@After
	public void tearDown() throws Exception {
		heap.clear();
	}

	@Test
	public void test() {
		assertEquals("Z", heap.getMax());
		heap.removeMax();
		heap.removeMax();
		heap.removeMax();
		assertEquals("A", heap.getMax());
	}

}
