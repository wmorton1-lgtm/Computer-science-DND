public class SortedArrayList<E extends Comparable<E>> extends MyArrayList<E> {


	@Override
	public boolean contains(E obj) {
		return binarySearchRecursive(internalArray, obj) != -1;
	}

	// May not contain more than one of the same object
	@Override
	public boolean add(E obj) {
		if (contains(obj)) {
			return false;
		}
		for (int index = 0; index < internalArray.length; index++) {
			if (obj.compareTo(internalArray[index + 1]) < 0 && obj.compareTo(internalArray[index]) > 0) {
				add(index, obj);
				return true;
			}
			
		}
		return false;
		// if (contains(obj)) {
		// 	return false;
		// }
		// add(binarySearchRecursive(internalArray, obj), obj);
		// return true;
	}

	// public int findIndexToAdd(E obj) {
	// if (obj.compareTo(this.get(this.size()/2)) < 0) {
	// objectCount = objectCount/2;

	// }
	// }

	@Override
	public boolean remove(E obj) {
		super.remove(obj);
		return true;
	}

	public E min() {
		return internalArray[0];
	}

	public E max() {
		return internalArray[internalArray.length - 1];
	}

	public int binarySearchRecursive(E[] arr, E key) {
        if (arr == null) {
            return -1;
        }
        return binarySearchRecursive(arr, key, 0, arr.length - 1);
    }

	
    private int binarySearchRecursive(E[] arr, E key, int low, int high) {
        if (low > high) {
            return -1;
        }
		int midIndex = (low + high) / 2;
        E mid = arr[midIndex];
        if (mid.compareTo(key) == 0) {
            return midIndex;
        } else if (mid.compareTo(key) < 0) {
            return binarySearchRecursive(arr, key, midIndex + 1, high);
        } else {
            return binarySearchRecursive(arr, key, low, midIndex - 1);
        }
    }
}
