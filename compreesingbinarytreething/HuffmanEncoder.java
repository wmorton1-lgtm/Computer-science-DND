import java.io.BufferedReader;
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
        for (int i = 1; i < 129; i++) {
            String codeforI = br.readLine();
            if (codeforI != null && codeforI != "") {
                characterBinaryGrid.put((char) i, codeforI);
            }
        }
        br.close();
    }

    public String encodeChar(char c) {
        if (characterBinaryGrid.get(c) != null) {
            return characterBinaryGrid.get(c);
        } else {
            return "";
        }
        
    }

    public void encodeFileToHuffmanCodes(String fileToCompress, String encodedFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileToCompress));
        PrintWriter pw = new PrintWriter(encodedFile + ".huf");
        int binaryCount = 0;
        while (br.ready()) {
            char c = (char) br.read();
            String charInBinary = encodeChar(c);
            pw.write(charInBinary);
            binaryCount+= charInBinary.length();
        } 

        pw.write(characterBinaryGrid.get(EOF));
        binaryCount+= characterBinaryGrid.get(EOF).length();

        for (int i = 0; i < 8 - (binaryCount % 8); i++) {
            pw.write('0');
        }
        br.close();
        pw.close();
    }
    
}
