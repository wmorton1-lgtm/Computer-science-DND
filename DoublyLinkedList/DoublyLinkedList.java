import java.util.Objects;

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
		for (int i = 0; i < values.length; i++) {
			add(values[i]);
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
		if (obj == null) {
			for (ListNode2<Nucleotide> n = SENTINEL.getNext(); !n.equals(SENTINEL); n =
					n.getNext()) {
				if (n.getValue() == obj) {
					return index;
				}
				index++;
			}
		} else {
			for (ListNode2<Nucleotide> n = SENTINEL.getNext(); !n.equals(SENTINEL); n =
					n.getNext()) {
				if (n.getValue().equals(obj)) {
					return index;
				}
				index++;
			}
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
			nodeCount++;
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
		throw new IndexOutOfBoundsException(
				"get() -  the index was out of bounds & got thru check");
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
		throw new IndexOutOfBoundsException(
				"set(index, obj) -  the index was out of bounds & got thru check");
	}

	// // Inserts obj to become the i-th element. Increments the size
	// // of the list by one.
	public void add(int i, Nucleotide obj) {
		if (i > nodeCount || i < 0) {
			throw new IndexOutOfBoundsException("add(i, obj) -  the index was out of bounds");
		}
		if (i == nodeCount) {
			add(obj);
			return;
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
				return;
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
				nodeCount--;
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

		for (ListNode2<Nucleotide> n = SENTINEL.getNext(); !n.getNext().equals(SENTINEL); n =
				n.getNext()) {
			sting.append(n.getValue());
			sting.append(", ");
		}
		sting.append(SENTINEL.getPrevious().getValue());
		sting.append("]");
		return sting.toString();
	}

	// // Like question 7 on the SinglyLinkedList test:
	// // Add a "segment" (another list) onto the end of this list
	public void addSegmentToEnd(DoublyLinkedList seg) {
		if (seg.size() == 0) {
			return;
		}
		ListNode2<Nucleotide> lastFromSeg = seg.getSentinel().getPrevious();
		ListNode2<Nucleotide> firstFromSeg = seg.getSentinel().getNext();
		firstFromSeg.setPrevious(SENTINEL.getPrevious());
		SENTINEL.getPrevious().setNext(firstFromSeg);
		lastFromSeg.setNext(SENTINEL);
		SENTINEL.setPrevious(lastFromSeg);
		nodeCount += seg.size();
	}

	// // Like question 8 on the SinglyLinkedList test:
	// // You are to remove the next 16 nodes from the list, after the node
	// nodeBefore.
	// // (on the test these nodes were assumed to contain CCCCCCCCGGGGGGGG, but
	// here
	// // you do not need to assume or check for that)
	public void removeCCCCCCCCGGGGGGGG(ListNode2<Nucleotide> nodeBefore) {
		if (nodeCount < 16) {
			throw new IndexOutOfBoundsException("The linked list had less than 16 indexees");
		} else if (nodeCount == 16) {
			SENTINEL.setNext(SENTINEL);
			SENTINEL.setPrevious(SENTINEL);
			nodeCount = 0;
			return;
		} else {
			int index = 0;
			for (ListNode2<Nucleotide> n = nodeBefore; !n.getNext().equals(SENTINEL); n =
					n.getNext()) {
				if (index == 17) {
					nodeBefore.setNext(n);
					n.setPrevious(nodeBefore);
					nodeCount -= 16;
					return;
				}
				index++;
			}
		}


	}

	// // Like question 9 on the SinglyLinkedList test:
	// // You are to find and delete the first instance of seg in the list.
	// // If seg is not in the list, return false, otherwise return true.

	// instance variables FIX IF X
	public boolean deleteSegment(DoublyLinkedList seg) {
		if (seg.size() > nodeCount) {
			throw new IndexOutOfBoundsException(
					"deletesegment(seg) the requested segment was longer than the list where it should hav been removed");
		}

		ListNode2<Nucleotide> segHead = seg.getHead();


		for (ListNode2<Nucleotide> n = SENTINEL.getNext(); n != SENTINEL; n = n.getNext()) {
			if (n.getValue().equals(segHead.getValue())) {
				ListNode2<Nucleotide> beforeStart = n.getPrevious();
				ListNode2<Nucleotide> currentNode = n;
				ListNode2<Nucleotide> segNode = segHead;



				while (segNode != seg.getSentinel() && currentNode != SENTINEL
						&& Objects.equals(currentNode.getValue(), segNode.getValue())) {
					currentNode = currentNode.getNext();
					segNode = segNode.getNext();
				}

				if (segNode == seg.getSentinel()) {
					beforeStart.setNext(currentNode);
					currentNode.setPrevious(beforeStart);
					if (beforeStart == SENTINEL) {
						SENTINEL.setNext(currentNode);
					}
					nodeCount -= seg.size();
					return true;
				}
			}

		}
		return false;

	}

	// // Like question 10 on the SinglyLinkedList test:
	// // Delete the last three nodes in the list
	// // If there are not enough nodes, return false
	public boolean deleteLastThree() {
		if (3 > nodeCount) {
			throw new IndexOutOfBoundsException(
					"deletelastThree() the list's length is shorter than 3");
		}
		if (nodeCount == 3) {
			SENTINEL.setNext(SENTINEL);
			SENTINEL.setPrevious(SENTINEL);
			nodeCount = 0;
			return true;
		}
		ListNode2<Nucleotide> nodeBeforeLast3 =
				SENTINEL.getPrevious().getPrevious().getPrevious().getPrevious();
		nodeBeforeLast3.setNext(SENTINEL);
		SENTINEL.setPrevious(nodeBeforeLast3);
		nodeCount -= 3;
		return true;

	}

	// // Like question 11 on the SinglyLinkedList test:
	// // Replaces every node containing "A" with three nodes containing "T" "A" "C"
	public void replaceEveryAWithTAC() {
		for (ListNode2<Nucleotide> n = SENTINEL.getNext(); n != SENTINEL; n = n.getNext()) {
			if (n.getValue() == null) {

			} else if (n.getValue().equals(Nucleotide.A)) {
				ListNode2<Nucleotide> afterN = n.getNext();
				ListNode2<Nucleotide> beforeN = n.getPrevious();
				ListNode2<Nucleotide> addBeforeN =
						new ListNode2<Nucleotide>(Nucleotide.T, beforeN, n);
				ListNode2<Nucleotide> addAfterN =
						new ListNode2<Nucleotide>(Nucleotide.C, n, afterN);
				afterN.setPrevious(addAfterN);
				n.setNext(addAfterN);
				beforeN.setNext(addBeforeN);
				n = addAfterN;
				nodeCount += 2;
			}
		}
	}

}
