package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import adts.Iterator;
import adts.ListADT;
import adts.StackADT;
import utilities.MyStack;

/**
 * Test methods for MyStack class
 * @author Jordan Tejada, David D'Entremont, Huu Mai, Van Hien Tieu
 * Oct 20, 2021
 */
class MyStackTests {
	
	private StackADT<String> list;
	private StackADT<String> list2;
	private Object[] list3;
	private String t1, t2, t3, t4, t5, t6;
	
	
	/**
	 * @Before - Will execute the method before each test. 
	 * 		This method can prepare the test environment (e.g. read input data, initialize the class). 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception{
		list = new MyStack<>();
		list2 = new MyStack<>();
		list3 = new Object[10];
		t1 = "Alpha";
		t2 = "Brave";
		t3 = "Charlie";
		t4 = "Delta";
		t5 = "Ethan";
		t6 = null;
	}
	
	/**
	 * @After - Will execute the method after each test. 
	 * 		This method can cleanup the test environment (e.g. delete temporary data, restore defaults). 
	 * @throws java.lang.Exception
	 */
	@AfterEach
	public void tearDown() throws Exception{
		list = null;
		list2 = null;
		list3 = null;
		t1 = null;
		t2 = null; 
		t3 = null;
		t4 = null;
		t5 = null;
		t6 = null;
	}
	
	/**
	 * Test method for {@link StackADT.MyStack#push(element)}.
	 */
	@Test
	public void testPush(){
		list.push(t1);
		list.push(t2);
		list.push(t3);
		list.push(t4);
		list.push(t5);
		
		assertEquals(3, list.search(t3));
		assertEquals(5, list.size());
		assertEquals(t5, list.peek());
	}
	
	/**
	 * Test method for {@link StackADT.MyStack#push(element)}.
	 * @throws NullPointerException
	 */
	@Test
	public void testPushNull(){
		try {
		list.push(t1);
		list.push(t6);
		fail("push method failed to throw NullPointerException");
		} catch(NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link StackADT.MyStack#pop()}.
	 */
	@Test
	public void testPop() {
		list.push(t1);
		list.push(t2);
		list.push(t3);
		list.pop();
		
		assertEquals(t2, list.peek());
		assertEquals(-1, list.search(t3));
		list.push(t4);
		list.push(list.pop());
		assertEquals(t4, list.peek());
	}
	
	/**
	 * Test method for {@link StackADT.MyStack#pop()}.
	 * @throws EmptyStackException
	 */
	@Test
	public void testPopEmpty() {
		try {
			list.pop();
			fail("pop method failed to throw EmptyStackException");
		} catch(EmptyStackException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link StackADT.MyStack#peek()}.
	 */
	@Test
	public void testPeek() {
		list.push(t1);
		list.push(t2);
		list.push(t2);
		list.push(t4);
		list.push(t5);
		
		assertEquals(t5, list.peek());
	}
	
	/**
	 * Test method for {@link StackADT.MyStack#peek()}.
	 * @throws EmptyStackException
	 */
	@Test 
	public void testPeekEmpty() {
		try {
			list.peek();
			fail("pop method failed to throw EmptyStackException");
		} catch(EmptyStackException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link StackADT.MyStack#clear()}.
	 */
	@Test
	public void testClear() {
		list.push(t1);
		list.push(t2);
		list.push(t3);
		list.push(t4);
		list.push(t5);
		list.clear();
		
		assertEquals(0, list.size());
	}
	
	/**
	 * Test method for {@link StackADT.MyStack#isEmpty()}.
	 */
	@Test 
	public void testIsEmpty() {
		assertEquals(true, list.isEmpty());
		list.push(t1);
		assertEquals(false, list.isEmpty());
	}
	
	/**
	 * Test method for {@link StackADT.MyStack#size()}.
	 */
	@Test
	public void testSize() {
		list.push(t1);
		list.push(t2);
		list.push(t3);
		
		assertEquals(3, list.size());
	}
	
	/**
	 * Test method for {@link StackADT.MyStack#equals()}.
	 */
	@Test
	public void testEquals() {
		list.push(t1);
		list.push(t2);
		list.push(t3);
		list.push(t4);
		list.push(t5);
		
		list2.push(t1);
		list2.push(t2);
		list2.push(t3);
		list2.push(t4);
		list2.push(t5);
		
		assertEquals(true, list.equals(list2));
		
		list2.clear();
		list2.push(t1);
		list2.push(t2);
		assertEquals(false, list.equals(list2));
		
		list2.clear();
		list2.push(t5);
		list2.push(t4);
		list2.push(t3);
		list2.push(t2);
		list2.push(t1);
		assertEquals(false, list.equals(list2));
	}
	
	/**
	 * Test method for {@link StackADT.MyStack#toArray()}.
	 */
	@Test
	public void testToArrayO() {
		
		list.push(t1);
		list.push(t2);
		list.push(t3);
		list.push(t4);
		list.push(t5);
		
		list3= list.toArray();
		
		assertEquals(list.peek(), list3[0]);
		list.pop();
		list.pop();
		list.pop();
		list.pop();
		assertEquals(list.pop(), list3[4]);
	}
	
	/**
	 * Test method for {@link StackADT.MyStack#toArray()}.
	 */
	@Test
	public void testToArrayE() {
		
		String[] list4 = new String[10];
		list.push(t1);
		list.push(t2);
		list.push(t3);
		list.push(t4);
		list.push(t5);
		
		list4 = list.toArray(list4);
		
		assertEquals(list.peek(), list4[0]);
		list.pop();
		list.pop();
		list.pop();
		list.pop();
		assertEquals(list.pop(), list4[4]);
	}
	
	/**
	 * Test method for {@link StackADT.MyStack#toArray()}.
	 * @throws NullPointerException
	 */
	@Test
	public void testToArrayENull() {
		try {
			String[] list4 = null;
			list.push(t1);
			list.push(t2);
			list.push(t3);
			list.push(t4);
			list.push(t5);
			
			list4 = list.toArray(list4);
			fail("toArray E[] method failed to throw NullPointerException");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	/**
	 * Test method for {@link ListADT.MyArrayList#iterator(element)}.
	 */
	@Test
	public void testIterator() {
		
		list.push(t1);
		list.push(t2);
		list.push(t3);
		list.push(t4);
		list.push(t5);
		
		Iterator<String> iter = list.iterator();
		assertTrue(iter.hasNext(), "iterator method value returned false with hasNext()");
		assertEquals(t2, iter.next(), "iterator method value did not match correctly with next() value");
	}
	
	/**
	 * Test method for {@link StackADT.MyStack#search()}.
	 */
	@Test
	public void testSearch() {
		list.push(t1);
		list.push(t2);
		list.push(t3);
		list.push(t4);
		list.push(t5);
		
		assertEquals(1, list.search(t5));
		assertEquals(2, list.search(t4));
		assertEquals(5, list.search(t1));
	}
	
	/**
	 * Test method for {@link StackADT.MyStack#search()}.
	 * @throws NullPointerException
	 */
	@Test
	public void testSearchNull() {
		try {
			list.push(t1);
			list.push(t2);
			list.push(t3);
			list.push(t4);
			list.push(t5);
			
			list.search(t6);
			fail("search method failed to throw NullPointerException");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link StackADT.MyStack#contains()}.
	 */
	@Test
	public void testContains() {
		list.push(t1);
		list.push(t2);
		list.push(t3);
		list.push(t4);
		list.push(t5);
		
		assertEquals(true, list.contains(t3));
		assertEquals(false, list.contains("none"));
	}
	
	/**
	 * Test method for {@link StackADT.MyStack#contains()}.
	 * @throws NullPointerException
	 */
	@Test
	public void testContainsNull() {
		try {
			list.push(t1);
			list.push(t2);
			list.push(t3);
			list.push(t4);
			list.push(t5);
			
			list.contains(t6);
			fail("contains method failed to throw NullPointerException");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
}
