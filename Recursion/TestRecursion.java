public class TestRecursion {
    public static void main(String[] args) {
        // // printlistinreverse
        // ListNode one = new ListNode("One");
        // ListNode two = new ListNode("Two");
        // ListNode three = new ListNode("Three");
        // one.setNext(two);
        // two.setNext(three);
        // Recursion.printListInReverse(one);

        // infect
        String[][] list = new String[5][5];
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                list[i][j] = (j+1) + "";
            }
        }
        Recursion.print2DArray(list);

        
    }
}