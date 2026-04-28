import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.io.PrintWriter;

public class HuffmanCodeGenerator {
    private HashMap<Character, Integer> characterFrequencyGrid;
    private ArrayList<Character> charListInOrderOfFrequency;
    private HashMap<Character, String> characterBinaryGrid;
    private char EOF = 26;
    private HuffManTreeNode root;

    public HuffmanCodeGenerator(String frequencyFile) throws IOException {
        characterFrequencyGrid = new HashMap<Character, Integer>();
        characterBinaryGrid = new HashMap<Character, String>();
        charListInOrderOfFrequency = new ArrayList<Character>();

        readData(frequencyFile);
        sortCharArray();
        root = makeHuffmanTree();
    }

    public void readData(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        int charAsInt = br.read();
        while (charAsInt != -1) {
            char c = (char) charAsInt;
            if (characterFrequencyGrid.containsKey(c)) {
                int charFrequency = getFrequency(c);
                characterFrequencyGrid.put(c, charFrequency + 1);
            } else {
                charListInOrderOfFrequency.add(c);
                characterFrequencyGrid.put(c, 1);
            }
            charAsInt = br.read();
        }

        charListInOrderOfFrequency.add(EOF);
        characterFrequencyGrid.put(EOF, getFrequency(EOF) + 1);
        br.close();
    }

    public int getFrequency(char c) {
        Integer frequency = characterFrequencyGrid.get(c);
        if (frequency != null) {
            return frequency;
        } else {
            return 0;
        }
    }

    public void sortCharArray() {
        for (int i = 0; i < charListInOrderOfFrequency.size() - 1; i++) {
            for (int j = 0; j < charListInOrderOfFrequency.size() - 1 - i; j++) {
                if (getFrequency(charListInOrderOfFrequency.get(j)) > charListInOrderOfFrequency
                        .get(j + 1)) {
                    char temp = charListInOrderOfFrequency.get(j);
                    charListInOrderOfFrequency.set(j, charListInOrderOfFrequency.get(j + 1));
                    charListInOrderOfFrequency.set(j + 1, temp);
                }
            }
        }

    }

    public HuffManTreeNode makeHuffmanTree() {
        ArrayList<HuffManTreeNode> nodeList = new ArrayList<HuffManTreeNode>();
        for (int i = 0; i < charListInOrderOfFrequency.size(); i++) {
            char c = charListInOrderOfFrequency.get(i);
            HuffManTreeNode toAdd = new HuffManTreeNode(null, null, null, c, getFrequency(c));
            nodeList.add(toAdd);
        }

        while (nodeList.size() > 1) {
            sortNodeArray(nodeList);

            HuffManTreeNode left = nodeList.remove(0);
            HuffManTreeNode right = nodeList.remove(0);
            HuffManTreeNode parent = new HuffManTreeNode(null, left, right, (char) 0,
                    left.getFrequency() + right.getFrequency());

            left.setParent(parent);
            right.setParent(parent);
            nodeList.add(parent);
        }
        return nodeList.get(0);
    }

    public void sortNodeArray(ArrayList<HuffManTreeNode> nodes) {
        for (int i = 0; i < nodes.size() - 1; i++) {
            for (int j = 0; j < nodes.size() - 1 - i; j++) {
                if (nodes.get(j).getFrequency() > nodes.get(j + 1).getFrequency()) {
                    HuffManTreeNode temp = nodes.get(j);
                    nodes.set(j, nodes.get(j + 1));
                    nodes.set(j + 1, temp);
                }
            }
        }
    }

    public void makeBinaryGridFromTree(HuffManTreeNode node, String binaryCode) {
        if (node == null) {
            throw new IllegalArgumentException("makeBinaryGridFromTree(): root/node was null");
        }

        if (node.isLeaf()) {
            if (binaryCode == "") {
                binaryCode = "0";
            }

            node.setBinaryCode(binaryCode);
            characterBinaryGrid.put(node.getValue(), binaryCode);
        } else {
            makeBinaryGridFromTree(node.getLeftChild(), binaryCode + "0");
            makeBinaryGridFromTree(node.getRightChild(), binaryCode + "1");
        }
    }

    public String getCode(char c) {
        if (characterBinaryGrid.get(c) != null) {
            return characterBinaryGrid.get(c);
        } else {
            return "";
        }
    }

    public void makeCodeFile(String codeFile) throws IOException {
        PrintWriter pw = new PrintWriter(codeFile);
        for (int i = 0; i < 128; i++) {
            if (characterBinaryGrid.get((char) i) != null) {
                pw.println(characterBinaryGrid.get((char) i));
            } else {
                pw.println("");
            }
        }
        pw.close();
    }
}

