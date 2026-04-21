import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.util.PriorityQueue;

public class HuffmanCodeGenerator {
    private HashMap<Character, Integer> characterFrequencyGrid;
    private char EOF = 26;
    private ArrayList<Character> charListInOrderOfFrequency;
    private PriorityQueue<Character> charHeap;

    public HuffmanCodeGenerator(String frequencyFile) throws IOException {
        characterFrequencyGrid = new HashMap<Character, Integer>();
        readData(frequencyFile);
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
                charHeap.add(c);
                characterFrequencyGrid.put(c, 1);
            }
            charAsInt = br.read();
        }

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
        for (int i = 0; i < charHeap.size(); i++) {
            charListInOrderOfFrequency.add(charHeap.poll());
        }
    }
}

