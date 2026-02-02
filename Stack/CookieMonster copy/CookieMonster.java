import java.io.*;
import java.util.*;

// You are allowed (and expected!) to use either Java's ArrayDeque or LinkedList class to make
// stacks and queues


public class CookieMonster {

	private int[][] cookieGrid;
	private int numRows;
	private int numCols;

	// Constructs a CookieMonster from a file with format:
	// numRows numCols
	// <<rest of the grid, with spaces in between the numbers>>
	public CookieMonster(String fileName) {
		int row = 0;
		int col = 0;
		try {
			Scanner input = new Scanner(new File(fileName));

			numRows = input.nextInt();
			numCols = input.nextInt();
			cookieGrid = new int[numRows][numCols];

			for (row = 0; row < numRows; row++)
				for (col = 0; col < numCols; col++)
					cookieGrid[row][col] = input.nextInt();

			input.close();
		} catch (Exception e) {
			System.out.print("Error creating maze: " + e.toString());
			System.out.println("Error occurred at row: " + row + ", col: " + col);
		}

	}

	public CookieMonster(int[][] cookieGrid) {
		this.cookieGrid = cookieGrid;
		this.numRows = cookieGrid.length;
		this.numCols = cookieGrid[0].length;
	}

	// You may find it VERY helpful to write this helper method. Or not!
	private boolean validPoint(int row, int col) {
		if (row < 0 || col < 0) {
			return false;
		}
		if (numCols == col) {
			return false;
		}
		if (numRows == row) { // row will be 1 mmore than col
			return false;
		}
		if (cookieGrid[row][col] == -1) {
			return false;
		}
		return true;
	}

	/*
	 * RECURSIVELY calculates the route which grants the most cookies. Returns the maximum number of
	 * cookies attainable.
	 */
	public int recursiveCookies() {
		if (!validPoint(0, 0)) {
			return 0;
		}
		return recursiveCookies(0, 0);
	}

	// Returns the maximum number of cookies edible starting from (and including)
	// cookieGrid[row][col]
	public int recursiveCookies(int row, int col) {
		int totalRight = 0;
		int totalDown = 0;
		if (!validPoint(row + 1, col) && !validPoint(row, col + 1)) {
			return cookieGrid[row][col];
		}
		if (validPoint(row + 1, col)) {
			totalDown += recursiveCookies(row + 1, col) + cookieGrid[row][col];
		}
		if (validPoint(row, col + 1)) {
			totalRight += recursiveCookies(row, col + 1) + cookieGrid[row][col];
		}
		return Math.max(totalRight, totalDown);
	}


	/*
	 * Calculate which route grants the most cookies using a QUEUE. Returns the maximum number of
	 * cookies attainable.
	 */
	/* From any given position, always add the path right before adding the path down */
	public int queueCookies() {
		if (!validPoint(0, 0)) {
			return 0;
		}
		Queue<OrphanScout> scoutQueue = new LinkedList<>();
		OrphanScout originScout = new OrphanScout(0, 0, 0);
		scoutQueue.offer(originScout);
		int highestCookies = 0;

		while (!scoutQueue.isEmpty()) {
			OrphanScout currentScout = scoutQueue.peek();
			int parentRow = currentScout.getEndingRow();
			int parentCol = currentScout.getEndingCol();
			// if (!validPoint(parentRow + 1, parentCol + 1)) {
			// 	continue;
			// }
			if (validPoint(parentRow + 1, parentCol)) {
				OrphanScout childScout = new OrphanScout(parentRow + 1, parentCol,
						currentScout.getCookiesDiscovered() + cookieGrid[parentRow + 1][parentCol]);
				scoutQueue.offer(childScout);
			}
			if (validPoint(parentRow, parentCol + 1)) {
				OrphanScout childScout = new OrphanScout(parentRow, parentCol + 1,
						currentScout.getCookiesDiscovered() + cookieGrid[parentRow][parentCol + 1]);
				scoutQueue.offer(childScout);
			}
			if (currentScout.getCookiesDiscovered() > highestCookies) {
				highestCookies = currentScout.getCookiesDiscovered();
			}
			scoutQueue.poll();
		}
		// if (highestCookies < 0) {
		// 	highestCookies = 0;
		// }
		return highestCookies + cookieGrid[0][0];
	}


	/*
	 * Calculate which route grants the most cookies using a stack. Returns the maximum number of
	 * cookies attainable.
	 */
	/* From any given position, always add the path right before adding the path down */
	public int stackCookies() {
		if (!validPoint(0, 0)) {
			return 0;
		}
		Stack<OrphanScout> scoutStack = new Stack<OrphanScout>();
		OrphanScout originScout = new OrphanScout(0, 0, 0);
		scoutStack.push(originScout);
		int highestCookies = 0;

		while (!scoutStack.isEmpty()) {
			OrphanScout currentScout = scoutStack.pop();
			int parentRow = currentScout.getEndingRow();
			int parentCol = currentScout.getEndingCol();
			// if (!validPoint(parentRow + 1, parentCol + 1)) {
			// 	continue;
			// }
			if (validPoint(parentRow + 1, parentCol)) {
				OrphanScout childScout = new OrphanScout(parentRow + 1, parentCol,
						currentScout.getCookiesDiscovered() + cookieGrid[parentRow + 1][parentCol]);
				scoutStack.push(childScout);
			}
			if (validPoint(parentRow, parentCol + 1)) {
				OrphanScout childScout = new OrphanScout(parentRow, parentCol + 1,
						currentScout.getCookiesDiscovered() + cookieGrid[parentRow][parentCol + 1]);
				scoutStack.push(childScout);
			}
			if (currentScout.getCookiesDiscovered() > highestCookies) {
				highestCookies = currentScout.getCookiesDiscovered();
			}
		}
		// if (highestCookies < 0) {
		// 	highestCookies = 0;
		// }
		return highestCookies + cookieGrid[0][0];
	}
}
