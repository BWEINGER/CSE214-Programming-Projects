import java.util.*;

/**
 * This class represents the Ripoff Rental store. It contains three bookshelves, labeled A, B, and C. A variable is also used to keep track of the current shelf's name.
 * @author Brett
 */
public class RipoffRental {

	private static Bookshelf shelfA;
	private static Bookshelf shelfB;
	private static Bookshelf shelfC;
	private static String shelfName;
	
	/**
	 * A constructor that initializes each shelf and identifies shelf A as the current shelf
	 */
	public RipoffRental() {
		shelfA = new Bookshelf();
		shelfB = new Bookshelf();
		shelfC = new Bookshelf();
		shelfName = "A"; //start with first shelf
	}
	
	/**
	 * @return
	 * 	The first bookshelf object
	 */
	public static Bookshelf getA() {
		return shelfA;
	}
	
	/**
	 * This method is used to change the contents of the first bookshelf object
	 * @param shelf
	 * 	A bookshelf object to replace shelf A with
	 */
	public static void setA(Bookshelf shelf) {
		shelfA = shelf;
	}
	
	/**
	 * @return
	 * 	The second bookshelf object
	 */
	public static Bookshelf getB() {
		return shelfB;
	}
	
	/**
	 * This method is used to change the contents of the second bookshelf object
	 * @param shelf
	 * 	A bookshelf object to replace shelf B with
	 */
	public static void setB(Bookshelf shelf) {
		shelfB = shelf;
	}
	
	/**
	 * @return
	 * 	The third bookshelf object
	 */
	public static Bookshelf getC() {
		return shelfC;
	}
	
	/**
	 * This method is used to change the contents of the third bookshelf object
	 * @param shelf
	 * 	A bookshelf object to replace shelf C with
	 */
	public static void setC(Bookshelf shelf) {
		shelfC = shelf;
	}
	
	/**
	 * @return
	 * 	The name of the current bookshelf object
	 */
	public static String getName() {
		return shelfName;
	}
	
	/**
	 * This method is used to change the name of the current bookshelf so it can be displayed to the user when changing shelves
	 * @param name
	 * 	The name ("A", "B", or "C") of the current bookshelf
	 */
	public static void setName(String name) {
		shelfName = name;
	}
}