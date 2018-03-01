/**
 * This class represents a list of delivery nodes, containing pointers to the start of the list, the end of the list, and a cursor that tracks the current node in the list
 * @author Brett Weinger, ID #: 111639717
 */
public class DeliveryList {
	private DeliveryListNode head;
	private DeliveryListNode tail;
	private DeliveryListNode cursor;
	private int manyItems;
	
	/**
	 * This is a default constructor used to create a new list with all node instance variables initialized to null and the counter set to zero items
	 */
	public DeliveryList() {
		head = null;;
		tail = null;
		cursor = null;
		manyItems = 0;
	}
	
	/**
	 * @return
	 * 	The delivery node object at the beginning of the list
	 */
	public DeliveryListNode getHead() {
		return head;
	}

	/**
	 * This method changes the pointer to the first node in the list
	 * @param head
	 * 	The new node to be placed at the beginning of the list
	 */
	public void setHead(DeliveryListNode head) {
		this.head = head;
	}

	/**
	 * @return
	 * 	The delivery node object at the end of the list
	 */
	public DeliveryListNode getTail() {
		return tail;
	}

	/**
	 * This method changes the pointer to the last node in the list
	 * @param tail
	 * 	The new node to be placed at the end of the list
	 */
	public void setTail(DeliveryListNode tail) {
		this.tail = tail;
	}

	/**
	 * This method changes the pointer to the current node being worked with in the list
	 * @param cursor
	 * 	The new node to replace the cursor pointer with
	 */
	public void setCursor(DeliveryListNode cursor) {
		this.cursor = cursor;
	}

	/**
	 * @return
	 * 	The number of delivery nodes (equal to the number of deliveries) in the list
	 */
	public int numDeliveries() {
		return manyItems;
	}
	
	/**
	 * @return
	 * 	The current delivery object wrapped in the node that the user is working with (or simply the first, if left unchanged)
	 * 	Returns null if the cursor does not point to any node and therefore contains no data
	 */
	public Delivery getCursor() {
		if(cursor != null) { //avoids null pointer exception
			return cursor.getData();
		} else {
			return null;
		}
	}
	
	/**
	 * This method changes the cursor pointer to the first node in the list
	 */
	public void resetCursorToHead() {
		if(head != null) {
			cursor = head;
		} else {
			cursor = null;
		}
	}
	
	/**
	 * This method changes the cursor pointer to the next node in the list
	 * @throws EndOfListException
	 *	If there is no next node in the list to advance the cursor to
	 */
	public void cursorForward() throws EndOfListException {
		if(cursor != tail) {
			cursor = cursor.getNext();
		} else {
			throw new EndOfListException();
		}
	}
	
	/**
	 * This method changes the cursor pointer to the previous node in the list
	 * @throws EndOfListException
	 *	If there is no previous node in the list to move the cursor to
	 */
	public void cursorBackward() throws EndOfListException {
		if(cursor != head) {
			cursor = cursor.getPrev();
		} else {
			throw new EndOfListException();
		}
	}
	
	/**
	 * This method adds a new node to the list after the cursor pointer and changes any links accordingly
	 * The values of the head, tail, and cursor instance variables are updated as well
	 * @param newDelivery
	 * 	A delivery object to be wrapped in a node before being added to the list
	 * @throws IllegalArgumentException
	 *	If the user attempts to add null data to the list
	 */
	public void insertAfterCursor(Delivery newDelivery) throws IllegalArgumentException {
		if(newDelivery == null) {
			throw new IllegalArgumentException();
		}
		DeliveryListNode nodePTR = new DeliveryListNode(newDelivery);
		if(cursor != null) {
			nodePTR.setNext(cursor.getNext());
			nodePTR.setPrev(cursor);
			if(cursor.getNext() != null) {
				cursor.getNext().setPrev(nodePTR);
			}
			cursor.setNext(nodePTR);
			if(nodePTR.getNext() == null) { //cursor was the last item
				tail = nodePTR; //update tail to inserted item
			}
		} else { //update head and tail to inserted item
			head = nodePTR;
			tail = nodePTR;
			cursor = nodePTR;
		}
		manyItems++;
	}
	
	/**
	 * This method adds a new node to the end of the list after the tail pointer and changes any links accordingly
	 * The values of the head, tail, and cursor instance variables are updated as well
	 * @param newDelivery
	 * 	A delivery object to be wrapped in a node before being added to the list
	 * @throws IllegalArgumentException
	 *	If the user attempts to add null data to the list
	 */
	public void appendToTail(Delivery newDelivery) throws IllegalArgumentException {
		if(newDelivery == null) {
			throw new IllegalArgumentException();
		}
		DeliveryListNode nodePTR = new DeliveryListNode(newDelivery);
		if(tail != null) { //new item's next already null, and tail's previous remains unchanged
			nodePTR.setPrev(tail);
			tail.setNext(nodePTR);
		} else {
			head = nodePTR;
			cursor = nodePTR;
		}
		tail = nodePTR;
		manyItems++;
	}
	
	/**
	 * This method removes the node from the list indicated by the cursor pointer and changes any links accordingly
	 * The values of the head, tail, and cursor instance variables are updated as well
	 * @throws EndOfListException
	 *	If the cursor does not point to any node and data therefore cannot be removed
	 */
	public Delivery removeCursor() throws EndOfListException {
		if(cursor == null) {
			throw new EndOfListException();
		}
		Delivery storedDelivery = cursor.getData();
		if(cursor != head && cursor != tail) { //not head or tail
			cursor.getPrev().setNext(cursor.getNext());
			cursor.getNext().setPrev(cursor.getPrev());
			if(cursor != tail) {
				cursor = cursor.getNext();
			} else {
				cursor = cursor.getPrev();
				tail = cursor;
			}
		} else if(cursor == head) { //removing head
			cursor.getNext().setPrev(null);
			cursor = cursor.getNext();
			head = cursor;
			if(head == null) {
				tail = null;
			}	
		} else { //removing tail
			cursor.getPrev().setNext(null);
			cursor = cursor.getPrev();
			tail = cursor;
			if(tail == null) {
				head = null;
			}
		}
		manyItems--;
		return storedDelivery; //return original cursor delivery object
	}
}

/**
 * This class is a subclass of Exception and is thrown when the user exceeds the bounds of the list
 * @author Brett Weinger, ID #: 111639717
 */
class EndOfListException extends Exception {
}
