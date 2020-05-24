// Raja Hammad Mehmood.
//This program uses queue method(First in first out) for borrowing and returning an item.

import java.util.Scanner;
import java.util.Queue; // Required for the Queue
import java.util.LinkedList; // Additional class required for the Queue
public class Question3 {
	public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	Queue<String> q = new LinkedList<String>();
    	while(true) {
    		System.out.println("Enter 'return', 'borrow', or, 'quit':");
    		String choice=scanner.nextLine();
    		if(choice.equalsIgnoreCase("borrow")) {
    			System.out.println("Your Name?");
    			
    			String name=scanner.nextLine();
    			if(q.isEmpty()==true) {
    				System.out.println("You can borrow the item immediatly");
    			}
    			else {
    				System.out.println("You'll have to wait");
    			}
    			q.add(name);
    			
    		}
    		else if(choice.equalsIgnoreCase("return")) {
    			q.remove();
    			if(q.isEmpty()==true) {
    				System.out.println("Just leave the item. Nobody's waiting");
    			}
    			else {
    				System.out.println("Give the item to "+q.peek());
    			}
    			
    		}
    		else if(choice.equalsIgnoreCase("quit")) {
    			System.out.println("Done");
    			break;
    			
    		}
    		
    	}
    }
	
}

