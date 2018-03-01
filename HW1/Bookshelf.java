/**
 * This class represents a bookshelf filled with up to twenty books. It keeps track of how many books currently on the shelf.
 * @author Brett Weinger, ID #: 111639717
 */

public class Bookshelf {
	
	//Declare instance variables
	private Book[] books;
	private int count;
	final int CAPACITY = 20;
	
	/**
	 * This is a constructor used to create a bookshelf object. Since CAPACITY is initialized to 20, that is also the array size of books.
	 * There are initially no books on the shelf
	 */
	public Bookshelf() {
		books = new Book[CAPACITY];
		count = 0;
	}
	
	/**
	 * @return
	 * 	Returns number of non-null book objects on shelf
	 */
	public int numBooks() {
		return count; //looping through array runs in O(n) time, not O(1)
	}
	
	/**
	 * Retrieves a book on the shelf via its index
	 * @param index
	 * 	The index in the books[] array to locate the book object
	 * @return
	 * 	The book at the specified index
	 * @throws ArrayIndexOutOfBoundsException 
	 * 	When trying to retrieve a book at an empty index or one outside the bounds of books[]
	 */
	public Book getBook(int index) throws ArrayIndexOutOfBoundsException {
		try {
			if(index >= count) {
				throw new ArrayIndexOutOfBoundsException(); //run catch-block immediately if index does not correspond to a book
			}
			Book b = books[index]; //runs catch-block if index >= CAPACITY
			return b;
		} catch(ArrayIndexOutOfBoundsException i) {
			System.out.println("Invalid index!");
			return null;
		}
	}
	
	/**
	 * Removes a book on the shelf via its index. All books to the right in books[] are shifted to the left.
	 * @param index
	 * 	The index in the books[] array to locate and remove the book object
	 * @return
	 * 	The book object that was removed
	 * @throws ArrayIndexOutOfBoundsException
	 * 	If the specified index does not correspond to a book object in the books[] array
	 * @throws EmptyShelfException
	 * 	If there are no books on the shelf and therefore none can be removed
	 */
	public Book removeBook(int index) throws ArrayIndexOutOfBoundsException, EmptyShelfException {
		if(count == 0) {
			throw new EmptyShelfException();
		} else if(index >= count) {
			throw new ArrayIndexOutOfBoundsException();
		}
		Book b = books[index]; //throws exception if index is invalid
		int i = index;
		while(books[i] != null) { //If b initialized w/o exception thrown, i < CAPACITY
			if(i == CAPACITY - 1) { //when dealing with final array index, cannot move next book in its place; only set to null and exit loop
				books[i] = null;
				break;
			} else { //dealing with any other array index; replace with next book
				books[i] = books[i + 1];
			}
			i++;
		}
		count--;
		return b;
	}
	
	/**
	 * Adds a book to the shelf at a given index. All books at the specified index and after are shifted to the right.
	 * @param index
	 * 	The index in books[] to add the book
	 * @param book
	 * 	The book object to add to the books[] array
	 * @throws IllegalArgumentException
	 * 	If adding the book at the specified index would create a hole in the array
	 * @throws FullShelfException
	 * 	If the shelf has reached its CAPACITY of book objects and another one cannot be added
	 */
	public void addBook(int index, Book book) throws IllegalArgumentException, FullShelfException {
		if(count == CAPACITY) {
			throw new FullShelfException();
		} else if(index > count) {
			throw new IllegalArgumentException();
		}
		int i = count;
		while(i > index) {
			books[i] = books[i - 1];
			i--;
		}
		books[index] = book;
		count++;
	}

	/**
	 *Swaps the order of two book objects in the books[] array
	 * @param index1
	 * 	The index of the first book to swap
	 * @param index2
	 * 	The index of the second book to swap
	 * @throws ArrayIndexOutOfBoundsException
	 * 	If the indexes do not correspond to books object in the array
	 */
	public void swapBooks(int index1, int index2) throws ArrayIndexOutOfBoundsException {
		if(books[index1] == null || books[index2] == null) {
			throw new ArrayIndexOutOfBoundsException();
		}
		Book temp = books[index1];
		books[index1] = books[index2];
		books[index2] = temp;
	}
	
	/**
	 * Copies the bookshelf object
	 * @returns
	 * 	A clone of the bookshelf, including clones of all the books in its internal books[] array. The borrower field of those books are left blank.
	 * @exception FullShelfException
	 * 	The loop will end before the new shelf could become full, but Java requires FullShelfException to be caught within clone()
	 */
	public Bookshelf clone() {
		Bookshelf shelf = new Bookshelf();
		try {
			for(int i = 0; i < count; i++) {
				shelf.addBook(i, this.books[i].clone());
			}
			return shelf;
		} catch(FullShelfException i ) {
			return null;
		}
	}
	
	/**
	 * Checks if the shelf is equal to another object. Equality does not include the books' borrower fields.
	 * @param o
	 * 	An object to compare the shelf to
	 * @return
	 * 	A boolean representing whether or not the two objects are equal
	 */
	public boolean equals(Object o) {
		if(o instanceof Bookshelf) { //Must be able to cast obj to a book
			Bookshelf shelf = (Bookshelf)o;
			if(count == shelf.numBooks()) {
				for(int i = 0; i < count; i++) {
					if(!books[i].equals(shelf.getBook(i))) {
						return false; //inequality
					}
				}
				return true; //finished looping through entire shelf without returning false 
			}
		}
		return false;
	}
	
	/**
	 * This method is used to print out the bookshelf's contents
	 * @return
	 * 	A string containing information regarding each book in the internal books[] array
	 */
	public String toString() {
		String s = "";
		for(int i = 0; i < count; i++) {
			s += books[i];
			if(i < count - 1) { //will iterate again
				s += "\n";
			}
		}
		return s;
	}
}

/**
 * This class is an exception that is thrown when dealing with an empty shelf is problematic.
 * @author Brett
 */
class EmptyShelfException extends Exception { //default constructor auto-initialized
}

/**
 * This class is an exception that is thrown when dealing with a full shelf is problematic.
 * @author Brett
 */
class FullShelfException extends Exception { //see comment above
}