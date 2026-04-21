import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;

public class HuffmanCodeGenerator {
    private HashMap<Character, Integer> characterFrequencyGrid;
    private char EOF = 26;

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
}

