package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import adts.Iterator;
import adts.ListADT;
import utilities.MyArrayList;

/**
 * Tests methods for MyArrayList.
 * @author Jordan Tejada, David D'Entremont, Huu Mai, Van Hien Tieu 
 * Oct 20, 2021
 */
class MyArrayListTest {

	private ListADT<String> list;
	private ListADT<String> list2;
	private ListADT<String> list3;
	private Object[] list4;
	private String t1, t2, t3, t4, t5, t6;
	
	
	/**
	 * @Before - Will execute the method before each test. 
	 * 		This method can prepare the test environment (e.g. read input data, initialize the class). 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception{
		list = new MyArrayList<>();
		list2 = new MyArrayList<>();
		list3 = null;
		list4 = new Object[10];
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
	 * Test method for {@link ListADT.MyArrayList#Size()}.
	 */
	@Test
	public void testSize() throws Exception{
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		assertEquals(5, list.size(), "Size method did not correctly ouput correct value");
		list.remove(0);
		assertEquals(4, list.size(), "Size method did not correctly ouput correct value");
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#Clear()}.
	 */
	@Test
	public void testClear(){
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		assertEquals(5, list.size(), "Size method did not correctly ouput correct value");
		list.clear();
		assertTrue(list.isEmpty(), "Clear method did not correctly clear the list");
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#Add(int, element)}.
	 */
	@Test
	public void testAddIndex(){
		list.add(0, t1);
		list.add(1, t2);
		list.add(2, t3);
		list.add(3, t4);
		list.add(4, t5);
		assertEquals(t1, list.get(0), "Add method with index did not add the value correctly");
		assertEquals(t2, list.get(1), "Add method with index did not add the value correctly");
		assertEquals(t5, list.get(4), "Add method with index did not add the value correctly");
		assertEquals(t4, list.get(3), "Add method with index did not add the value correctly");
		list.add(1, t3);
		list.add(3, t1);
		assertEquals(t3, list.get(1), "Add method with index did not add the value correctly");
		assertEquals(t1, list.get(3), "Add method with index did not add the value correctly");
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#Add(int, element)}.
	 * @throws IndexOutOfBoundsException
	 */
	@Test
	public void testAddIndexOut(){
		
		try {
			list.add(0, t1);
			list.add(1, t2);
			list.add(1000, t3);
			fail("Add index method failed to throw IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#Add(int, element)}.
	 * @throws IndexOutOfBoundsException
	 */
	@Test
	public void testAddIndexNull() {
		try {
			list.add(0, t1);
			list.add(1, t2);
			list.add(2, t6);
			fail("Add index method failed to throw NullPointerException");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	
	/**
	 * Test method for {@link ListADT.MyArrayList#Add(element)}.
	 */
	@Test
	public void testAdd(){
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		assertEquals(t1, list.get(0), "Add method did not add the value correctly");
		assertEquals(t2, list.get(1), "Add method did not add the value correctly");
		assertEquals(t5, list.get(4), "Add method did not add the value correctly");
		assertEquals(t4, list.get(3), "Add method did not add the value correctly");
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#Add(element)}.
	 * @throws IndexOutOfBoundsException
	 */
	@Test
	public void testAddNull() {
		try {
			list.add(t1);
			list.add(t2);
			list.add(t3);
			list.add(t6);
			fail("Add method failed to throw NullPointerException");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#addAkk(ListADT<? extends E>)}.
	 */
	@Test
	public void testAddAll() {
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		
		list.addAll(list2);
		
		assertEquals(t1, list.get(0), "addAll method did not add the first value correctly");
		assertEquals(5, list.size(), "addAll method did not add all the values correctly");
		assertEquals(t5, list.get(4), "addAll method did not add the last value correctly");
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#addAkk(ListADT<? extends E>)}.
	 * @throws NullPointerException
	 */
	@Test
	public void testAddAllNull() {
		
		try {
			list3.addAll(list2);
			fail("addAll method failed to throw NullPointerException");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#get(int)}.
	 */
	@Test
	public void testGet() {
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		
		assertEquals(t1, list.get(0), "get method did not get the first value correctly");
		assertEquals(t5, list.get(4), "get method did not get the last value correctly");
		assertEquals(t3, list.get(2), "get method did not get the middle value correctly");
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#get(int)}.
	 * @throws IndexOutOfBoundsException
	 */
	@Test
	public void testGetOut() {
		try {
			list.add(t1);
			list.add(t2);
			list.add(t3);
			list.add(t4);
			list.add(t5);
			
			list.get(1000);
			fail("get method failed to throw IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveInt() {
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		
		assertEquals(t1, list.remove(0), "remove int method did not remove the first value correctly");
		assertEquals(t5, list.remove(3), "remove int method did not remove the last value correctly");
		assertEquals(t3, list.remove(1), "remove int method did not remove the last value correctly");
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#remove(int)}.
	 * @throws IndexOutOfBoundsException
	 */
	@Test
	public void testRemoveIntOut() {
		try {
			list.add(t1);
			list.add(t2);
			list.add(t3);
			list.add(t4);
			list.add(t5);
			
			list.remove(1000);
			fail("remove method failed to throw IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#remove(elemenet)}.
	 */
	@Test
	public void testRemoveE() {
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		
		
		assertEquals(t1, list.remove(t1), "remove int method did not remove the first value correctly");
		assertEquals(t5, list.remove(t5), "remove int method did not remove the last value correctly");
		assertEquals(t3, list.remove(t3), "remove int method did not remove the last value correctly");
		assertEquals(null, list.remove("null"), "remove int method did not remove the last value correctly");
	}	
	
	/**
	 * Test method for {@link ListADT.MyArrayList#remove(element)}.
	 * @throws NullPointerException
	 */
	@Test
	public void testRemoveENull() {
		try {
			list.add(t1);
			list.add(t2);
			list.add(t3);
			list.add(t4);
			list.add(t5); 
			
			list.remove(t6);
			fail("remove method failed to throw NullPointerException");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#set(int, element)}.
	 */
	@Test
	public void testSet() {
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		
		list.set(0, t1);
		
		assertEquals(t1, list.get(0), "set int method did not replace the first value correctly");
		assertEquals(t3, list.get(1), "set int method did not replace the value correctly");
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#set(int, element)}.
	 * @throws NullPointerException
	 */
	@Test
	public void testSetNull() {
		try {
			list.add(t2);
			list.add(t3);
			list.add(t4);
			list.add(t5);
			
			list.set(0, t6);
			fail("set method failed to throw NullPointerException");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#set(int, element)}.
	 * @throws IndexOutOfBoundsException
	 */
	@Test
	public void testSetOut() {
		try {
			list.add(t2);
			list.add(t3);
			list.add(t4);
			list.add(t5);
			
			list.set(1000, t6);
			fail("set method failed to throw IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		list.add(t1);
		assertFalse(list.isEmpty(), "isEmpty method did not output right boolean vlue");
		list.clear();
		assertTrue(list.isEmpty(), "isEmpty method did not output right boolean vlue");
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#contain(element)}.
	 */
	@Test
	public void testContain() {
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		
		assertTrue(list.contains(t2), "contain method did not output right boolean value");
		assertFalse(list.contains("valuenothere"), "contain method did not output right boolean value");
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#contain(element)}.
	 * @throws NullPointerException
	 */
	@Test
	public void testContainNull() {
		try {
			list.add(t1);
			list.add(t2);
			list.add(t3);
			list.add(t4);
			list.add(t5);
			
			list.contains(t6);
			fail("contain method failed to throw NullPointerException");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#toArray(element)}.
	 */
	@Test
	public void testToArrayE() {
		
		String[] list5 = new String[5];
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		
		list5 = list.toArray(list5);
		
		assertEquals(list.get(0), list5[0], "toArray E[] method first value was not converted correctly");
		assertEquals(list.get(2), list5[2], "toArray E[] method mid value was not converted correctly");
		assertEquals(list.get(4), list5[4], "toArray E[] method last value was not converted correctly");
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#toArray(element)}.
	 * @throws NullPointerException
	 */
	@Test
	public void testToArrayENull() {
		try {
			String[] list5 = null;
			list.add(t1);
			list.add(t2);
			list.add(t3);
			list.add(t4);
			list.add(t5);
			
			list5 = list.toArray(list5);
			fail("toArray E[] method failed to throw NullPointerException");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link ListADT.MyArrayList#toArray(element)}.
	 */
	@Test
	public void testToArrayO() {
		
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		
		list4 = list.toArray();
		
		assertEquals(list.get(0), list4[0], "toArray Object[] method first value was not converted correctly");
		assertEquals(list.get(2), list4[2], "toArray Object[] method mid value was not converted correctly");
		assertEquals(list.get(4), list4[4], "toArray Object[] method last value was not converted correctly");
	}
	
	
	/**
	 * Test method for {@link ListADT.MyArrayList#iterator(element)}.
	 */
	@Test
	public void testIterator() {
		
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		
		Iterator<String> iter = list.iterator();
		assertTrue(iter.hasNext(), "iterator method value returned false with hasNext()");
		assertEquals(t2, iter.next(), "iterator method value did not match correctly with next() value");
	}
}
