/**
 * This class is used to wrap a delivery object and provides links to two other similarly wrapped objects
 * @author Brett Weinger, ID #: 111639717
 */
public class DeliveryListNode {
	private Delivery data;
	private DeliveryListNode next;
	private DeliveryListNode prev;
	
	/**
	 * This is a default constructor used to create a new object with all instance variables initialized to null
	 */
	public DeliveryListNode() {
		data = null;
		next = null;
		prev = null;
	}
	
	/**
	 * This is a constructor used to create a node object containing a delivery subject to user input
	 * @param initData
	 *	A delivery object containing information about source and destination locations and special instructions
	 * @throws IllegalArgumentException
	 * 	If the user tries to create the node with no data
	 */
	public DeliveryListNode(Delivery initData) throws IllegalArgumentException {
		if(initData == null) {
			throw new IllegalArgumentException();
		} else {
			data = initData;
		}
		next = null;
		prev =  null;
	}

	/**
	 * @return
	 * 	The delivery object stored within the node
	 */
	public Delivery getData() {
		return data;
	}

	/**
	 * This method changes the node's internal delivery object
	 * @param data
	 * 	The new delivery to be wrapped within the node
	 */
	public void setData(Delivery data) {
		this.data = data;
	}

	/**
	 * @return
	 * 	Another node at the location logically to the right of the current node
	 */
	public DeliveryListNode getNext() {
		return next;
	}

	/**
	 * This method changes the next node that the current node points to
	 * @param next
	 * 	The new node to be logically to the right of the current node
	 */
	public void setNext(DeliveryListNode next) {
		this.next = next;
	}

	/**
	 * @return
	 * 	Another node at the location logically to the left of the current node
	 */
	public DeliveryListNode getPrev() {
		return prev;
	}

	/**
	 * This method changes the previous node that the current node points to
	 * @param prev
	 * 	The new node to be logically to the left of the current node
	 */
	public void setPrev(DeliveryListNode prev) {
		this.prev = prev;
	}
}
