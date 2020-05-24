
// Author: Chris Fietkiewicz. Based on PostfixEval.java by David Eck.
// Sample code for HW #6.
// Raja Hammad Mehmood
//Converts infix to post fix expression and evaluates it

import java.io.*;
import java.util.Stack;

public class InfixEval {
	public static void main(String[] args) {
		System.out.println("This program can evaluate FULLY PARENTHESIZED INFIX expressions such as\n");
		System.out.println("        (2 + 2)");
		System.out.println("or");
		System.out.println("        ((7.3 + 89.2) / ((9 * 1.83) + 2))");
		System.out.println("The operators +, -, *, /, and ^ can be used.");
		while (true) {
			// Get and process one line of input from the user.
			System.out.println("\nEnter a FULLY PARENTHESIZED INFIX expression or press return to end:\n");
			String infixString = TextIO.getln(); // NEW: Save user input for conversion
			if (infixString.length() == 0) {
				// If the line is empty, we are done.
				break;
			}
			String postfixString = convertToPostfix(infixString); // NEW: Students must complete this method.
			InputStream targetStream = new ByteArrayInputStream(postfixString.getBytes()); // NEW: Prepare to redirect
																							// string to
																							// readAndEvaluate()
			TextIO.readStream(targetStream); // NEW: Redirect string to readAndEvaluate()
			readAndEvaluate(); // Process the input.
			TextIO.readStandardInput(); // NEW: Reset TextIO to get keyboard input
		}
		System.out.println("Exiting program.");
	} // end main();

	/*
	 * The convertToPostfix method receives a String that is assumed to contain a
	 * fully parenthesized infix expression. It should convert the infix expression
	 * String into an equivalent postfix expression, which is returned. The
	 * conversion should be done using Java's Stack class, which is used in the
	 * sample code to create a stack of operators. NOTE: The sample code is provided
	 * with a Stack of Characters and an *incorrect* postfix String. Students need
	 * to create "postfixString" using the algorithm described in the posted
	 * instructions.
	 */
	private static String convertToPostfix(String infixString) {
		Stack<Character> operators = new Stack<Character>(); // Stack of Characters (for operators)
		String postfixString = "";
		for (int i = 0; i < infixString.length(); i++) {
			char c = infixString.charAt(i);
			if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
				operators.push(c);
			} else if (c == ')') {
				if (operators.isEmpty() != true) {
					postfixString = postfixString.concat(operators.pop().toString());
				}
			} else if (c != '(') {
				postfixString = postfixString.concat(((Character) c).toString());
			}
		}

		System.out.println(postfixString);
		return postfixString;
	}

	/**
	 * Read one line of input and process it as a postfix expression. If the input
	 * is not a legal postfix expression, then an error message is displayed.
	 * Otherwise, the value of the expression is displayed. It is assumed that the
	 * first character on the input line is a non-blank. (This is checked in the
	 * main() routine.)
	 */
	private static void readAndEvaluate() {

		StackOfDouble stack; // For evaluating the expression.

		stack = new StackOfDouble(); // Make a new, empty stack.

		System.out.println();

		while (TextIO.peek() != '\n') {

			if (Character.isDigit(TextIO.peek())) {
				// The next item in input is a number. Read it and
				// save it on the stack.
				double num = TextIO.getDouble();
				stack.push(num);
				System.out.println("   Pushed constant " + num);
			} else {
				// Since the next item is not a number, the only thing
				// it can legally be is an operator. Get the operator
				// and perform the operation.
				char op; // The operator, which must be +, -, *, /, or ^.
				double x, y; // The operands, from the stack, for the operation.
				double answer; // The result, to be pushed onto the stack.
				op = TextIO.getChar();
				if (op != '+' && op != '-' && op != '*' && op != '/' && op != '^') {
					// The character is not one of the acceptable operations.
					System.out.println("\nIllegal operator found in input: " + op);
					return;
				}
				if (stack.isEmpty()) {
					System.out.println("   Stack is empty while trying to evaluate " + op);
					System.out.println("\nNot enough numbers in expression!");
					return;
				}
				y = stack.pop();
				if (stack.isEmpty()) {
					System.out.println("   Stack is empty while trying to evaluate " + op);
					System.out.println("\nNot enough numbers in expression!");
					return;
				}
				x = stack.pop();
				switch (op) {
				case '+':
					answer = x + y;
					break;
				case '-':
					answer = x - y;
					break;
				case '*':
					answer = x * y;
					break;
				case '/':
					answer = x / y;
					break;
				default:
					answer = Math.pow(x, y); // (op must be '^'.)
				}
				stack.push(answer);
				System.out.println("   Evaluated " + op + " and pushed " + answer);
			}

			TextIO.skipBlanks();

		} // end while

		// If we get to this point, the input has been read successfully.
		// If the expression was legal, then the value of the expression is
		// on the stack, and it is the only thing on the stack.

		if (stack.isEmpty()) { // Impossible if the input is really non-empty.
			System.out.println("No expression provided.");
			return;
		}

		double value = stack.pop(); // Value of the expression.
		System.out.println("   Popped " + value + " at end of expression.");

		if (stack.isEmpty() == false) {
			System.out.println("   Stack is not empty.");
			System.out.println("\nNot enough operators for all the numbers!");
			return;
		}

		System.out.println("\nValue = " + value);

	} // end readAndEvaluate()

} // end class PostfixEval
