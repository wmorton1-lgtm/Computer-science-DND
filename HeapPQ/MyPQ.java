
public class MyPQ<E extends Comparable<E>> {

	private E[] heap;
	private int objectCount;

	public MyPQ() {
		this.heap = (E[]) new Comparable[3];
		this.objectCount = 0;
	}

	// Returns the number of elements in the priority queue
	public int size() {
		return objectCount;
	}

	// DO NOT CHANGE MY JANKY TOSTRING!!!!!
	public String toString() {
		StringBuffer stringbuf = new StringBuffer("[");
		for (int i = 0; i < objectCount; i++) {
			stringbuf.append(heap[i]);
			if (i < objectCount - 1)
				stringbuf.append(", ");
		}
		stringbuf.append("]\nor alternatively,\n");

		for (int rowLength = 1, j = 0; j < objectCount; rowLength *= 2) {
			for (int i = 0; i < rowLength && j < objectCount; i++, j++) {
				stringbuf.append(heap[j] + " ");
			}
			stringbuf.append("\n");
			if (j < objectCount) {
				for (int i = 0; i < Math.min(objectCount - j, rowLength * 2); i++) {
					if (i % 2 == 0)
						stringbuf.append("/");
					else
						stringbuf.append("\\ ");
				}
				stringbuf.append("\n");
			}
		}
		return stringbuf.toString();
	}

	// Doubles the size of the heap array
	private void increaseCapacity() {
		E[] newHeap = (E[]) new Comparable[heap.length * 2];
		for (int i = 0; i < heap.length; i++) {
			newHeap[i] = heap[i];
		}
		heap = newHeap;
	}

	// Returns the index of the "parent" of index i
	// i/2 -1
	private int parent(int i) {
		if (i <= 0) {
			return -1;
		}
		return (i - 1) / 2;
	}

	// Returns the index of the *smaller child* of index i
	private int smallerChild(int i) {
		if (i < 0) {
			return -1;
		}
		int rightIndex = (i * 2) + 2;
		int leftIndex = (i * 2) + 1;



		if (leftIndex >= objectCount) {
			return -1;
		}

		if (rightIndex >= objectCount) {
			return leftIndex;
		}

		E left = heap[leftIndex];
		E right = heap[rightIndex];
		if (right.compareTo(left) < 0) {
			return (i * 2) + 2;
		} else {
			return (i * 2) + 1;
		}
	}

	// Swaps the contents of indices i and j
	private void swap(int i, int j) {
		if (i < 0 || j < 0 || i >= objectCount || j >= objectCount) {
			// throw new IndexOutOfBoundsException("swap(): THE INDEX WAS NEGATIVE or out of
			// bounds");
			return;
		}
		E jj = heap[j];
		heap[j] = heap[i];
		heap[i] = jj;
	}

	// Bubbles the element at index i upwards until the heap properties hold again.
	private void bubbleUp(int i) {
		if (i < 0) {
			// throw new IndexOutOfBoundsException("BUBBLEUP(): THE INDEX WAS NEGATIVE");
			return;
		}
		boolean parentIsGreater = true;
		while (parentIsGreater) {
			int parentIndex = parent(i);
			if (parentIndex == -1) {
				return;
			}
			if (heap[i].compareTo(heap[parentIndex]) >= 0) {
				parentIsGreater = false;
				return;
			}
			swap(i, parentIndex);
			i = parentIndex;
		}
	}

	// Bubbles the element at index i downwards until the heap properties hold again.
	private void bubbleDown(int i) {
		if (i < 0) {
			// throw new IndexOutOfBoundsException("BUBBLEDOWN(): THE INDEX WAS NEGATIVE");
			return;
		}
		boolean childIsSmaller = true;
		while (childIsSmaller) {
			int smallerChildIndex = smallerChild(i);
			if (smallerChildIndex == -1) {
				return;
			}
			if (heap[i].compareTo(heap[smallerChildIndex]) <= 0) {
				return;
			}
			swap(i, smallerChildIndex);
			i = smallerChildIndex;
		}
	}

	
	public void add(E obj) {
		if (obj == null) {
			return;
		}
		if (objectCount == heap.length) {
			increaseCapacity();
		}
		heap[objectCount] = obj;
		objectCount++;
		bubbleUp(objectCount - 1);
	}

	
	public E removeMin() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("removeMin() heap empty");
		}

		E toReturn = heap[0];
		heap[0] = heap[objectCount - 1];
		heap[objectCount - 1] = null;
		objectCount--;
		if (objectCount > 0) {
			bubbleDown(0);
		}
		return toReturn;
	}

	
	public E peek() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("peek() heap empty");
		}
		return heap[0];
	}

	
	public boolean isEmpty() {
		return objectCount == 0;
	}
}
