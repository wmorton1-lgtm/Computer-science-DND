import java.util.ArrayList;

public class Recursion {

	// Prints the value of every node in the singly linked list with the given head,
	// but in reverse
	public static void printListInReverse(ListNode head) {
		if (head.getNext() == null) {
			System.out.print(head.getValue().toString() + " ");
		} else {
			printListInReverse(head.getNext());
			System.out.print(head.getValue().toString() + " ");
		}
	}

	// For the given 2D array of Strings, replaces the String at index[r][c]
	// with "infected" unless the String is "vaccinated";
	// also, any Strings they are orthogonally adjacent to
	// that are not "vaccinated" will also be infected, and any adjacent to
	// them as well etc.
	// Infecting someone who is already infected has no effect
	// Trying to infect outside the confines of the grid also has no effect
	// Precondition: grid has no null entries
	public static void infect(String[][] grid, int r, int c) {
		if (r >= grid.length || c >= grid[0].length || r < 0 || c < 0) {
			throw new IndexOutOfBoundsException("infect(grid, r ,c): index was out of bounds");
		}
		if (grid[r][c].equals("vaccinated") || grid[r][c].equals("infected")) {
			return;
		}
		grid[r][c] = "infected";
		boolean touchingTop = r == 0;
		boolean touchingBottom = r == grid.length - 1;
		boolean touchingLeft = c == 0;
		boolean touchingRight = c == grid[0].length - 1;
		if (!touchingRight) {
			infect(grid, r, c + 1);
		}
		if (!touchingLeft) {
			infect(grid, r, c - 1);
		}
		if (!touchingTop) {
			infect(grid, r - 1, c);
		}
		if (!touchingBottom) {
			infect(grid, r + 1, c);
		}
	}

	public static void print2DArray(String[][] grid) {

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				System.out.print(grid[i][j] + ' ');
			}
			System.out.println("\n");
		}
	}

	// How many subsets are there of the numbers 1...n
	// that don't contain any consecutive integers?
	// e.g. for n = 4, the subsets are {}, {1}, {2}, {3}, {4},
	// {1, 3}, {1, 4}, {2, 4}
	// The other subsets of 1,2,3,4 that DO contain consecutive integers are
	// {1,2}, {2,3}, {3,4}, {1,2,3}, {1,2,4}, {1,3,4}, {1,2,3,4}
	// Precondition: n > 0
	public static long countNonConsecutiveSubsets(int n) {
		if (n == 1) {
			return (long) 2;
		}
		if (n == 0) {
			return (long) 1;
		}
		return countNonConsecutiveSubsets(n - 1) + countNonConsecutiveSubsets(n - 2);
	}

	// A kid at the bottom of the stairs can jump up 1, 2, or 3 stairs at a time.
	// How many different ways can they jump up n stairs?
	// Jumping 1-1-2 is considered different than jumping 1-2-1
	// Precondition: n > 0
	public static long countWaysToJumpUpStairs(int n) {
		if (n == 0) {
			return (long) 0;
		}
		if (n == 1) {
			return (long) 1;
		}
		if (n == 2) {
			return (long) 2;
		}
		if (n == 3) {
			return (long) 4;
		}
		return countWaysToJumpUpStairs(n - 1) + countWaysToJumpUpStairs(n - 2)
				+ countWaysToJumpUpStairs(n - 3);

	}

	// Everything above this line does NOT require a recursive helper method
	// ------------------------------------
	// Everything below this line requires a recursive helper method
	// Any recursive helper method you write MUST have a comment describing:
	// 1) what the helper method does/returns
	// 2) your description must include role of each parameter in the helper method

	// Prints all the subsets of str on separate lines
	// You may assume that str has no repeated characters
	// For example, subsets("abc") would print out "", "a", "b", "c", "ab", "ac",
	// "bc", "abc"
	// Order is your choice

	// public static void addLetterToBeginningAndMaybePrintItToo(String a, String str) {
	// System.out.println("\"" + a + str + "\",");
	// }

	public static void printSubsets(String str) {
		printSubsetArray(generateSubsetsArray(str), 0);
	}

	public static void printSubsetArray(String[] array, int i) {
		if (i == array.length - 1) {
			System.out.println("\"" + array[i] + "\"");
			return;
		}
		System.out.println("\"" + array[i] + "\",");
		printSubsetArray(array, i + 1);
	}

	public static String[] generateSubsetsArray(String str) {
		if (str.length() == 2) {
			String[] baseArray = new String[4];
			baseArray[0] = "";
			baseArray[1] = "" + str.substring(0, 1) + "";
			baseArray[2] = "" + str.substring(1) + "";
			baseArray[3] = "" + str + "";
			return baseArray;
		}
		if (str.length() == 1) {
			String[] baseArray = new String[2];
			baseArray[0] = "";
			baseArray[1] = "" + str.substring(0, 1) + "";
			return baseArray;
		}
		if (str.length() == 0) {
			String[] baseArray = new String[1];
			baseArray[0] = "";
			return baseArray;
		}


		return addLetterToEveryIndex(generateSubsetsArray(str.substring(0, str.length() - 1)),
				str.substring(str.length() - 1));
	}

	public static String[] addLetterToEveryIndex(String[] array, String a) {
		String[] subsets = new String[array.length * 2];
		for (int j = 0; j < array.length; j++) {
			subsets[j + array.length] = array[j] + a;
			subsets[j] = array[j];
		}
		return subsets;
	}

	// List contains a single String to start.
	// Prints all the permutations of str on separate lines
	// You may assume that str has no repeated characters
	// For example, permute("abc") could print out "abc", "acb", "bac", "bca",
	// "cab", "cba"
	// Order is your choice
	public static void printPermutations(String str) {
		String[] permutations = generatePermutationsArray(str);
		for (int i = 0; i < permutations.length; i++) {
			System.out.println(permutations[i]);
		}
	}

	public static String[] generatePermutationsArray(String str) {
		if (str.length() == 2) {
			String[] baseArray = new String[2];
			baseArray[0] = str.substring(0, 1) + str.substring(1);
			baseArray[1] = str;
			return baseArray;
		}
		if (str.length() == 1) {
			String[] baseArray = new String[1];
			baseArray[0] = str;
			return baseArray;
		}
		if (str.length() == 0) {
			String[] baseArray = new String[1];
			baseArray[0] = "";
			return baseArray;
		}

		String firstLetter = str.substring(0, 1);
		String restOfStr = str.substring(1);
		String[] permutationsOfTheRest = generatePermutationsArray(restOfStr);
		String[] combigned = new String[permutationsOfTheRest.length * str.length()];

		int i = 0;
		for (String permutatedPart : permutationsOfTheRest) {
			for (int j = 0; j <= permutatedPart.length(); j++) {

			}
		}
		return addFirstLetterToRest(str.substring(0, 1),
				generatePermutationsArray(str.substring(1, str.length())), 0);

	}


	// Performs a mergeSort on the given array of ints
	// Precondition: you may assume there are NO duplicates!!!
	public static void mergeSort(int[] ints) {
		ArrayList<Integer> intsList = new ArrayList<>();
		splitArray(intsList);
	}

	public static ArrayList<int[]> splitArray(int[] ints) {
		ArrayList<int[]> intList = new ArrayList<>();
		if (ints.length== 1) {
			return;
		}
		intList.addAll(splitArray(ints.remo));
		
	}

	

	public static int[] getHalf(int whatHalf, int[] ints) {
		// if (whatHalf == 1) {
		// int[] firstHalf = new int[ints.length / 2];
		// for (int i = 0; i < ints.length / 2; i++) {
		// firstHalf[i] = ints[i];
		// }
		// return firstHalf;
		// }
		// int[] secondHalf = new int[ints.length / 2 + ints.length % 2];
		// for (int i = ints.length / 2; i < secondHalf.length; i++) {
		// secondHalf[i] = ints[i];
		// }
		// return secondHalf;
	}

	// Performs a quickSort on the given array of ints
	// Use the middle element (index n/2) as the pivot
	// Precondition: you may assume there are NO duplicates!!!
	public static void quickSort(int[] ints) {

	}

	// Prints a sequence of moves (one on each line)
	// to complete a Towers of Hanoi problem:
	// disks start on tower 0 and must end on tower 2.
	// The towers are number 0, 1, 2, and each move should be of
	// the form "1 -> 2", meaning "take the top disk of tower 1 and
	// put it on tower 2" etc.
	public static void printMove(int a, int b) {
		System.out.println(a + "->" + b);
	}

	public static void moveTower(int diskCount, int current, int destination) {
		int free;
		if (current == 0 && destination == 2) {
			free = 1;
		} else if (current == 2 && destination == 1) {
			free = 0;
		} else {
			free = 2;
		}
		if (diskCount == 1) {
			Recursion.printMove(current, destination);
			return;
		}
		if (diskCount == 2) {
			Recursion.printMove(current, free);
			Recursion.printMove(current, destination);
			Recursion.printMove(free, destination);
		}


		moveTower(diskCount - 1, current, destination);
		Recursion.printMove(current, destination);
	}


	public static void solveHanoi(int startingDisks) {
		moveTower(startingDisks, 0, 2);
	}

	// You are partaking in a scavenger hunt!
	// You've gotten a secret map to find many of the more difficult
	// items, but they are only available at VERY specific times at
	// specific places. You have an array, times[], that lists at which
	// MINUTE an item is available. Times is sorted in ascending order.
	// Items in the ScavHunt are worth varying numbers of points.
	// You also have an array, points[], same length as times[],
	// that lists how many points each of the corresponding items is worth.
	// Problem is: to get from one location to the other takes 5 minutes,
	// so if there is an item, for example, available at time 23 and another
	// at time 27, it's just not possible for you to make it to both: you'll
	// have to choose!
	// (but you COULD make it from a place at time 23 to another at time 28)
	// Write a method that returns the maximum POINTS you can get.
	// For example, if times = [3, 7, 9]
	// and points = [10, 15, 10]
	// Then the best possible result is getting the item at time 3 and the one at
	// time 9
	// for a total of 20 points, so it would return 20.
	public static int scavHunt(int[] times, int[] points) {
		return findMaxReward(times, points, 0);
	}

	public static int findMaxReward(int[] times, int[] points, int i) {
		if (i >= times.length) {
			return 0;
		}
		int doesTake = points[i];
		if (nextOneThatsFiveAway(i, times) != -1) {
			doesTake += findMaxReward(times, points, nextOneThatsFiveAway(i, times));
		}
		int dontTake = findMaxReward(times, points, i + 1);
		return Math.max(doesTake, dontTake);
	}

	public static int nextOneThatsFiveAway(int index, int[] times) {
		for (int i = index + 1; i < times.length; i++) {
			if (times[i] >= times[index] + 5) {
				return i;
			}
		}
		return -1;
	}

	// public static boolean isBaseCase(int[] times, int i) {
	// if (i > times.length - 6) {
	// if (times[i + 1] < times[i] + 5 || times[i + 2] < times[i] + 5
	// || times[i + 3] < times[i] + 5 || times[i + 4] < times[i] + 5
	// || times[i + 5] < times[i] + 5) {
	// return true;
	// }
	// }
	// }
}
