/*
 * See ArrayList documentation here:
 * http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
 */

/*
 * Your indexed functions should throw IndexOutOfBoundsException if index is invalid!
 */

public class MyArrayList<E> {

	/* Internal Object counter */
	protected int objectCount;

	/* Internal Object array */
	protected E[] internalArray;

	/* Constructor: Create it with whatever capacity you want? */
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		this.internalArray = (E[]) new Object[100];
	}

	/* Constructor with initial capacity */
	@SuppressWarnings("unchecked")
	public MyArrayList(int initialCapacity) {
		this.internalArray = (E[]) new Object[initialCapacity];
	}

	/* Return the number of active slots in the array list */
	public int size() {
		return objectCount;
	}

	public boolean isEmpty() {
		return objectCount == 0;
	}

	/* Get the index-th object in the list. */
	public E get(int index) {
		return internalArray[index];
	}

	/* Replace the object at index with obj. returns object that was replaced. */
	public E set(int index, E obj) {
		E toReplace = internalArray[index];
		internalArray[index] = obj;
		return toReplace;
	}

	/* Add an object to the end of the list; returns true */
	@SuppressWarnings("unchecked")
	public boolean add(E obj) {
		add(objectCount, obj);
		return true;
	}

	/* Insert an object at index */
	@SuppressWarnings("unchecked")
	public void add(int index, E obj) {
		if (objectCount == internalArray.length - 1) {
			E[] newinternalArray = (E[]) new Object[internalArray.length * 2];
			for (int i = 0; i < newinternalArray.length; i++) {
				newinternalArray[i] = internalArray[i];
			}
			internalArray = newinternalArray;
		}
		for (int i = objectCount; i > index; i--) {
			internalArray[i] = internalArray[i - 1];
		}
		internalArray[index] = obj;
		objectCount++;
	}

	public E remove(int index) {
		E wasRemoved = internalArray[index];
		for (int i = index; i < objectCount; i++) {
			internalArray[i] = internalArray[i + 1];
		}
		objectCount -= 1;
		return wasRemoved;
	}

	public boolean remove(E obj) {
		int orginialObjectCount = objectCount;
		for (int index = 0; index < objectCount; index++) {
			if (internalArray[index].equals(obj)) {
				remove(index);
				// index--;
			}
		}
		return orginialObjectCount != objectCount;
	}

	public boolean contains(E obj) {
		for (int i = 0; i < internalArray.length; i++) {
			if (internalArray[i].equals(obj)) {
				return true;
			}
		}
		return false;
	}

	// o(n)
	public String toString() {
		String finishedString = "[";
		for (int index = 0; index < objectCount; index++) {
			finishedString += internalArray[index].toString();
			finishedString += ", ";
		}
		return finishedString + "]";
	}



}
