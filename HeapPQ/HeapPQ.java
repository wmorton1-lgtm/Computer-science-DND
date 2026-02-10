
public class HeapPQ<E extends Comparable<E>> implements MyPriorityQueue<E> {

	private E[] heap;
	private int objectCount;

	public HeapPQ() {
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
		if (i == 0) {
			return -1;
		}
		if (i % 2 == 0) {
			return (i / 2) - 1;
		} else {
			return i / 2;
		}
	}

	// Returns the index of the *smaller child* of index i
	private int smallerChild(int i) {
		return Math.min(i * 2 + 1, i * 2 + 2) ;
	}

	// Swaps the contents of indices i and j
	private void swap(int i, int j) {
		E jj = heap[j];
		heap[j] = heap[i];
		heap[j] = jj;
	}

	// Bubbles the element at index i upwards until the heap properties hold again.
	private void bubbleUp(int i) {
		int parentIndex = parent(i);
		E currentValuE = heap[i];
		E parentValuE = heap[parentIndex];
		// if (parentIndex == -1) {
		// 	heap[parentIndex] = Math.max(currentValuE, parentValuE);
		// }
		if (parentValuE.compareTo(currentValuE) > 0) {
			heap[parentIndex] = currentValuE;
			heap[i] = parentValuE;
		}
	}

	// Bubbles the element at index i downwards until the heap properties hold again.
	private void bubbleDown(int i) {

	}

	@Override
	public void add(E obj) {
		if (objectCount - 1 == heap.length) {
			increaseCapacity();
		}
		heap[objectCount] = obj;
	}

	@Override
	public E removeMin() {
		for (int i = heap.length; i < 0; i--) {

		}
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'peek'");
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
	}

}
