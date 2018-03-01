import java.util.*;

/**
 * This class drives the program, containing the main method allowing the user to perform various commands.
 * @author Brett Weinger, ID #: 111639717
 */
public class DeliveryDriver {
	
	/**
	 * The main method prompts the user to input a choice based on a list of options
	 * The method may call one of its helper methods to perform the selected command
	 * As long as the user does not terminate the program, it will iterate and allow the user to perform multiple commands
	 * @exception IllegalArgumentException
	 * 	Handles this exception by printing out a message indicating that the user entered invalid data
	 * @exception EndOfListException
	 * 	Handles this exception by printing out a message indicating that the user exceeded the bounds of the current list
	 */
	public static void main(String[] args) {
		DeliveryList mainList = new DeliveryList();
		DeliveryList altList = new DeliveryList();
		Scanner sc = new Scanner(System.in);
		DeliveryList currentList = mainList;
		boolean repeat = true;
		Delivery clipboard = null;
		System.out.println("Welcome to the Delinquent Dollar Delivery Scheduler.\n");
		System.out.println("Menu:\nA) Add a delivery after cursor\n"
				+ "R) Remove delivery at cursor\nX) Cut Cursor\n"
				+ "V) Paste after cursor\nH) Cursor to head\n"
				+ "T) Cursor to tail\nF) Cursor Forward\n"
				+ "B) Cursor backward\nS) Switch delivery lists\n"
				+ "P) Print current list\nQ) Quit\n");
		while(repeat) {
			try {
				System.out.print("Please enter a choice: ");
				String choice = sc.nextLine().toUpperCase();
				switch(choice) {
				case "A":
					addAfterCursor(currentList, sc);
					break;
				case "H":
					currentList.setCursor(currentList.getHead());
					System.out.println("Cursor is now at head.");
					break;
				case "T":
					currentList.setCursor(currentList.getTail());
					System.out.println("Cursor is now at tail.");
					break;
				case "F":
					currentList.cursorForward();
					System.out.println("Cursor incremented forward.");
					break;
				case "B":
					currentList.cursorBackward();
					System.out.println("Cursor decremented backward.");
					break;
				case "R":
					currentList.removeCursor();
					System.out.println("Cursor removed.");
					break;
				case "X":
					clipboard = currentList.removeCursor();
					System.out.println("Cursor is cut.");
					break;
				case "V":
					if(clipboard == null) {
						System.out.println("Nothing to paste!");
					} else {
						currentList.insertAfterCursor(clipboard);
						System.out.println("Pasted from clipboard.");
					}
					break;
				case "S":
					if(currentList == mainList) {
						currentList = altList;
						System.out.println("Money Mike's list selected.");
					} else {
						currentList = mainList;
						System.out.println("Billy Business' list selected.");
					}
					break;
				case "P":
					print(currentList, mainList, altList);
					break;
				case "Q":
					System.out.println("Goodbye!");
					repeat = false;
					break;
				default:
					System.out.println("Choice not recognized. Try again?");
				}
			} catch(IllegalArgumentException i) {
				System.out.println("Illegal argument!");
			} catch(EndOfListException i) {
				System.out.println("End of list!");
			}
			System.out.println();
		}
	}
	
	/**
	 * Prompts user for inputs to add a new delivery object to the list after the cursor pointer
	 * @param list
	 * 	The Delivery List object to add the new delivery to
	 * @param sc
	 * 	The Scanner object that reads in user input
	 * @throws IllegalArgumentException
	 * 	If the method called within the Delivery List object throws this exception, indicating the user attempted to add a null object to the list
	 * 	As this program is currently written, the user input is restricted such that this exception should not be thrown
	 */
	public static void addAfterCursor(DeliveryList list, Scanner sc) throws IllegalArgumentException {
		System.out.print("Please enter a source: ");
		String source = sc.nextLine();
		System.out.print("Please enter a destination: ");
		String dest = sc.nextLine();
		System.out.print("Please enter any special instructions: ");
		String instruction = sc.nextLine();
		list.insertAfterCursor(new Delivery(source, dest, instruction));
		System.out.println("Order inserted.");
	}
	
	/**
	 * Prints the contents of the current Delivery List
	 * @param currentList
	 * 	The list the user is currently working with that will be printed out
	 * @param mainList
	 * 	Biz Billy's list, which is passed into the method to check whose name to display
	 * @param altList
	 * 	Money Mike's list, which is passed into the method to check whose name to display
	 */
	public static void print(DeliveryList currentList, DeliveryList mainList, DeliveryList altList) {
		if(currentList == mainList) {
			System.out.println("Biz Billy's Deliveries:");
		} else {
			System.out.println("Money Mike's Deliveries:");
		}
		System.out.println("------------------------");
		DeliveryListNode nodePTR = new DeliveryListNode();
		nodePTR = currentList.getHead();
		for(int i = 0; i < currentList.numDeliveries(); i++) {
			System.out.println(nodePTR.getData());
			System.out.println("~");
			nodePTR = nodePTR.getNext();
		}
	}
}
