package exceptions;

public class EmptyQueueException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7738439988818816808L;
	
	public EmptyQueueException() {
		System.out.println("Empty Queue!");
	}

}
