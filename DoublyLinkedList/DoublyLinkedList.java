
public class DoublyLinkedList {
	// Implements a circular doubly-linked list.

	private final ListNode2<Nucleotide> SENTINEL = new ListNode2<Nucleotide>(null);
	private int nodeCount;

	// Constructor: creates an empty list
	public DoublyLinkedList() {
		nodeCount = 0;
		SENTINEL.setNext(SENTINEL);
		SENTINEL.setPrevious(SENTINEL);
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public DoublyLinkedList(Nucleotide[] values) {
		SENTINEL.setNext(SENTINEL);
		SENTINEL.setPrevious(SENTINEL);
		int index = 0;
		for (int i = 0; i < values.length; i++) {
			add(values[index]);
			index++;
		}
	}

	public ListNode2<Nucleotide> getSentinel() {
		return SENTINEL;
	}

	public ListNode2<Nucleotide> getHead() {
		return SENTINEL.getNext();
	}

	public ListNode2<Nucleotide> getTail() {
		return SENTINEL.getPrevious();
	}

	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		return nodeCount == 0;
	}

	// Returns the number of elements in this list.
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(Nucleotide obj) {
		return indexOf(obj) != -1;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(Nucleotide obj) {
		int index = 0;
		for (ListNode2<Nucleotide> n = SENTINEL.getNext(); !n.getNext().equals(SENTINEL); n = n.getNext()) {
			if (n.getValue() == obj) {
				return index;
			}
			index++;
		}
		return -1;
	}

	// Adds obj to this collection. Returns true if successful;
	// otherwise returns false.
	public boolean add(Nucleotide obj) {
		ListNode2<Nucleotide> toAdd = new ListNode2<Nucleotide>(obj);
		if (obj == null) {
			toAdd.setPrevious(SENTINEL.getPrevious());
			SENTINEL.getPrevious().setNext(toAdd);
			toAdd.setNext(SENTINEL);
			SENTINEL.setPrevious(toAdd);
		} else {
			toAdd.setPrevious(SENTINEL.getPrevious());
			SENTINEL.getPrevious().setNext(toAdd);
			toAdd.setNext(SENTINEL);
			SENTINEL.setPrevious(toAdd);
			nodeCount++;
		}
		return true;
	}

	// // Removes the first element that is equal to obj, if any.
	// // Returns true if successful; otherwise returns false.
	public boolean remove(Nucleotide obj) {
		int index = 0;
		int objIndex = indexOf(obj);
		if (objIndex == -1) {
			return false;
		}
		for (ListNode2<Nucleotide> n = SENTINEL.getNext(); !n.equals(SENTINEL); n = n.getNext()) {
			if (index == objIndex) {
				n.getNext().setPrevious(n.getPrevious());
				n.getPrevious().setNext(n.getNext());
				nodeCount--;
				return true;
			}
			index++;
		}
		return false;
	}

	// // Returns the i-th element.
	public Nucleotide get(int i) {
		if (i >= nodeCount || i < 0) {
			throw new IndexOutOfBoundsException("get() -  the inex was out of bounds");
		}
		int index = 0;
		for (ListNode2<Nucleotide> n = SENTINEL.getNext(); !n.equals(SENTINEL); n = n.getNext()) {
			if (index == i) {
				return n.getValue();
			}
			index++;
		}
		throw new IndexOutOfBoundsException("get() -  the index was out of bounds & got thru check");
	}

	// // Replaces the i-th element with obj and returns the old value.
	public Nucleotide set(int i, Nucleotide obj) {
		if (i >= nodeCount || i < 0) {
			throw new IndexOutOfBoundsException("set(index, obj) -  the inex was out of bounds");
		}
		if (obj == null) {
			throw new NullPointerException("set(index, obj) - nucleotide was null");
		}
		int index = 0;
		for (ListNode2<Nucleotide> n = SENTINEL.getNext(); !n.equals(SENTINEL); n = n.getNext()) {
			if (index == i) {
				Nucleotide toreturn = n.getValue();
				n.setValue(obj);
				return toreturn;
			}
			index++;
		}
		throw new IndexOutOfBoundsException("set(index, obj) -  the index was out of bounds & got thru check");
	}

	// // Inserts obj to become the i-th element. Increments the size
	// // of the list by one.
	public void add(int i, Nucleotide obj) {
		if (i > nodeCount || i < 0) {
			throw new IndexOutOfBoundsException("add(i, obj) -  the inex was out of bounds");
		}
		int index = 0;
		ListNode2<Nucleotide> objNode = new ListNode2<Nucleotide>(obj);

		for (ListNode2<Nucleotide> n = SENTINEL.getNext(); !n.equals(SENTINEL); n = n.getNext()) {
			if (i == index) {
				objNode.setPrevious(n.getPrevious());
				n.getPrevious().setNext(objNode);
				objNode.setNext(n);
				n.setPrevious(objNode);
				nodeCount++;
			}
			index++;
		}
	}

	// // Removes the i-th element and returns its value.
	// // Decrements the size of the list by one.
	// instance variables FIX FIX FIX FIX FIX FIX FIX FIX FIX FIX 
	public Nucleotide remove(int i) {
		if (i >= nodeCount || i < 0) {
			throw new IndexOutOfBoundsException("remove(i) -  the inex was out of bounds");
		}
		int index = 0;
		for (ListNode2<Nucleotide> n = SENTINEL.getNext(); !n.equals(SENTINEL); n = n.getNext()) {
			if (i == index) {
				Nucleotide toReturn = n.getValue();
				n.getPrevious().setNext(n.getNext());
				n.getNext().setPrevious(n.getPrevious());
				return toReturn;
			}
			index++;
		}
		throw new IndexOutOfBoundsException("remove(i) - index passed check & nothing was removed");
	}

	// Returns a string representation of this list exactly like that for
	// MyArrayList.
	public String toString() {
		StringBuilder sting = new StringBuilder("[");

		for (ListNode2<Nucleotide> n = SENTINEL.getNext(); !n.getNext().equals(SENTINEL); n = n.getNext()) {
			sting.append(n.getValue());
			sting.append(", ");
		}
		sting.append(SENTINEL.getPrevious().getValue());
		sting.append("]");
		return sting.toString();
	}

	// // Like question 7 on the SinglyLinkedList test:
	// // Add a "segment" (another list) onto the end of this list
	// public void addSegmentToEnd(DoublyLinkedList seg) {

	// }

	// // Like question 8 on the SinglyLinkedList test:
	// // You are to remove the next 16 nodes from the list, after the node
	// nodeBefore.
	// // (on the test these nodes were assumed to contain CCCCCCCCGGGGGGGG, but
	// here
	// // you do not need to assume or check for that)
	// public void removeCCCCCCCCGGGGGGGG(ListNode2<Nucleotide> nodeBefore) {

	// }

	// // Like question 9 on the SinglyLinkedList test:
	// // You are to find and delete the first instance of seg in the list.
	// // If seg is not in the list, return false, otherwise return true.
	// public boolean deleteSegment(DoublyLinkedList seg) {

	// }

	// // Like question 10 on the SinglyLinkedList test:
	// // Delete the last three nodes in the list
	// // If there are not enough nodes, return false
	// public boolean deleteLastThree() {

	// }

	// // Like question 11 on the SinglyLinkedList test:
	// // Replaces every node containing "A" with three nodes containing "T" "A" "C"
	// public void replaceEveryAWithTAC() {

	// }

}
