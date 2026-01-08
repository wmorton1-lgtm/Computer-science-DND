// Implements a BST with BinaryNode nodes

public class MyBST<E extends Comparable<E>> {
	
	private BinaryNode<E> root;  // holds the root of this BST

	// Constructor: creates an empty BST.
	public MyBST() {
		root = null;
	}

	public BinaryNode<E> getRoot() {
		return root;
	}
	
	public int getHeight() {
		return root.getHeight();
	}

	// Returns true if this BST contains value; otherwise returns false.
	public boolean contains(E value) {
		return false;
	}

	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.
	public boolean add(E value) {
		if (this.contains(value)) {
			return false;
		}
		return addHelper(root, value);
	}

	public boolean addHelper(BinaryNode<E> currNode, E value) {
		if (currNode == null) {
			return false;
		}
		if (currNode.getLeft() == null && currNode.getValue().compareTo(value) < 0) {
			BinaryNode<E> toAdd = new BinaryNode<E>(value);
			currNode.setLeft(toAdd);
			toAdd.setParent(currNode);
			return true;
		}
		if (currNode.getRight() == null && currNode.getValue().compareTo(value) > 0) {
			BinaryNode<E> toAdd = new BinaryNode<E>(value);
			currNode.setRight(toAdd);
			toAdd.setParent(currNode);
			return true;
		}

		if (value.compareTo(currNode.getValue()) > 0) {
			addHelper(currNode.getRight(), value);
		} else {
			addHelper(currNode.getLeft(), value);
		}
		return false;
	}

	// Removes value from this BST.  Returns true if value has been
	// found and removed; otherwise returns false.
	// If removing a node with two children: replace it with the
	//  largest node in the right subtree
	public boolean remove(E value) {
		return false;
	}
	
	// Returns the minimum in the tree
	public E min() {
		
	}
	
	// Returns the maximum in the tree.
	public E max() {
		return null;
	}

	// Returns a bracket-surrounded, comma separated list of the contents of the nodes, in order
	// e.g. [Apple, Cranberry, Durian, Mango]
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		BinaryNode<E> currentNode = new BinaryNode<E>(this.min());
		E max = max();
		while(!currentNode.getValue().equals(max)) {

		}
	}

	public 

	

}
