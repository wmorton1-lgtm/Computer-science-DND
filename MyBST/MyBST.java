// Implements a BST with BinaryNode nodes

public class MyBST<E extends Comparable<E>> {

	private BinaryNode<E> root; // holds the root of this BST

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
		return toString().contains(value.toString());
	}

	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.
	public void reBalance(String
	public boolean add(E value) {
		if (root == null) {
			BinaryNode<E> toAdd = new BinaryNode<E>(value);
			root = toAdd;
			return true;
		}
		if (this.contains(value)) {
			return false;
		}
		return addHelper(root, value);
	}

	public boolean addHelper(BinaryNode<E> currNode, E value) {
		if (currNode == null) {
			return false;

		}

		if (currNode.getLeft() == null && currNode.getValue().compareTo(value) > 0) {
			BinaryNode<E> toAdd = new BinaryNode<E>(value);
			currNode.setLeft(toAdd);
			toAdd.setParent(currNode);
			toAdd.setHeight(toAdd.getParent().getHeight() + 1);
			return true;
		}
		if (currNode.getRight() == null && currNode.getValue().compareTo(value) < 0) {
			BinaryNode<E> toAdd = new BinaryNode<E>(value);
			currNode.setRight(toAdd);
			toAdd.setParent(currNode);
			toAdd.setHeight(toAdd.getParent().getHeight() + 1);
			return true;
		}

		if (value.compareTo(currNode.getValue()) > 0) {
			return addHelper(currNode.getRight(), value);
		} else {
			return addHelper(currNode.getLeft(), value);
		}
	}

	

	// Removes value from this BST. Returns true if value has been
	// found and removed; otherwise returns false.
	// If removing a node with two children: replace it with the
	// largest node in the right subtree
	public boolean remove(E value) {
		return false;
	}

	// Returns the minimum in the tree
	public E min() {
		return minHelper(root);
	}

	public E minHelper(BinaryNode<E> current) {
		if (current.getLeft() == null) {
			return (E) current.getValue();
		}
		return minHelper(current.getLeft());
	}

	// Returns the maximum in the tree.
	public E max() {
		return maxHelper(root);
	}

	public E maxHelper(BinaryNode<E> current) {
		if (current.getRight() == null) {
			return (E) current.getValue();
		}
		return maxHelper(current.getRight());
	}

	// Returns a bracket-surrounded, comma separated list of the contents of the nodes, in order
	// e.g. [Apple, Cranberry, Durian, Mango]
	public String toString() {
		String toReturn = "[" + rightUpperLeft(root) + "";
		if (toReturn.length() == 0) {
			return "[]";
		}
		return toReturn.substring(0, toReturn.length() - 2) + "]";
	}

	public StringBuilder rightUpperLeft(BinaryNode<E> currentNode) {
		StringBuilder builder = new StringBuilder("");
		if (currentNode == null) {
			return builder;
		}

		BinaryNode<E> left = currentNode.getLeft();
		BinaryNode<E> right = currentNode.getRight();
		
		builder.append(rightUpperLeft(left));
		builder.append(currentNode.getValue().toString()).append(", ");
		builder.append(rightUpperLeft(right));

		return builder;
	}



}
