public class BinarySearchTester {
    public static void main(String[] args) {
        MyBST<Integer> binarySearchTree = new MyBST<Integer>();
        // System.out.println("1");
        binarySearchTree.add(6);
        // System.out.println("2");
        binarySearchTree.add(4);
        binarySearchTree.add(12);
        binarySearchTree.add(9);
        binarySearchTree.add(7);
        binarySearchTree.add(3);
        binarySearchTree.add(5);
        binarySearchTree.add(21);
        binarySearchTree.add(11);
        binarySearchTree.add(67676767);
        // System.out.println(binarySearchTree.toString());
        // System.out.println("3");
        System.out.println(binarySearchTree.toString());
        
    }
}
