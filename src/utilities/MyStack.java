package utilities;

import java.util.EmptyStackException;

import adts.StackADT;

/**
 * MyStack class using MyArrayList list storing
 * @author Jordan Tejada, David D'Entremont, Huu Mai, Van Hien Tieu
 *
 * @param <E> Oct 23, 2021
 */
public class MyStack<E> extends MyArrayList<E> implements StackADT<E>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4275502251243506280L;
	
	/*@Override
	public boolean isEmpty() {
		return size() <= 0;
		
	}*/
	

	public void push(E element) throws NullPointerException{
		add(element);
	}
	
	public E peek() throws EmptyStackException{
		if (isEmpty() == false) {
			return get(size() - 1);
		}
		throw new EmptyStackException();
	}
	
	public E pop() throws EmptyStackException{
		if(isEmpty() == false) {
			return remove(size() - 1);
		}
		throw new EmptyStackException();
	}
	
	/*@Override
	public void clear() {
		list.clear();
	}
	*/
	/*@Override
	public int size() {
		return list.size();
	}*/
	
	public boolean equals(StackADT<E> that) {
		StackADT<E> compare = that;
		if(size() != that.size()) {
			return false;
		}
		else {
				for(int i = size() - 1; i >= 0; i--) {
					if(!get(i).equals(compare.pop())) {
						return false;
					}
				}	
			}
		return true;
	}
	
/*	@Override
	public Iterator<E> iterator() {
		Iterator<E> iter = new StackIterator<E>(this);
		return iter;
	}
	
	@SuppressWarnings("hiding")
	private class StackIterator<E> implements Iterator<E> {
		
		private StackADT<E> list;
		public StackIterator(StackADT<E> myStack) {
			list = myStack;
		}

		@Override
		public boolean hasNext() {
			if(list.peek() == null) {
				return false;
			}
			return true;			
		}

		@Override
		public E next() throws EmptyStackException{
			if(hasNext() == true) {
				return list.pop();
			}
			throw new EmptyStackException();
		}
	}
	*/
/*	@Override
	public Object[] toArray() {
		Object[] obj = new Object[size()];
		
		int j = size() - 1;
		for(int i = 0; i < size(); i++) {
			obj[i] = list.get(j);
			j--;
		}
		return obj;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public E[] toArray(E[] copy) throws NullPointerException{
		if(copy == null) {
			throw new NullPointerException();
		}
		if(copy.length < size()) {
			copy = (E[]) Array.newInstance(copy.getClass().getComponentType(), size());
		}
		int j = size() - 1;
		for(int i = 0; i < size(); i++) {
			copy[i] = list.get(j);
			j--;
		}
		return copy;
	}*/
	
	public int search(E obj) throws NullPointerException{
		if (obj == null) {
			throw new NullPointerException();
		}
		for(int i = 0; i < size(); i++) {
			if(get(i).equals(obj)) {
				return size() - (i);
			}
		}
		return -1;
	}
	
/*	@Override
	public boolean contains(E obj) throws NullPointerException{
		if (obj == null) {
			throw new NullPointerException();
		}
		if (list.contains(obj)) {
			return true;
		}
		return false;
	}*/

}
