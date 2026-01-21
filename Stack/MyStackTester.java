public class MyStackTester {

    public static void main(String[] args) {

        MyStack<String> foo = new MyStack<String>();
        foo.push("*Ohio impressed*");
        String str = foo.peek();
        str = foo.pop();
        System.out.println(foo.empty());
        System.out.println(str);
    }
}
