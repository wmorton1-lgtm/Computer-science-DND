import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class HuffmanEncoder {
    private char EOF = 26;
    private HashMap<Character, String> characterBinaryGrid;

    public HuffmanEncoder(String codeFile) throws IOException {
        characterBinaryGrid = new HashMap<Character, String>();

        BufferedReader br = new BufferedReader(new FileReader(codeFile));
        for (int i = 0; i < 128; i++) {
            String codeforI = br.readLine();
            if (codeforI != null && codeforI != "") {
                characterBinaryGrid.put((char) i, codeforI);
            }
        }
        br.close();
    }

    public String encodeChar(char c) {
        return characterBinaryGrid.get(c);
    }

    public void encodeFileToHuffmanCodes(String fileToCompress, String encodedFile) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(encodedFile));
        PrintWriter pw = new PrintWriter(encodedFile + ".huf");
        int binaryCount = 0;

    }
}
