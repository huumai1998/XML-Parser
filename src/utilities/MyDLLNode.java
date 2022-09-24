package utilities;

/**
 * Double linked list Node class for MyDLL using ListADT
 * @author Jordan Tejada, David D'Entremont, Huu Mai, Van Hien Tieu
 *
 * @param <E> Oct 23, 2021
 */
public class MyDLLNode<E>  {
	
	private E element;
	private MyDLLNode<E> prev, next;
	
	public MyDLLNode(E element, MyDLLNode<E> prev, MyDLLNode<E> next) {
		this.setElement(element);
		this.setPrev(prev);
		this.setNext(next);
	}

	public MyDLLNode<E> getPrev() {
		return prev;
	}

	public void setPrev(MyDLLNode<E> prev) {
		this.prev = prev;
	}

	public MyDLLNode<E> getNext() {
		return next;
	}

	public void setNext(MyDLLNode<E> next) {
		this.next = next;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}
}
