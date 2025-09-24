// Implements a singly-linked list.


public class SinglyLinkedList<E> {
	private ListNode<E> head;
	private ListNode<E> tail;
	private int nodeCount;

	// Constructor: creates an empty list
	public SinglyLinkedList() {
		// SinglyLinkedList<E> nodeList = new SinglyLinkedList<E>();
		head = new ListNode<E>(null);
		tail = new ListNode<E>(null);
		nodeCount = 0;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public SinglyLinkedList(Object[] values) {}

	public ListNode<E> getHead() {
		return head;
	}

	public ListNode<E> getTail() {
		return tail;
	}

	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		return (head == null || tail == null || nodeCount == 0);
	}

	// Returns the number of elements in this list.
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(E obj) {
		ListNode<E> first = head;
		ListNode<E> last = tail;
		
		for (ListNode<E> current = first; !current.equals(last);) {
			ListNode<E> becomeCurrent = new ListNode<E>(current.getNext().getValue(), current.getNext());
			if (becomeCurrent.equals(obj)) {
				return true;
			}
		}
		return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(E obj) {
		if (!contains(obj)) {
			return -1;
		}
		ListNode<E> first = head;
		ListNode<E> last = tail;
		int index = 0;
		for (ListNode<E> current = first; !current.equals(last); index++) {
			ListNode<E> becomeCurrent = new ListNode<E>(current.getNext().getValue(), current.getNext());
			if (becomeCurrent.equals(obj)) {
				return index;
			}
		}
		return -1;
	}

	// Adds obj to this collection. Returns true if successful;
	// otherwise returns false.
	public boolean add(E obj) {
		ListNode<E> objNode = new ListNode<E>(obj);
		tail.setNext(objNode);


	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(E obj) {

	}

	// Returns the i-th element.
	public E get(int i) {}

	// Replaces the i-th element with obj and returns the old value.
	public E set(int i, Object obj) {}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Object obj) {}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public E remove(int i) {

	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {


	}


}
