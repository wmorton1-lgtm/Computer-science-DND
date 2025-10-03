// Implements a singly-linked list.

import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import javax.naming.directory.InvalidSearchControlsException;

public class SinglyLinkedList<E> {
	private ListNode<E> head;
	private ListNode<E> tail;
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

	public ListNode<E> getHead() {
		return head;
	}

	public ListNode<E> getTail() {
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
		if (obj.equals(null)) {
			throw new NullPointerException("IndexOf() OBJECT PARAMETER WAS NULL");
		}
		int index = 0;
		for (ListNode<E> n = head; n != null; n = n.getNext()) {
			if (n.getValue().equals(obj)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	// Adds obj to this collection. Returns true if successful;
	// otherwise returns false.
	public boolean add(E obj) {
		if (obj.equals(null) || obj == null) {
			return false;
		}

		ListNode<E> objNode = new ListNode<E>(obj);
		if (head == null && nodeCount == 0) {
			head = objNode;
			tail = objNode;
			head.setNext(tail);
		}
		tail.setNext(objNode);
		tail = objNode;
		tail.setNext(null);
		nodeCount++;
		return true;

	}


	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException("remove(obj) parameter was null ");
		}
		int objIndex = indexOf(obj);
		if (objIndex == -1) {
			return false;
		} else {
			if (remove(objIndex) == obj) {
				return true;
			}
		}
		return false;
	}

	// Returns the i-th element.
	public E get(int i) {
		if (i > nodeCount || i < 0) {
			throw new IndexOutOfBoundsException("Get(i) index was out of range");
		}
		int index = 0;
		for (ListNode<E> n = head; n != null; n = n.getNext()) {
			if (index == i) {
				return n.getValue();
			}
			index++;
		}
		throw new IllegalStateException(
				"get(i) the index made it thru the edge cases and somehow nothing was returned");
	}



	// Replaces the i-th element with obj and returns the old value.
	public E set(int i, Object obj) {
		if (i > nodeCount - 1 || i < 0) {
			throw new IllegalArgumentException("Set(i, obj) index was out of range");
		}
		E objE = (E) obj;
		int index = 0;
		for (ListNode<E> n = head; n != null; n = n.getNext()) {
			if (index == i) {
				E toReturn = n.getValue();
				n.setValue(objE);
				return toReturn;
			}
			index++;
		}
		throw new IllegalAccessError("set()-nothing was set & thusforth nothing returned");
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	// instance variables FIX FIX FIX FIX FIX FIX FIX FIX FIX FIX FIX FIX FIX FIX FIX
	public void add(int i, Object obj) {
		if (i < 0 || i > nodeCount - 1) {
			throw new IndexOutOfBoundsException("add(index, obj) - index was out of bounds");
		}
		if (obj == null) {
			throw new NullPointerException("add(index, obj) - obj was null");
		}

		ListNode<E> objNode = new ListNode<E>((E) obj);
		if (i == 0) {
			add((E) obj);
			for (ListNode<E> n = head; n.getNext().getNext() != null; n = n.getNext()) {
				n.getNext().setValue(n.getValue());
			}
			head.setValue((E) obj);
			


		} else {
			int index = 0;
			for (ListNode<E> n = head; n != null; n = n.getNext()) {
				if (index == i - 1) {
					objNode.setNext(n.getNext());
					n.setNext(objNode);
					nodeCount++;
				}
				index++;
			}
		}
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.


	public E remove(int i) {
		if (i > nodeCount - 1 || i < 0) {
			throw new IllegalArgumentException(
					"Remove(index) The index requested was out of range");
		}
		int index = 0;
		for (ListNode<E> n = head; n != null; n = n.getNext()) {
			if (index == i - 1) {
				E toReturn = n.getNext().getValue();
				n.setNext(n.getNext().getNext());
				nodeCount--;
				return toReturn;
			}
			index++;
		}
		throw new IllegalAccessError("The index was out of range & not removed (ohshit)");
	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		StringBuilder toReturn = new StringBuilder("[");
		for (ListNode<E> n = head; n.getNext() != null; n = n.getNext()) {
			toReturn.append(n.getValue().toString());
			toReturn.append(", ");
		}
		toReturn.append(tail.getValue());
		return toReturn.append("]").toString();

	}

}
