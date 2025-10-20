public class TestRecursion {
    public static void main(String[] args) {
        ListNode one = new ListNode("One");
        ListNode two = new ListNode("Two");
        ListNode three = new ListNode("Three");
        one.setNext(two);
        two.setNext(three);
        
        Recursion.printListInReverse(one);

        
    }
}