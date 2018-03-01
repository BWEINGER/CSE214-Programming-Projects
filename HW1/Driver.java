import java.util.Scanner;

/**
 * This class drives the program, containing the main method allowing the user to perform various commands.
 * @author Brett
 */
public class Driver {
	
	/**
	 * The main method prompts the user to input a choice based on a list of options
	 * The method calls one of various helper methods each iteration to perform the selected command
	 * @exception ArrayIndexOutOfBoundsException
	 * 	Handles this exception by printing out a message indicating that the index selected does not correspond to an element in the bookshelf's internal books[] array
	 * @exception IllegalArgumentException
	 * 	Handles this exception by printing out a message indicating that the index selected would create a hole in the books[] array
	 * @exception FullShelfException
	 * 	Handles this exception by printing out a message telling the user that their action could not be performed because the current shelf is full
	 * @exception EmptySHelfException
	 * 	Handles this exception by printing out a message telling the user that their action could not be performed because the current shelf is empty
	 */
	public static void main(String[] args) {
		RipoffRental store = new RipoffRental();
		Bookshelf currentShelf = RipoffRental.getA();
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to my ripoff bookstore. There's probably a bunch of laws "
				+ "against this, so continue at your own risk. "
				+ "Please choose from the following list of menu items.");
		System.out.println("A) Add Book\nS) Swap Books\nL) Loan Book\nR) Remove Book\nD) Duplicate Book\n"
				+ "C) Change Shelf\nO) Overwrite Shelf\nE) Check Shelf Equality\nP) Print Shelf\nQ) Quit\n");
		String choice = "";
		boolean repeatLoop = true;
		while(repeatLoop) {
			try {
				System.out.print("Please select a choice: ");
				choice = sc.nextLine().toUpperCase();
				switch(choice) {
				case "A":
					addBook(currentShelf);
					break;
				case "S":
					swapBooks(currentShelf);
					break;
				case "L":
					loanBook(currentShelf);
					break;
				case "R":
					removeBook(currentShelf);
					break;
				case "D":
					duplicateBook(currentShelf);
					break;
				case "C":
					currentShelf = changeShelf(currentShelf, RipoffRental.getA(), RipoffRental.getB(), RipoffRental.getC());
					break;
				case "O":
					overwriteShelf(currentShelf, RipoffRental.getA(), RipoffRental.getB(), RipoffRental.getC());
					break;
				case "E":
					checkEquality(RipoffRental.getA(), RipoffRental.getB(), RipoffRental.getC());
					break;
				case "P":
					printShelf(currentShelf);
					break;
				case "Q":
					repeatLoop = false;
					System.out.println("Goodbye!");
					sc.close();
					break;
				default:
					System.out.println("Choice not recognized. Try again?\n");
					break;
				}
			} catch(ArrayIndexOutOfBoundsException i) {
				System.out.println("Error: Bad index. Action could not be performed.\n");
			} catch(IllegalArgumentException i) {
				System.out.println("Invalid index! Index is too large to add book.\n");
			} catch(FullShelfException i) {
				System.out.println("Full shelf! Book could not be added.\n");
			} catch(EmptyShelfException i) {
				System.out.println("Empty shelf! Book could not be removed.\n");
			}
		}
	}
	
	/**
	 * Prompts user for inputs to add a new book object to the current shelf
	 * @param shelf
	 * 	The bookshelf object to add the book to
	 * @throws ArrayIndexOutOfBoundsException
	 * 	If the user inputs an index outside the bounds of the shelf's books[] array
	 * @throws IllegalArgumentException
	 * 	If the user inputs an index that would create a hole in the array
	 * @throws FullShelfException
	 * 	If the shelf is full and a new book therefore cannot be added
	 */
	public static void addBook(Bookshelf shelf) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, FullShelfException {
		Scanner sc = new Scanner(System.in); //new scanner initialization prevents automatic character consumption when returning to main
		System.out.print("Please enter a title: ");
		String title = sc.nextLine();
		System.out.print("Please enter an author: ");
		String author = sc.nextLine();
		int condition = 0;
		boolean repeat = true;
		while(repeat) {
			System.out.print("Please enter condition (1-5): ");
			condition = sc.nextInt();
			if(condition >= 1 && condition <= 5) {
				repeat = false;
			} else {
				System.out.println("Come on, I'm trying to make this program stupid-proof! Read the directions!");
			}
		}
		System.out.print("Please enter position on shelf: ");
		int position = sc.nextInt();
		shelf.addBook(position - 1, new Book(title, author, condition)); //throws exceptions accordingly back to main
		System.out.println("Book added!\n");
	}
	
	/**
	 * Prompts user for inputs to swap the positions of two books in the shelf's internal books[] array
	 * @param shelf
	 * 	The bookshelf object to swap the books in
	 * @throws ArrayIndexOutOfBoundsException
	 * 	If the indexes do not correspond to book objects in the array
	 */
	public static void swapBooks(Bookshelf shelf) throws ArrayIndexOutOfBoundsException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter an index: ");
		int index1 = sc.nextInt();
		System.out.print("Please enter another index: ");
		int index2 = sc.nextInt();
		shelf.swapBooks(index1 - 1, index2 - 1);
		System.out.println("Books swapped!\n");
	}
	
	/**
	 * Prompts user for inputs to change borrower and condition of a book on the shelf
	 * @param shelf
	 * 	The bookshelf object to locate the book in
	 * @throws ArrayIndexOutOfBoundsException
	 * 	If the user enters an index that does not properly correspond to a book in the array
	 */
	public static void loanBook(Bookshelf shelf) throws ArrayIndexOutOfBoundsException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter an index: ");
		int index = sc.nextInt();
		if(index > shelf.numBooks() || index < 1) {
			throw new ArrayIndexOutOfBoundsException();
		}
		sc.nextLine();
		System.out.print("Please enter a recipient: ");
		String recipient = sc.nextLine();
		int condition = 0;
		boolean repeat = true;
		while(repeat) {
			System.out.print("Please enter condition (1-5): ");
			condition = sc.nextInt();
			if(condition >= 1 && condition <= 5) {
				repeat = false;
			} else {
				System.out.println("That number isn't between 1 and 5!!");
			}
		}
		shelf.getBook(index - 1).setBorrower(recipient);
		shelf.getBook(index - 1).setCondition(condition);
		System.out.println(shelf.getBook(index - 1).getTitle() + " has been loaned out to " + recipient + ".");
	}
	
	/**
	 * Prompts the user for information to remove a book on the shelf
	 * @param shelf
	 * 	The bookshelf object to remove the book in
	 * @throws ArrayIndexOutOfBoundsException
	 * 	If the user enters an index that does not properly correspond to a book in the array
	 * @throws EmptyShelfException
	 * 	If the bookshelf object is empty and therefore there is no book object to remove
	 */
	public static void removeBook(Bookshelf shelf) throws ArrayIndexOutOfBoundsException, EmptyShelfException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter an index: ");
		int index = sc.nextInt();
		Book b = shelf.removeBook(index - 1);
		System.out.println("Removed " + b.getTitle() + " at index " + index + ".\n");
	}
	
	/**
	 * Prompts the user for information to clone a book on the shelf. The borrower field is left blank.
	 * @param shelf
	 * 	The shelf to look for the book object in
	 * @throws ArrayIndexOutOfBoundsException
	 * 	If the user enters an index that does not properly correspond to a book in the books[] array
	 * @throws FullShelfException
	 * 	If the shelf is full and therefore a duplicate book cannot be added
	 */
	public static void duplicateBook(Bookshelf shelf) throws ArrayIndexOutOfBoundsException, FullShelfException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter a source index: ");
		int source = sc.nextInt();
		if(source > shelf.numBooks() || source < 1) {
			throw new ArrayIndexOutOfBoundsException();
		}
		System.out.print("Please enter a destination index: ");
		int destination = sc.nextInt();
		
		shelf.addBook(destination - 1, shelf.getBook(source - 1).clone());
		System.out.println("A copy of " + shelf.getBook(destination - 1).getTitle() + " has been added at position " + destination + ".\n");
	}
	
	/**
	 * Prompts the user for a new shelf to perform other commands on
	 * @param currentShelf
	 * 	The shelf that the user is currently looking at
	 * @param a
	 * 	The first shelf
	 * @param b
	 * 	The second shelf
	 * @param c
	 * 	The third shelf
	 * @return
	 * 	The selected shelf to look at
	 */
	public static Bookshelf changeShelf(Bookshelf currentShelf, Bookshelf a, Bookshelf b, Bookshelf c) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please select a shelf to look at (A, B, or C): ");
		String shelf = sc.nextLine().toUpperCase();
		if(shelf.equals("A")) {
			System.out.println("Shelf A selected.\n");
			RipoffRental.setName("A");
			return a;
		} else if(shelf.equals("B")) {
			System.out.println("Shelf B selected.\n");
			RipoffRental.setName("B");
			return b;
		} else if(shelf.equals("C")) {
			System.out.println("Shelf C selected.\n");
			RipoffRental.setName("C");
			return c;
		} else {
			System.out.println("That's not a valid shelf!\n");
			return currentShelf;
		}
	}
	
	/**
	 * Prints out the contents of the current shelf
	 * @param shelf
	 * 	The shelf the user is currently looking at
	 */
	public static void printShelf(Bookshelf shelf) {
		System.out.println("Shelf " + RipoffRental.getName() + ":");
		System.out.println("Spot Title\t\t\tAuthor\t\tCond.\tBorrower");
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println(shelf + "\n");
	}
	
	/**
	 * Replaces the contents of another shelf with those of the current shelf
	 * @param currentShelf
	 * 	The shelf whose contents will replace another shelf; the user is currently looking at this shelf
	 * @param a
	 * 	The first shelf
	 * @param b
	 * 	The second shelf
	 * @param c
	 * 	The third shelf
	 */
	public static void overwriteShelf(Bookshelf currentShelf, Bookshelf a, Bookshelf b, Bookshelf c) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please select a shelf to overwrite with the current shelf: ");
		String shelf = sc.nextLine().toUpperCase();
		if(shelf.equals("A")) {
			RipoffRental.setA(currentShelf.clone());
			System.out.println("Shelf A overwritten with copy of shelf " + RipoffRental.getName() + ".\n");
		} else if(shelf.equals("B")) {
			RipoffRental.setB(currentShelf.clone());
			System.out.println("Shelf B overwritten with copy of shelf " + RipoffRental.getName() + ".\n");
		} else if(shelf.equals("C")) {
			RipoffRental.setC(currentShelf.clone());
			System.out.println("Shelf C overwritten with copy of shelf " + RipoffRental.getName() + ".\n");
		} else {
			System.out.println("That shelf doesn't exist!\n");
		}
	}
	
	/**
	 * Determines whether or not two shelves of the user's choice are equal and prints out the result.
	 * A book's borrower field is not included when checking for equality.
	 * @param a
	 * 	The first shelf
	 * @param b
	 * 	The second shelf
	 * @param c
	 * 	The third shelf
	 */
	public static void checkEquality(Bookshelf a, Bookshelf b, Bookshelf c) {
		Scanner sc = new Scanner(System.in);
		Bookshelf shelf1 = null;
		Bookshelf shelf2 = null;
		System.out.print("Please select a shelf: ");
		String firstShelf = sc.nextLine();
		if(firstShelf.equalsIgnoreCase("A")) {
			shelf1 = a;
		} else if(firstShelf.equalsIgnoreCase("B")) {
			shelf1 = b;
		} else if(firstShelf.equalsIgnoreCase("C")) {
			shelf1 = c;
		} else {
			System.out.println("That's not a shelf!\n");
			return;
		}
		System.out.print("Please select another shelf: ");
		String secondShelf = sc.nextLine();
		if(secondShelf.equalsIgnoreCase("A")) {
			shelf2 = a;
		} else if(secondShelf.equalsIgnoreCase("B")) {
			shelf2 = b;
		} else if(secondShelf.equalsIgnoreCase("C")) {
			shelf2 = c;
		} else {
			System.out.println("That's not a shelf!\n");
			return;
		}
		boolean equal = shelf1.equals(shelf2);
		if(equal) {
			System.out.println("The two shelves are equal.\n");
		} else {
			System.out.println("The two shelves are not equal.\n");
		}
	}
}