package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import exceptions.EmptyQueueException;
import utilities.MyQueue;
import utilities.MyStack;
/**
 * Class for parsing xml files using queues and stacks.
 *  @author Jordan Tejada, David D'Entremont, Huu Mai, Van Hien Tieu
 * Nov 8, 2021
 */
public class Parser {
	/**
	 * Method takes the command line "args" and finds the file within the res folder. Parsers' the xml file to see if 
	 * it formatted correctly, if not returns errors of the tags it found errors in. 
	 * @param args the input from the command line
	 * @throws EmptyQueueException 
	 */
	public static void readXML(String[] args) throws EmptyQueueException {
		String file = "res/";
		// reads the strings from args and finds the string -f to find the file name for parsing
		for (int i = 0; i < args.length; i++) {
			if(args[i].toLowerCase().startsWith("-f")) {
				file = file + args[i].substring(args[i].indexOf("-f") + 2);
			}
		}
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File(file))); // reads file
			MyStack<String> stack = new MyStack<String>();
			MyQueue<String> errorQ = new MyQueue<String>();
			MyQueue<String> extrasQ = new MyQueue<String>();
			
			String[] line;
			String[] moreLine;
			String read = "";
			String opening = "<";
			String closing = "</";
			String root = "";
			int rootCounter = 0;
			boolean rootCheck = false;
			// Skips the first line
			br.readLine();
			// reads each line in the file
			while ((read = br.readLine()) != null) {
				String openingTag = "";
				String closingTag = "";
				int openCounter = 0;
				int closeCounter = 0;
				boolean tagCheck = false;
				// Checks to see if there is more than 1 tag in the line
				// Note: it grabs the first words before "<" so if there is more than 1 tag, there is going to be
				// more than 2 elements in the array, 2 elements if there is one tag
				moreLine = read.split("<");
				//Counts the number of "<" and ">" characters
				for(int i = 0; i < read.length(); i++) {
					if(read.charAt(i) == '<') {
						openCounter++;
					}
					else if(read.charAt(i) == '>') {
						closeCounter++;
					}
					
				}
				//If they dont match, make tagCheck true meaning there are extra "<" or ">" characters
				if(openCounter != closeCounter) {
					tagCheck = true;
				}
				// Creates a variable string in between the characters "<" and ">" + 1(so the string the string contains
				// ">") 
				read = read.substring(read.indexOf("<"), read.indexOf(">") + 1);
				// Checks if there is more than 1 root tag
				if (root.equals(read)) {
					rootCounter++;
				}
				// Creates a root variable
				if (!read.startsWith("<?") && rootCheck == false) {
					rootCheck = true;
					root = read;
				}
				// if it reads an opening tag and skips if it ends with "/>" and if there is only 1 tag in the line..
				if (read.startsWith(opening) && !read.startsWith(closing) && !read.endsWith("/>") && moreLine.length < 3) {
					// reads the the line and separates it between character " "
					line = read.split(" ");
					// if the starting line ends with ">" correctly gets the substring in between the two characters
					// "<" and ">" into opening tag and pushes it
					if (line[0].endsWith(">")) {
						openingTag = line[0].substring(read.indexOf("<") + 1, read.indexOf(">"));
					}
					// else correctly gets the substring between the character "<" and the end of the string and pushes it
					else {
						openingTag = line[0].substring(read.indexOf("<") + 1);
					}
					// pushes the openingTag
					stack.push(openingTag);
					// Also if there is an extra "<" or ">" in the line, enqueue in extraQ
					if (tagCheck == true) {
						extrasQ.enqueue(openingTag);
					}
				}
				// if it reads a closing tag and there is only 1 tag in the line..
				if (read.startsWith(closing) && moreLine.length < 3) {
					closingTag = read.substring(read.indexOf("/") + 1, read.indexOf(">"));
					// if closing tag matches the peek of the stack, pop the stack
					if (stack.peek().equals(closingTag)) {
						stack.pop();
					}
					// if closing tag doesn't match the peek but matches the head of errorQ, dequeue and ignore
					else if (!errorQ.isEmpty() && errorQ.peek().equals(closingTag)) {
						errorQ.dequeue();
					}
					// if the stack is empty, add to errorQ and ignore
					else if (stack.isEmpty()) {
						errorQ.enqueue(closingTag);
					}
					// Search the stack for matching closing tag and opening tag
					else {
						int search = 0;
						search = stack.search(closingTag);
						// if match is found, pop until reaches opening tag and enqueue all popped tags
						if (search != -1) {
							for(int i = 0; i < search; i++) {
								errorQ.enqueue(stack.pop());
							}
							System.out.println("error not matching closing tag with opening tag = " + closingTag);
						}
						// if match not found, tag enqueue in extrasQ
						else {
						extrasQ.enqueue(closingTag);
						}
					}
					// Also if there is an extra "<" or ">" in the line, enqueue in extraQ
					if (tagCheck == true) {
						extrasQ.enqueue(closingTag);
					}
				}
				//Same methods as above but if the line contains more than 1 tag
				if (moreLine.length > 2) {
					for(int i = 1; i < moreLine.length; i++) {
						if (!moreLine[i].startsWith("/") && !read.endsWith("/>")) {
							openingTag = moreLine[i].substring(0, moreLine[i].indexOf(">"));
							stack.push(openingTag);
							// Also if there is an extra "<" or ">" in the line, enqueue in extraQ
							if (tagCheck == true) {
								extrasQ.enqueue(openingTag);
							}
						}
						if (moreLine[i].startsWith("/")) {
							closingTag = moreLine[i].substring(moreLine[i].indexOf("/") + 1, moreLine[i].indexOf(">"));
							if (stack.peek().equals(closingTag)) {
								stack.pop();
							}
							else if (!errorQ.isEmpty() && errorQ.peek().equals(closingTag)) {
								errorQ.dequeue();
							}
							else if (stack.isEmpty()) {
								errorQ.enqueue(closingTag);
							}
							else {
								int search = 0;
								search = stack.search(closingTag);
								if (search != -1) {
									for(int j = 0; j < search; j++) {
										errorQ.enqueue(stack.pop());
									}
									System.out.println("error not matching closing tag with opening tag = " + closingTag);
								}
								else {
								extrasQ.enqueue(closingTag);
								}
							}
							// Also if there is an extra "<" or ">" in the line, enqueue in extraQ
							if (tagCheck == true) {
								extrasQ.enqueue(closingTag);
							}
						}			
					}
				}
			}
			// Repeat until both queues are empty
			while(!errorQ.isEmpty() || !extrasQ.isEmpty()) {
				//if stack is not empty, pop each element from each stack into errorQ
				if (!stack.isEmpty()) {
					int size = stack.size();
					for(int i = 0; i < size; i++) {
						errorQ.enqueue(stack.pop());
					}
				}
				// if either queues are empty(but not both), report each element in both queues as error
				if (errorQ.isEmpty() && !extrasQ.isEmpty() || !errorQ.isEmpty() && extrasQ.isEmpty()) {
					// if errorQ is not empty....
					if (!errorQ.isEmpty()) {
						String[] errorList = new String[errorQ.size()];
						errorList = errorQ.toArray(errorList);
						for (int i = 0; i < errorList.length; i++) {
							System.out.println("error = " + errorList[i]);
						}
						errorQ.dequeueAll();
					}
					// else if extrasQ is not empty....
					else if (!extrasQ.isEmpty()) {
						String[] errorList = new String[extrasQ.size()];
						errorList = extrasQ.toArray(errorList);
						for (int i = 0; i < errorList.length; i++) {
							System.out.println("error no matching close or opening tag / extra '<' or '>'= " + errorList[i]);
						}
						extrasQ.dequeueAll();
					}
				}
				// if both queues are not empty..
				if (!errorQ.isEmpty() && !extrasQ.isEmpty()) {
						// if the peek of both queues don't match, dequeue errorQ and report it as an error
						if (!errorQ.peek().equals(extrasQ.peek())) {
							System.out.println("error = " + errorQ.dequeue());
						}
						// else dequeue both errorQ and extrasQ
						else {
							errorQ.dequeue();
							extrasQ.dequeue();
						}
					} 
				}
			// If there is more than one root tag, report as an error
			if (rootCounter > 0) {
				System.out.println("error = more than one root tag");
			}
		} catch (FileNotFoundException e)
		{
			System.out.print("Error reading file");
			e.printStackTrace();
		} catch (IOException e)
		{
			System.out.print("Error reading file");
			e.printStackTrace();
		}
		System.out.println("Finished Parsing");
	}
}
