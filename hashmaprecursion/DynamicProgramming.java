import java.util.HashMap;

public class DynamicProgramming {



    // Every day for the rest of the year, you're going to be given a choice between two jobs to do:
    // one that is LOW stress, and one that is HIGH stress. Each job pays out a dollar amount;
    // *usually* the high stress jobs pay more. However, after doing a high stress job, you need to
    // REST for a day.

    // Given a list of all the payouts for all the low stress and high stress jobs,
    // what is the most amount of money you can get?

    // You can assume lowPayouts.length == highPayouts.length
    public static int hiLoStress(int[] lowPayouts, int[] highPayouts) {
        HashMap<Integer, Integer> dayToBestValueMap = new HashMap<>();
        return hiLoStressHelper(lowPayouts, highPayouts, 0, false, dayToBestValueMap);
    }

    public static int hiLoStressHelper(int[] lowPayouts, int[] highPayouts, int day,
            boolean stressed, HashMap<Integer, Integer> hash) {
        if (day > highPayouts.length) {
            return 0;
        }
        if (stressed) {
            // if (hash.get(day) != null) {
            // int todaysSolution = hiLoStressHelper(lowPayouts, highPayouts, day++, false, hash);
            // hash.put(day, todaysSolution);
            // return todaysSolution;
            // } else {
            // return hash.get(day);
            // }
            return hiLoStressHelper(lowPayouts, highPayouts, day++, false, hash);
        }
        if (hash.get(day) == null) {
            int todaysSolution = Math.max(
                    lowPayouts[day] + hiLoStressHelper(lowPayouts, highPayouts, day++, false, hash),
                    highPayouts[day]
                            + hiLoStressHelper(lowPayouts, highPayouts, day++, true, hash));
            hash.put(day, todaysSolution);
            return todaysSolution;
        } else {
            return hash.get(day);
        }
    }


    // You are partaking in a scavenger hunt!
    // You've gotten a secret map to find many of the more difficult
    // items, but they are only available at VERY specific times at
    // specific places. You have an array, times[], that lists at which
    // MINUTE an item is available, in increasing order.
    // Items in the ScavHunt are worth varying numbers of points.
    // You also have an array, points[], same length as times[],
    // that lists how many points each of the corresponding items is worth.
    // Problem is: to get from one location to the other takes 5 minutes,
    // so if there is an item, for example, available at time 23 and another
    // at time 27, it's just not possible for you to make it to both: you'll
    // have to choose!
    // Write a method that returns the maximum POINTS you can get.
    public static int scavHunt(int[] times, int[] points) {
        HashMap<Integer, Integer> timeToMaxRewardsMap = new HashMap<>();
        return findMaxReward(times, points, 0, timeToMaxRewardsMap);
    }

    public static int findMaxReward(int[] times, int[] points, int i,
            HashMap<Integer, Integer> map) {
        if (i >= times.length) {
            return 0;
        }
        if (map.containsKey(i)) {
            return map.get(i);
        } else {
            int doesTake = points[i];
            if (nextOneThatsFiveAway(i, times) != -1) {
                doesTake += findMaxReward(times, points, nextOneThatsFiveAway(i, times), map);
            }
            int dontTake = findMaxReward(times, points, i + 1, map);
            int solution = Math.max(doesTake, dontTake);
            map.put(i, solution);
            return solution;
        }
    }

    public static int nextOneThatsFiveAway(int index, int[] times) {
        for (int i = index + 1; i < times.length; i++) {
            if (times[i] >= times[index] + 5) {
                return i;
            }
        }
        return -1;
    }



    /*
     * Uses memoization to calculate the route which grants the most cookies, starting at [0][0],
     * only going right or down at each point
     */
    public static int dynamicCookies(int[][] cookieGrid) {
        return recursiveCookies(cookieGrid);
    }

    public static int recursiveCookies(int[][] grid) {
        if (!validPoint(0, 0, grid)) {
            return 0;
        }
        HashMap<String, Integer> squareToMaxMap = new HashMap<>();
        return recursiveCookies(0, 0, grid, squareToMaxMap);
    }

    // Returns the maximum number of cookies edible starting from (and including)
    // cookieGrid[row][col]
    public static int recursiveCookies(int row, int col, int[][] cookieGrid,
            HashMap<String, Integer> hm) {
        int totalRight = 0;
        int totalDown = 0;
        if (!validPoint(row + 1, col, cookieGrid) && !validPoint(row, col + 1, cookieGrid)) {
            return cookieGrid[row][col];
        }
        if (validPoint(row + 1, col, cookieGrid)) { // done 
            if (hm.containsKey(String.valueOf(row+1) + String.valueOf(col))) {
                totalDown = hm.get(String.valueOf(row+1) + String.valueOf(col));
            } else {
                totalDown += recursiveCookies(row + 1, col, cookieGrid, hm) + cookieGrid[row][col];
                hm.put(String.valueOf(row+1) + String.valueOf(col), totalDown);
            }
        }
        if (validPoint(row, col + 1, cookieGrid)) { // todo fix this for going down instead of right
            totalRight += recursiveCookies(row, col + 1, cookieGrid, hm) + cookieGrid[row][col];
        }
        return Math.max(totalRight, totalDown);
    }

    private static boolean validPoint(int row, int col, int[][] cookieGrid) {
        int numCols = cookieGrid.length;
        int numRows = cookieGrid[0].length;

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
}
