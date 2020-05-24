
// Author: Chris Fietkiewicz.
//Raja Hammad Mehmood
//Checks if a word is a Palindrome.
// Sample code for HW #6.
import java.util.Scanner; // Required for user input
import java.util.Stack; // Required for the Stack
import java.util.Queue; // Required for the Queue
import java.util.LinkedList; // Additional class required for the Queue
import java.util.Stack; // Required for the Stack
import java.util.Queue; // Required for the Queue
import java.util.LinkedList; // Additional class required for the Queue

public class Palindrome {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the text: ");
		String text = scanner.nextLine();
		if (isPalindrome(text))
			System.out.println("Yes, that is a palindrome!");
		else
			System.out.println("No, that is not a palindrome!");
	}

	/*
	 * Method to check whether parameter "text" is a palindrome. Returns true if it
	 * is, or false if it is not.
	 */
	public static boolean isPalindrome(String text) {
		Stack<Character> stack = new Stack<Character>();
		Queue<Character> queue = new LinkedList<Character>();
		// Put string into stack and queue
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			stack.push(c);
			queue.add(c);
		}
		for (int i = 0; i < text.length(); i++) {
			char a = stack.pop();
			char b = queue.remove();
			if (a != b) {
				return false;
			}
		}
		return true; // Return true if all of the characters matched
	}
}
