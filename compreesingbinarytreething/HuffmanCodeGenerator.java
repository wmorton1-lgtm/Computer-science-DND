import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;

public class HuffmanCodeGenerator {
    private HashMap<Character, Integer> characterFrequencyGrid;

    public HuffmanCodeGenerator(String frequencyFile) {
        characterFrequencyGrid = new HashMap<Character, Integer>();

    }

    public void readData(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        char c = (char) br.read();
        while (br.ready()) {
            if (characterFrequencyGrid.containsKey(c)) {
                int charFrequency = getFrequency(c);
                characterFrequencyGrid.put(c, charFrequency + 1);
            } else {
                characterFrequencyGrid.put(c, 1);
            }
        }
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

