public class HuffManTreeNode {
    // instance variables
    private String binaryCode;
    private HuffManTreeNode parent;
    private HuffManTreeNode leftChild;
    private HuffManTreeNode rightChild;
    private char value;
    private int frequency;


    // constructors


    public HuffManTreeNode(HuffManTreeNode parent, HuffManTreeNode leftChild,
            HuffManTreeNode rightChild, char value, int frequency) {
        this.binaryCode = "";
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.value = value;
        this.frequency = frequency;
    }

    // methods

    public String calculateBinaryCode() {
        if (isRoot()) {
            return "";
        }
        if (isLeftChild()) {
            return parent.getBinaryCode() + '0';
        } else {
            return parent.getBinaryCode() + '1';
        }
    }

    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeftChild() {
        return getParent().getLeftChild() == this;
    }


    // getters and setters
    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public String getBinaryCode() {
        return binaryCode;
    }

    public void setBinaryCode(String binaryCode) {
        this.binaryCode = binaryCode;
    }

    public HuffManTreeNode getParent() {
        return parent;
    }

    public void setParent(HuffManTreeNode parent) {
        this.parent = parent;
    }

    public HuffManTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(HuffManTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public HuffManTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(HuffManTreeNode rightChild) {
        this.rightChild = rightChild;
    }


    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }


}
