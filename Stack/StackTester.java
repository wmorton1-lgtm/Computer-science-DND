public class StackTester {
    public static void main(String[] args) {
        MyStack<Character> s = new MyStack<>();
        System.out.println(s.empty()); // should be true
        s.push('(');
        System.out.println(s.empty()); // should be false
        System.out.println(s.peek());  // should print '('
        System.out.println(s.pop());   // should print '('
        System.out.println(s.empty()); // should be true
    }
}
