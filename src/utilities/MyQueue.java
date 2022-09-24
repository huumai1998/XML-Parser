package utilities;

import adts.QueueADT;
import exceptions.EmptyQueueException;

/**
 * MyQueue class using MyDLL list storing
 * @author Jordan Tejada, David D'Entremont, Huu Mai, Van Hien Tieu
 *
 * @param <E> Oct 23, 2021
 */
public class MyQueue<E> extends MyDLL<E> implements QueueADT<E>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3637181872858396662L;
	private int capacity;

	public void enqueue(E element) throws NullPointerException {
			add(element);
			/*if(element != null) {
				list.add(element);
			}
			else {
				throw new NullPointerException();
			}*/
	}

	public E dequeue() throws EmptyQueueException {
		if(size() != 0) {
			return remove(0);
		}
		throw new EmptyQueueException();
	}

	public E peek() throws EmptyQueueException {
		if(size() != 0) {
			return get(0);
		}
		throw new EmptyQueueException();
	}

	public boolean equals(QueueADT<E> that) {
		QueueADT<E> compare = that;
		if(size() != that.size()) {
			return false;
		}
		else {
			for(int i = 0; i < size(); i++) {
				try {
					if(!get(i).equals(compare.dequeue())) {
						return false;
					}
				} catch (IndexOutOfBoundsException e) {
					throw new IndexOutOfBoundsException();
				} catch (EmptyQueueException e) {
					System.out.print("Empty Queue");
				}
			}
		}
		return true;
	}

	/*@Override
	public Iterator<E> iterator() {
		Iterator<E> iter = new QueueIterator<E>(this);
		return iter;
	}
	
	
	/*@SuppressWarnings("hiding")
	private class QueueIterator<E> implements Iterator<E> {
		
		private MyQueue<E> list;
		public QueueIterator(MyQueue<E> queue) {
			list = queue;
		}
		@Override
		public boolean hasNext() {
			try {
				if(list.peek() != null) {
					return true;
				}
			}	catch (EmptyQueueException e) {
				return false;
			}
			return false;
		}

		@Override
		public E next() throws NoSuchElementException {
			if (hasNext()) {
				try {
					return list.dequeue();
				} catch (EmptyQueueException e) {
					return null;
				}	
			}
			return null;
		}
		
	}
	
	@Override
	public Object[] toArray() {
		Object[] obj = new Object[size()];
		
		for(int i = 0; i < size(); i++) {
			obj[i] = list.get(i);
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] copy) throws NullPointerException {
		if(copy == null) {
			throw new NullPointerException();
		}
		if(copy.length < size()) {
			copy = (E[]) Array.newInstance(copy.getClass().getComponentType(), size());
		}
		for(int i = 0; i < size(); i++) {
			copy[i] = list.get(i);
		}
		return copy;
	}
	*/
	@Override
	public boolean isFull() {
		if(size() == capacity) {
			return true;
		}
		return false;
	}

	/*@Override
	public int size() {
		return list.size();
	}*/

	/*@Override
	public boolean isEmpty() {
		if(size() == 0) {
			return true;
		}
		return false;
	}*/

	@Override
	public void dequeueAll() {
		clear();
//		list.clear();
	}
	
}
