package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import adts.Iterator;
import adts.QueueADT;
import exceptions.EmptyQueueException;
import utilities.MyQueue;

/**
 * Test methods for MyQueue class
 * @author Jordan Tejada, David D'Entremont, Huu Mai, Van Hien Tieu 
 * Oct 23, 2021
 */

class MyQueueTests {

	private QueueADT<String> list;
	private QueueADT<String> list2;
	private Object[] list3;
	private String t1, t2, t3, t4, t5, t6;
	
	
	/**
	 * @Before - Will execute the method before each test. 
	 * 		This method can prepare the test environment (e.g. read input data, initialize the class). 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception{
		list = new MyQueue<>();
		list2 = new MyQueue<>();
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
	 * Test method for {@link QueueADT.MyStack#enqueue(element)}.
	 * @throws EmptyQueueException 
	 */
	@Test
	public void testEnqueue() throws EmptyQueueException{
		list.enqueue(t1);
		list.enqueue(t2);
		list.enqueue(t3);
		list.enqueue(t4);
		list.enqueue(t5);
		
		assertEquals(5, list.size());
		assertEquals(t1, list.peek());
	}
	
	/**
	 * Test method for {@link QueueADT.MyStack#enqueue(element)}.
	 * @throws NullPointerException
	 */
	@Test
	public void testEnqueueNull(){
		try {
		list.enqueue(t1);
		list.enqueue(t6);
		fail("push method failed to throw NullPointerException");
		} catch(NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link QueueADT.MyStack#dequeue()}.
	 * @throws EmptyQueueException 
	 */
	@Test
	public void testDequeue() throws EmptyQueueException {
		list.enqueue(t1);
		list.enqueue(t2);
		list.enqueue(t3);
		list.enqueue(t4);
		list.enqueue(t5);
		
		assertEquals(t1, list.peek());
		assertEquals(t1, list.dequeue());
		assertEquals(t2, list.dequeue());
		assertEquals(t3, list.dequeue());
	}
	
	/**
	 * Test method for {@link QueueADT.MyStack#pop()}.
	 * @throws EmptyQueueException 
	 */
	@Test
	public void testDequeueEmpty() throws EmptyQueueException {
		try {
			list.dequeue();
			fail("pop method failed to throw EmptyQueueException");
		} catch(EmptyQueueException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link QueueADT.MyStack#peek()}.
	 * @throws EmptyQueueException 
	 */
	@Test
	public void testPeek() throws EmptyQueueException {
		list.enqueue(t1);
		list.enqueue(t2);
		list.enqueue(t3);
		list.enqueue(t4);
		list.enqueue(t5);
		
		assertEquals(t1, list.peek());
	}
	
	/**
	 * Test method for {@link QueueADT.MyStack#peek()}.
	 * @throws EmptyQueueException 
	 */
	@Test 
	public void testPeekEmpty() throws EmptyQueueException {
		try {
			list.peek();
			fail("pop method failed to throw EmptyQueueException");
		} catch(EmptyQueueException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link QueueADT.MyStack#equals()}.
	 */
	@Test
	public void testEquals() {
		list.enqueue(t1);
		list.enqueue(t2);
		list.enqueue(t3);
		list.enqueue(t4);
		list.enqueue(t5);
		
		list2.enqueue(t1);
		list2.enqueue(t2);
		list2.enqueue(t3);
		list2.enqueue(t4);
		list2.enqueue(t5);
		
		assertEquals(true, list.equals(list2));
		
		list2.dequeueAll();
		list2.enqueue(t1);
		list2.enqueue(t2);
		assertEquals(false, list.equals(list2));
		
		list2.dequeueAll();
		list2.enqueue(t5);
		list2.enqueue(t4);
		list2.enqueue(t3);
		list2.enqueue(t2);
		list2.enqueue(t1);
		assertEquals(false, list.equals(list2));
	}
	
	/**
	 * Test method for {@link QueueADT.MyStack#toArray()}.
	 * @throws EmptyQueueException 
	 */
	@Test
	public void testToArrayO() throws EmptyQueueException {
		
		list.enqueue(t1);
		list.enqueue(t2);
		list.enqueue(t3);
		list.enqueue(t4);
		list.enqueue(t5);
		
		list3= list.toArray();
		
		assertEquals(list.peek(), list3[0]);
		list.dequeue();
		list.dequeue();
		list.dequeue();
		list.dequeue();
		assertEquals(list.dequeue(), list3[4]);
	}
	
	/**
	 * Test method for {@link QueueADT.MyStack#toArray(E[] toHold)}.
	 * @throws EmptyQueueException 
	 */
	@Test
	public void testToArrayE() throws EmptyQueueException {
		
		String[] list4 = new String[10];
		list.enqueue(t1);
		list.enqueue(t2);
		list.enqueue(t3);
		list.enqueue(t4);
		list.enqueue(t5);
		
		list4 = list.toArray(list4);
		
		assertEquals(list.peek(), list4[0]);
		list.dequeue();
		list.dequeue();
		list.dequeue();
		list.dequeue();
		assertEquals(list.dequeue(), list4[4]);
	}
	
	/**
	 * Test method for {@link QueueADT.MyStack#toArray(E[] toHold)}.
	 * @throws NullPointerException
	 */
	@Test
	public void testToArrayENull() {
		try {
			String[] list4 = null;
			list.enqueue(t1);
			list.enqueue(t2);
			list.enqueue(t3);
			list.enqueue(t4);
			list.enqueue(t5);
			
			list4 = list.toArray(list4);
			fail("toArray E[] method failed to throw NullPointerException");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testToIterator() {
			list.enqueue(t1);
			list.enqueue(t2);
			list.enqueue(t3);
			list.enqueue(t4);
			list.enqueue(t5);
			
			Iterator<String> iter = list.iterator();
			assertTrue(iter.hasNext(), "iterator method value returned false with hasNext()");
			assertEquals(t2, iter.next(), "iterator method value did not match correctly with next() value");
	}
	/**
	 * Test method for {@link QueueADT.MyStack#size()}.
	 */
	@Test
	public void testSize() {
		list.enqueue(t1);
		list.enqueue(t2);
		list.enqueue(t3);
		
		assertEquals(3, list.size());
	}
	
	/**
	 * Test method for {@link QueueADT.MyStack#isEmpty()}.
	 */
	@Test 
	public void testIsEmpty() {
		assertEquals(true, list.isEmpty());
		list.enqueue(t1);
		assertEquals(false, list.isEmpty());
	}
	
	/**
	 * Test method for {@link QueueADT.MyStack#dequeueAll()}.
	 */
	@Test
	public void testDequeueAll() {
		list.enqueue(t1);
		list.enqueue(t2);
		list.enqueue(t3);
		list.enqueue(t4);
		list.enqueue(t5);
		list.dequeueAll();
		
		assertEquals(0, list.size());
	}
}
