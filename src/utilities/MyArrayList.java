package utilities;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

import adts.Iterator;
import adts.ListADT;

/**
 * A class that implements a list of objects by using an array.
 * Entries in a list have positions that begin with 1.
 * Duplicate entries are allowed.
 * @author Jordan Tejada, David D'Entremont, Huu Mai, Van Hien Tieu
 *
 * @param <E> Oct 20, 2021
 */
public class MyArrayList<E> implements ListADT<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3578858266903529815L;

	private E[] array;
	private int size;
	
	private static final int DEFAULT_CAPACITY = 25;
	
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		E[] tempArray = (E[])new Object[DEFAULT_CAPACITY + 1];
		array = tempArray;
		size = 0;
	}
	
	@SuppressWarnings("unchecked")
	public MyArrayList(int initialCapacity) {
		E[] tempArray = (E[])new Object[initialCapacity + 1];
		array = tempArray;
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			array[i] = null;
		}
		size = 0;
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {

		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index must be between 0 and " + size());
		if (toAdd == null)
			throw new NullPointerException("Cannot add null element.");
		if (array.length - size() == 1)
			expandSize();
		if (index == size()) {
			array[size()] = toAdd;
			size++;
			return true;
		}
		for(int i = index; i <= size(); i++) {
			array[i + 1] = array[i];
		}
		array[index] = toAdd;
		size++;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private void expandSize() {
		int doubleSize = array.length * 2;
		E[] newArray = (E[]) new Object[doubleSize];
		for(int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if (toAdd == null)
			throw new NullPointerException("Cannot add null element.");
		size++;
		array[(size - 1)] = toAdd;
		return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		for (int i = 0; i < toAdd.size(); i++) {
			if (array.length - size() == 1)
				expandSize();
			array[size] = toAdd.get(i);
			size++;
		}
		return true;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index must be between 0 and " + size());
		return array[index];
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index must be between 0 and " + size());
		E removedElement = array[index];
		for (int i = index; i < size() - 1; i++) {
			array[i] = array[i + 1];
		}
		size--;
		return removedElement;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {
		if (toRemove == null)
			throw new NullPointerException("Cannot remove null element.");
		if (!contains(toRemove)) {
			return null;
		}
		
		int index = 0;
		
		for(int i = 0; i < size(); i++) {
				if (array[i].equals(toRemove)) {
					index = i;
				}
		}
		E removed = array[index];
		for(int i = index; i < size - 1; i++) {
			array[i] = array[i + 1];
		}
		size--;
		return removed;	
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index must be between 0 and " + size());
		if (toChange == null)
			throw new NullPointerException("Cannot change null element.");
		E replaced = array[index];
		array[index] = toChange;
		return replaced;
	}

	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null)
			throw new NullPointerException("Cannot find null element.");
		for (int i = 0; i < size(); i++) {
			if (array[i] == toFind) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		if(toHold.length < size()) {
			toHold = (E[]) Array.newInstance(toHold.getClass().getComponentType(), size);
		}
		int j = size() - 1;
		for(int i = 0; i < size(); i++) {
			toHold[i] = array[j];
			j--;
		}
		return toHold;
	}

	@Override
	public Object[] toArray() {
		Object[] obj = new Object[size()];
		
		int j = size() - 1;
		for(int i = 0; i < size(); i++) {
			obj[i] = array[j];
			j--;
		}
		return obj;
	}

	@Override
	public Iterator<E> iterator() {
		Iterator<E> i = new ArrayListIterator<E>(this);
		return i;
	}
	
	@SuppressWarnings("hiding")
	public class ArrayListIterator<E> implements Iterator<E> {
		
		private int current;
		private MyArrayList<E> array;
		
		public ArrayListIterator(MyArrayList<E> array) {
			this.array = array;
			this.current = 0;
		}
		@Override
		public boolean hasNext() {
			if (array.get(current + 1) != null) {
				return true;
			}
			return false;
		}

		@Override
		public E next() throws NoSuchElementException {
			if(hasNext() == false) {
				throw new NoSuchElementException();
			}
			current++;
			return array.get(current);
		}

	}

}
