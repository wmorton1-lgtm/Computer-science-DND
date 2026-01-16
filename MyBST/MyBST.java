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
		// if (root == null) {
		// return false;
		// }
		return findNode(root, value) != null;
	}

	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.

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



	// Smallest node in the right subtree
	public BinaryNode<E> findNode(BinaryNode<E> current, E value) {
		if (current == null) {
			return null;
		}
		if (current.getValue().equals(value)) {
			return current;
		} else if (current.getValue().compareTo(value) > 0) {
			return findNode(current.getLeft(), value);
		} else {
			// (current.getValue().compareTo(value) < 0)
			return findNode(current.getRight(), value);
		}
	}

	// getters
	// Removes value from this BST. Returns true if value has been
	// found and removed; otherwise returns false.
	// If removing a node with two children: replace it with the
	public boolean remove(E value) {
		if (!contains(value)) {
			return false;
		}
		BinaryNode<E> toRemove = findNode(root, value);
		removeHelper(toRemove);
		if (root != null) {
			root.setParent(null);
		}
		return true;
	}

	public void removeHelper(BinaryNode<E> toBeRemoved) {
		if (toBeRemoved.isLeaf()) {
			removeLeaf(toBeRemoved);
		} else if (toBeRemoved.getRight() == null) { // only left nodes
			removeNodeIfOnlyHasLeftChildren(toBeRemoved);
		} else if (toBeRemoved.getLeft() == null) { // only riht nodes
			removeNodeIfOnlyHasRightChildren(toBeRemoved);
		} else { // 2 nodes
			BinaryNode<E> moveValue = minHelper(toBeRemoved.getRight());
			toBeRemoved.setValue(moveValue.getValue());

			if (moveValue.isLeaf()) {
				removeLeaf(moveValue);
			} else { // will have only right children
				moveValue.getRight().setParent(moveValue.getParent());
				moveValue.getParent().setLeft(moveValue.getRight());
			}
			moveValue.setParent(null);
		}
	}

	// public void moveNode(BinaryNode<E> toBeReplaced, BinaryNode<E> goingToReplace) {

	// }

	public boolean isRight(BinaryNode<E> current) {
		return current.getParent() != null && current == current.getParent().getRight();

	}

	// public boolean checkForLeafedRootAndRemove(BinaryNode<E> toRemove) {
	// if (toRemove.getParent() == null) {
	// root = null;
	// return true;
	// }
	// return false;
	// }

	public void removeLeaf(BinaryNode<E> toRemove) {
		if (toRemove.getParent() == null || toRemove.getValue().equals(root.getValue())) {
			root = null;
			return;
		}
		if (isRight(toRemove)) {
			toRemove.getParent().setRight(null);
		} else {
			toRemove.getParent().setLeft(null);
		}
		toRemove.setParent(null);
	}

	public void removeNodeIfOnlyHasLeftChildren(BinaryNode<E> toRemove) {// Todo:fix left thing
		BinaryNode<E> replacementNode = null;
		if (toRemove.getLeft().isLeaf()) {
			replacementNode = toRemove.getLeft();
		} else {
			replacementNode = maxHelper(toRemove.getLeft());
		}
		// toRemove.setLeft(replacementNode.getLeft());
		// toRemove.setRight(replacementNode.getRight());
		// if (replacementNode.getLeft() != null) {
		// replacementNode.getLeft().setParent(toRemove);
		// }
		// if (replacementNode.getRight() != null) {
		// replacementNode.getRight().setParent(toRemove);
		// }
		if (replacementNode.getLeft() != null) {
			replacementNode.getLeft().setParent(replacementNode.getParent());
			if (!isRight(replacementNode)) {
				replacementNode.getParent().setRight(replacementNode.getRight());
			} else {
				replacementNode.getParent().setLeft(replacementNode.getRight());
			}
			replacementNode.setLeft(null);
		} else {
			replacementNode.getParent().setRight(null);
		}
		replacementNode.setParent(null);
		toRemove.setValue(replacementNode.getValue());
	}

	public void removeNodeIfOnlyHasRightChildren(BinaryNode<E> toRemove) {
		BinaryNode<E> replacementNode;
		if (toRemove.getRight().isLeaf()) {
			replacementNode = toRemove.getRight();
		} else {
			replacementNode = minHelper(toRemove.getRight());
		}
		if (replacementNode.getRight() != null) {
			replacementNode.getRight().setParent(replacementNode.getParent());
			if (isRight(replacementNode)) {
				replacementNode.getParent().setRight(replacementNode.getRight());
			} else {
				replacementNode.getParent().setLeft(replacementNode.getRight());
			}
			replacementNode.setRight(null);
		} else {
			replacementNode.getParent().setLeft(null);
		}
		replacementNode.setParent(null);
		toRemove.setValue(replacementNode.getValue());
	}

	// getters


	// Returns the minimum in the tree
	public E min() {
		return minHelper(root).getValue();
	}

	public BinaryNode<E> minHelper(BinaryNode<E> current) {
		if (current.getLeft() == null) {
			return current;
		}
		return minHelper(current.getLeft());
	}

	// Returns the maximum in the tree.
	public E max() {
		return maxHelper(root).getValue();
	}

	public BinaryNode<E> maxHelper(BinaryNode<E> current) {
		if (current.getRight() == null) {
			return current;
		}
		return maxHelper(current.getRight());
	}

	// Returns a bracket-surrounded, comma separated list of the contents of the nodes, in order
	// e.g. [Apple, Cranberry, Durian, Mango]
	public String toString() {
		String toReturn = "[" + rightUpperLeft(root) + "";
		if (toReturn.length() == 1) {
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
