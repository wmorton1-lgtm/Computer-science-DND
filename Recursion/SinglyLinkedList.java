// Implements a singly-linked list.

import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import javax.naming.directory.InvalidSearchControlsException;

public class SinglyLinkedList<E> {
	private ListNode head;
	private ListNode tail;
	private int nodeCount;

	// Constructor: creates an empty list
	public SinglyLinkedList() {
		// SinglyLinkedList<E> nodeList = new SinglyLinkedList<E>();
		this.head = null;
		this.tail = null;
		nodeCount = 0;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public SinglyLinkedList(Object[] values) {
		this.head = null;
		this.tail = head;
		for (int i = 0; i < values.length; i++) {
			add((E) values[i]);
		}
	}

	public ListNode getHead() {
		return head;
	}

	public ListNode getTail() {
		return tail;
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
	public boolean contains(E obj) {
		return indexOf(obj) != -1;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(E obj) {
		int index = 0;
		if (obj == null) {
			for (ListNode n = head; n != null; n = n.getNext()) {
				if (n.getValue() == obj) {
					return index;
				}
				index++;
			}
		} else {
			for (ListNode n = head; n != null; n = n.getNext()) {
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
	public boolean add(E obj) {
		ListNode objNode = new ListNode(obj);
		if (head == null) {
			head = objNode;
			tail = objNode;

		} else {
			tail.setNext(objNode);
			tail = objNode;

		}
		nodeCount++;
		return true;
	}



	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(E obj) {
		int objIndex = indexOf(obj);
		if (objIndex == -1) {
			return false;
		} else {
			if (remove(objIndex).equals(obj)) {
				return true;
			}
		}
		return false;
	}

	// Returns tashe i-th element.
	public Object get(int i) {
		if (i > nodeCount - 1 || i < 0) {
			throw new IndexOutOfBoundsException("Get(i) index was out of range");
		}
		int index = 0;
		for (ListNode n = head; n != null; n = n.getNext()) {
			if (index == i) {
				return n.getValue();
			}
			index++;
		}
		throw new IllegalStateException(
				"get(i) the index made it thru the edge cases and somehow nothing was returned");
	}

	// Replaces the i-th element with obj and returns the old value.
	public Object set(int i, Object obj) {
		if (i > nodeCount - 1 || i < 0) {
			throw new IndexOutOfBoundsException("Set(i, obj) index was out of range");
		}
		int index = 0;
		for (ListNode n = head; n != null; n = n.getNext()) {
			if (index == i) {
				Object toReturn = n.getValue();
				n.setValue(obj);
				return toReturn;
			}
			index++;
		}
		throw new IllegalAccessError("set()-nothing was set & thusforth nothing returned");
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.

	public void add(int i, Object obj) {
		if (i < 0 || i > nodeCount) {
			throw new IndexOutOfBoundsException("add(index, obj) - index was out of bounds");
		}

		ListNode objNode = new ListNode(obj);

		if (i == 0) {
			objNode.setNext(head);
			head = objNode;
			if (nodeCount == 0) {
				tail = objNode;
			}
			nodeCount++;
		} else {
			int index = 0;
			for (ListNode n = head; n != null; n = n.getNext()) {
				if (index == i - 1) {
					objNode.setNext(n.getNext());
					n.setNext(objNode);
					if (objNode.getNext() == null) {
						tail = objNode;
					}
					nodeCount++;
				}
				index++;
			}
		}
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.

	public Object remove(int i) {
		if (i >= nodeCount || i < 0) {
			throw new IndexOutOfBoundsException(
					"Remove(index) The index requested was out of range");
		}

		if (i == 0) {
			Object toReturn = head.getValue();
			head = head.getNext();
			nodeCount--;
			return toReturn;
		}

		int index = 0;
		if (i == nodeCount - 1) {
			Object toReturn = tail.getValue();
			for (ListNode n = head; n != null; n = n.getNext()) {
				if (n.getNext().equals(tail)) {
					tail = n;
					tail.setNext(null);
					nodeCount--;
					return toReturn;
				}
			}
		} else {
			for (ListNode n = head; n != null; n = n.getNext()) {
				if (index == i - 1) {
					Object toReturn = n.getNext().getValue();
					n.setNext(n.getNext().getNext());
					nodeCount--;
					return toReturn;
				}
				index++;
			}
		}
		throw new IllegalAccessError("The index was out of range & not removed (bad)");
	}

	// Returns a string representation of this list exactly like that for
	// MyArrayList.
	public String toString() {
		if (nodeCount == 0) {
			return "[]";
		}
		StringBuilder toReturn = new StringBuilder("[");
		for (ListNode n = head; n.getNext() != null; n = n.getNext()) {
			toReturn.append(n.getValue().toString());
			toReturn.append(", ");
		}
		toReturn.append(tail.getValue());
		return toReturn.append("]").toString();

	}

}
