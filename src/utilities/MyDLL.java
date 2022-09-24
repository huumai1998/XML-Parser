package utilities;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

import adts.Iterator;
import adts.ListADT;

/**
 * A class the implements a queue of objects using an Double linked list.
 * @author Jordan Tejada, David D'Entremont, Huu Mai, Van Hien Tieu 
 *
 * @param <E> Oct 23, 2021
 */
public class MyDLL<E> implements ListADT<E> {
	
	private static final long serialVersionUID = 2447827901295237242L;
	private MyDLLNode<E> head;
	private MyDLLNode<E> tail;
	private int size;
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index must be between 0 and " + size());
		if (toAdd == null)
			throw new NullPointerException("Cannot add null element.");
		
		MyDLLNode<E> add = new MyDLLNode<E>(toAdd, null, null);
		
		if (index == 0) {
			if (head == null) {
				head = add;
				size++;
			}
			else {
				add.setNext(head);
				head.setPrev(add);
				head = add;
				size++;
			}
		}
		
		else if (index == size) {
			if (size == 1) {
				tail = add; 
				head.setNext(tail);
				tail.setPrev(head);
				size++;
			}
			else {
				add.setPrev(tail);
				tail.setNext(add);
				tail = add;
				size++;
			}
		}
		
		else {
			MyDLLNode<E> curr = head;
			for(int i = 0; i < size; i++) {
				if (index == 1 && i == 1) {
					head.setNext(add);
					add.setPrev(head);
					curr.setPrev(add);
					add.setNext(curr);
					size++;
					return true;
				}
				else if (index == (size - 1)) {
					curr.setNext(add);
					add.setPrev(curr);
					tail.setPrev(add);
					add.setNext(tail);
					size++;
					return true;
				}
				if (i == index) {
					curr.getPrev().setNext(add);
					add.setPrev(curr.getPrev());
					curr.setPrev(add);
					add.setNext(curr);
					size++;
					return true;
				}
				curr = curr.getNext();
			}
		}
		return true;
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if (toAdd == null)
			throw new NullPointerException("Cannot add null element.");
		
		MyDLLNode<E> add = new MyDLLNode<E>(toAdd, null, null);
		
		if (head == null) {
			head = add;
			size++;
			return true;
		}
		
		else if (size == 1) {
			tail = add;
			head.setNext(tail);
			tail.setPrev(head);
			size++;
		}
		
		else {
			add.setPrev(tail);
			tail.setNext(add);
			tail = add;
			size++;
		}
		return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < toAdd.size(); i++) {
			if (head == null) {
				head.setElement(toAdd.get(i));
				size++;
			}
			else {
				MyDLLNode<E> add = new MyDLLNode<E>(toAdd.get(i), null, null);
				tail.setNext(add);
				add.setPrev(tail);
				tail = add;
			}
		}
		return true;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index must be between 0 and " + size());
		
		if (index == 0) {
			return head.getElement();
		}
		else if (index == (size - 1)) {
			return tail.getElement();
		}
		
		else {
			MyDLLNode<E> curr = head;
			for(int i = 0; i < size; i++) {
				if (i == index)  {
					return curr.getElement();
				}
				curr = curr.getNext();
			}
		}
		return null;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index must be between 0 and " + size());
		
		MyDLLNode<E> remove = new MyDLLNode<E>(get(index), null, null);
		MyDLLNode<E> curr = head;
		
		if (size == 1) {
			clear();
		}
		
		else if (size == 2) {
			if (index == 0) {
				head = head.getNext();
				head.setPrev(null);	
				size--;
				tail = null;
			}
			else {
				head = head.getPrev();
				head.setNext(null);
				size--;
				tail = null;
			}
		}
		
		else if (index == 0) {
			head = head.getNext();
			head.setPrev(null);	
			size--;
		}
		
		
		else if (index == (size - 1)) {
			tail = tail.getPrev();
			tail.setNext(null);
			size--;
		}
		
		else {
			for(int i = 0; i < size; i++) {
				if (i == index) { 
					curr.getNext().setPrev(curr.getPrev());
					curr.getPrev().setNext(curr.getNext());
					size--;
					return remove.getElement();
				}
				curr = curr.getNext();
			}
		}
		return remove.getElement();
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {
		if (toRemove == null)
			throw new NullPointerException("Cannot add null element.");
		
		MyDLLNode<E> curr = head;
		
		if (!contains(toRemove)) {
			return null;
		}
		
		if (size == 1) {
			clear();
			return toRemove;
		}
		
		else if (size == 2) {
			if (toRemove.equals(head.getElement())) {
				head = head.getNext();
				head.setPrev(null);	
				size--;
				tail = null;
				return toRemove;
			}
			else {
				head = head.getPrev();
				head.setNext(null);
				size--;
				tail = null;
				return toRemove;
			}
		}
		
		else if (toRemove.equals(head.getElement())) {
			head = head.getNext();
			head.setPrev(null);	
			size--;
			return toRemove;
		}
		else if (toRemove.equals(tail.getElement())) {
			tail = tail.getPrev();
			tail.setNext(null);
			size--;
			return toRemove;
		}
		else {
			for(int i = 0; i < size; i++) {
				if(curr.getElement().equals(toRemove)) {
					curr.getNext().setPrev(curr.getPrev());
					curr.getPrev().setNext(curr.getNext());
					size--;
					return toRemove;
				}
				curr = curr.getNext();
			}
		}
		return null;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index must be between 0 and " + size());
		if (toChange == null)
			throw new NullPointerException("Cannot add null element.");
		
		MyDLLNode<E> change  = new MyDLLNode<E>(toChange, null, null);
		MyDLLNode<E> curr = head;
		E replaced = null;
		int i = 0;
		
		if (size == 1){
			head = change;
		}
		
		
		else if (index == 0) {
			replaced = head.getElement();
			if (head == null) {
				head = change;
			}
			else if (size == 2) {
				change.setNext(tail);
				tail.setPrev(change);
				head = change;
			}
			else if (size >= 3) {
				head.getNext().setPrev(change);
				change.setNext(head.getNext());
				head = change;
			}
		}
		
		else if (index == (size - 1)) {
			replaced = tail.getElement();
			if (tail == null) {
				tail = change;
				head.setNext(tail);
				tail.setPrev(head);
			}
			else if (size == 2) {
				tail = change;
				tail.setPrev(head);
				head.setNext(tail);
			}
			else if (size >= 3) {
				tail.getPrev().setNext(change);
				change.setPrev(tail.getPrev());
				tail = change;
			}
		}
		
		else if (index == 1 && size == 3) {
			replaced = head.getNext().getElement();
			head.setNext(change);
			change.setPrev(head);
			tail.setPrev(change);
			change.setNext(tail);
		}
		
		else {
			return setRecursive(i + 1, curr.getNext(), change, index);
		}
		return replaced;
	}

	private E setRecursive(int i, MyDLLNode<E> curr, MyDLLNode<E> change, int index) {
		E replaced = null;
		if (i == index) {
			replaced = curr.getElement();
			if (index == 1) {
				change.setNext(curr.getNext());
				curr.getNext().setPrev(change);
				change.setPrev(head);
				head.setNext(change);
			}
			else if (index == (size - 2)) {
				change.setPrev(curr.getPrev());
				curr.getPrev().setNext(change);
				change.setNext(tail);
				tail.setPrev(change);
			}
			else {
				change.setNext(curr.getNext());
				curr.getNext().setPrev(change);
				change.setPrev(curr.getPrev());
				curr.getPrev().setNext(change);
			}
		}
		else {
			return setRecursive(i + 1, curr.getNext(), change, index);
		}
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
			throw new NullPointerException("Cannot add null element.");
		
		MyDLLNode<E> curr = head;
		
		if (size == 0) {
			return false;
		}
		
		else {
			while(curr != null) {
				if (curr.getElement().equals(toFind)) {
					return true;
				}
				curr = curr.getNext();
			}
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		if (toHold == null)
			throw new NullPointerException("Cannot add null element.");
		
		if(toHold.length < size) {
			toHold = (E[]) Array.newInstance(toHold.getClass().getComponentType(), size);
		}
		
		if (size == 0) {
			return null;
		}
		
		else {
			MyDLLNode<E> curr = head;
			for (int i = 0; i < size; i++) {
				toHold[i] = curr.getElement();
				curr = curr.getNext();
			}
			return toHold;
		}
	}

	@Override
	public Object[] toArray() {
		
		if (size == 0) {
			return null;
		}
		else {
			Object[] obj = new Object[size];
			MyDLLNode<E> curr = head;
			for (int i = 0; i < size; i++) {
				obj[i] = curr.getElement();
				curr = curr.getNext();
			}
			return obj;
		}
	}

	@Override
	public Iterator<E> iterator() {
		Iterator<E> iter = new DLLIterator<E>(this);
		return iter;
	}
	
	@SuppressWarnings("hiding")
	private class DLLIterator<E> implements Iterator<E> {
		
		private MyDLLNode<E> curr;
		public DLLIterator(MyDLL<E> list) {
			curr = list.head;
		}
		
		@Override
		public boolean hasNext() {
			if(curr.getNext() == null) {
				return false;
			}
			return true;
		}

		@Override
		public E next() throws NoSuchElementException {
			if (hasNext() == false) {
				throw new NoSuchElementException();
			}
			curr = curr.getNext();
			return curr.getElement();
		}

	}

}
