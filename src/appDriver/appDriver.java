package appDriver;

import exceptions.EmptyQueueException;
import parser.Parser;

/**
 * 
 *Class runs the program from the class Parser with the method readXML
 *
 * @author Jordan Tejada, David D'Entremont, Huu Mai, Van Hien Tieu
 *
 */
public class appDriver
{
	/**
	 * Run the Parser method readXML.
	 * @param args
	 * @throws EmptyQueueException
	 */
	public static void main(String[]args) throws EmptyQueueException
	{
		Parser.readXML(args);
	}
}
