import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestDoublyLinkedList {

	ArrayList<Nucleotide> list = new ArrayList<Nucleotide>();
	DoublyLinkedList mylist = new DoublyLinkedList();

	void fillListsWithData() {
		mylist.add(Nucleotide.A);
		mylist.add(Nucleotide.G);
		mylist.add(Nucleotide.C);
		list.add(Nucleotide.A);
		list.add(Nucleotide.G);
		list.add(Nucleotide.C);
	}

	@Test
	@DisplayName("[5] Test if your basic constructor works correctly")
	public void testConstructor() {
		mylist = new DoublyLinkedList();
		assertEquals(0, mylist.size());

	}
	
	@Test
	@DisplayName("[5] Test if your constructor from array works correctly")
	public void testConstructor2() {
		Nucleotide[] arr = {Nucleotide.A, Nucleotide.G, Nucleotide.C};
		mylist = new DoublyLinkedList(arr);

		
		int i = 0;
		long startTime = System.currentTimeMillis();
		for (ListNode2 node = mylist.getHead(); node != mylist.getSentinel(); node = node.getNext()) {
			if (System.currentTimeMillis() - startTime > 5000)
				fail("Your test times out.");
			assertEquals(arr[i], node.getValue());
			i++;
		}
		assertEquals(i, arr.length);
		i--;
		startTime = System.currentTimeMillis();
		for (ListNode2 node = mylist.getTail(); node != mylist.getSentinel(); node = node.getPrevious()) {
			if (System.currentTimeMillis() - startTime > 5000)
				fail("Your test times out.");
			assertEquals(arr[i], node.getValue());
			i--;
		}
		assertEquals(i, -1);
	}
	
	@Test
	@DisplayName("[5] Test if your add method works correctly")
	public void testAdd() {
		fillListsWithData();

		int i = 0;
		long startTime = System.currentTimeMillis();
		for (ListNode2 node = mylist.getHead(); node != mylist.getSentinel(); node = node.getNext()) {
			if (System.currentTimeMillis() - startTime > 1000)
				fail("Your test times out.");
			assertEquals(list.get(i), node.getValue());
			i++;
		}
		assertEquals(i, list.size());
		i--;
		startTime = System.currentTimeMillis();
		for (ListNode2 node = mylist.getTail(); node != mylist.getSentinel(); node = node.getPrevious()) {
			if (System.currentTimeMillis() - startTime > 1000)
				fail("Your test times out.");
			assertEquals(list.get(i), node.getValue());
			i--;
		}
		assertEquals(i, -1);
	}
	
	@Test
	@DisplayName("[5] Test if your get method works correctly")
	public void testGet() {
		fillListsWithData();

		for (int i = 0; i < list.size(); i++) {
			assertNotNull(mylist.get(i));		
			assertEquals(list.get(i), mylist.get(i));
		}
	}


	@Test
	@DisplayName("[5] Test if your size method works correctly")
	public void testSize() {
		fillListsWithData();

		assertEquals(list.size(), mylist.size());
	}

	@Test
	@DisplayName("[5] Test if your toString method works correctly")
	public void testToString() {
		fillListsWithData();

		assertEquals(list.toString(), mylist.toString());
	}


	@Test
	@DisplayName("[5] Test if your isEmpty() works correctly")
	public void testIsEmpty() {
		assertTrue(mylist.isEmpty());

		mylist.add(Nucleotide.C);

		assertFalse(mylist.isEmpty());
	}

	@Test
	@DisplayName("[5] Test if your set() works correctly")
	public void testSet() {
		fillListsWithData();
		assertEquals(list.set(1, Nucleotide.A), mylist.set(1, Nucleotide.A));
		int i = 0;
		long startTime = System.currentTimeMillis();
		for (ListNode2 node = mylist.getHead(); node != mylist.getSentinel(); node = node.getNext()) {
			if (System.currentTimeMillis() - startTime > 1000)
				fail("Your test times out.");
			assertEquals(list.get(i), node.getValue());
			i++;
		}
		assertEquals(i, list.size());
		i--;
		startTime = System.currentTimeMillis();
		for (ListNode2 node = mylist.getTail(); node != mylist.getSentinel(); node = node.getPrevious()) {
			if (System.currentTimeMillis() - startTime > 1000)
				fail("Your test times out.");
			assertEquals(list.get(i), node.getValue());
			i--;
		}
		assertEquals(i, -1);

	}


	@Test
	@DisplayName("[5] Test if your get() throws the correct exception")
	public void testException() {
		fillListsWithData();

		assertThrows(IndexOutOfBoundsException.class, () -> mylist.get(4));
	}



	@Test
	@DisplayName("[5] Test if your add at index method works correctly")
	public void testAddAtIndex() {
		fillListsWithData();

		mylist.add(1, Nucleotide.T);
		list.add(1, Nucleotide.T);


		int i = 0;
		long startTime = System.currentTimeMillis();
		for (ListNode2 node = mylist.getHead(); node != mylist.getSentinel(); node = node.getNext()) {
			if (System.currentTimeMillis() - startTime > 1000)
				fail("Your test times out.");
			assertEquals(list.get(i), node.getValue());
			i++;
		}
		assertEquals(i, list.size());
		i--;
		startTime = System.currentTimeMillis();
		for (ListNode2 node = mylist.getTail(); node != mylist.getSentinel(); node = node.getPrevious()) {
			if (System.currentTimeMillis() - startTime > 1000)
				fail("Your test times out.");
			assertEquals(list.get(i), node.getValue());
			i--;
		}
		assertEquals(i, -1);

	}

	@Test
	@DisplayName("[5] Test if your remove at index method works correctly.")
	public void testRemoveIndex() {
		list = new ArrayList();
		mylist = new DoublyLinkedList();

		mylist.add(Nucleotide.G);
		mylist.add(Nucleotide.C);
		mylist.add(Nucleotide.T);
		mylist.add(Nucleotide.C);
		mylist.add(Nucleotide.A);
		mylist.add(Nucleotide.T);
		mylist.remove(3);

		list.add(Nucleotide.G);
		list.add(Nucleotide.C);
		list.add(Nucleotide.T);
		list.add(Nucleotide.C);
		list.add(Nucleotide.A);
		list.add(Nucleotide.T);
		list.remove(3);

		assertEquals(list.size(), mylist.size());

		int i = 0;
		long startTime = System.currentTimeMillis();
		for (ListNode2 node = mylist.getHead(); node != mylist.getSentinel(); node = node.getNext()) {
			if (System.currentTimeMillis() - startTime > 1000)
				fail("Your test times out.");
			assertEquals(list.get(i), node.getValue());
			i++;
		}
		assertEquals(i, list.size());
		i--;
		startTime = System.currentTimeMillis();
		for (ListNode2 node = mylist.getTail(); node != mylist.getSentinel(); node = node.getPrevious()) {
			if (System.currentTimeMillis() - startTime > 1000)
				fail("Your test times out.");
			assertEquals(list.get(i), node.getValue());
			i--;
		}
		assertEquals(i, -1);

	}

	@Test
	@DisplayName("[5] Test if your remove object method works correctly.")
	public void testRemoveStrings() {
		list = new ArrayList();
		mylist = new DoublyLinkedList();

		mylist.add(Nucleotide.A);
		mylist.add(Nucleotide.G);
		mylist.add(Nucleotide.C);
		mylist.add(Nucleotide.T);
		mylist.add(Nucleotide.C);
		mylist.add(Nucleotide.A);
		mylist.add(Nucleotide.T);
		list.add(Nucleotide.A);
		list.add(Nucleotide.G);
		list.add(Nucleotide.C);
		list.add(Nucleotide.T);
		list.add(Nucleotide.C);
		list.add(Nucleotide.A);
		list.add(Nucleotide.T);

		assertEquals(mylist.remove(Nucleotide.A), list.remove(Nucleotide.A));

		assertEquals(mylist.remove(Nucleotide.A), list.remove(Nucleotide.A));

		int i = 0;
		long startTime = System.currentTimeMillis();
		for (ListNode2 node = mylist.getHead(); node != mylist.getSentinel(); node = node.getNext()) {
			if (System.currentTimeMillis() - startTime > 1000)
				fail("Your test times out.");
			assertEquals(list.get(i), node.getValue());
			i++;
		}
		assertEquals(i, list.size());
		i--;
		startTime = System.currentTimeMillis();
		for (ListNode2 node = mylist.getTail(); node != mylist.getSentinel(); node = node.getPrevious()) {
			if (System.currentTimeMillis() - startTime > 1000)
				fail("Your test times out.");
			assertEquals(list.get(i), node.getValue());
			i--;
		}
		assertEquals(i, -1);

	}




}