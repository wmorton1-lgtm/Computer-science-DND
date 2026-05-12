import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
            if (codeforI != null && !codeforI.equals("")) {
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

    public void encodeLong(String a, String b) throws IOException {
        encodeFileToHuffmanCodes(a, b);
    }

    public void encodeFileToHuffmanCodes(String fileToCompress, String encodedFile)
            throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileToCompress));
        PrintWriter pw = new PrintWriter(new FileWriter(encodedFile)); // null null null null null
                                                                       // CHANGED NAME Here
        int binaryCount = 0;
        int currentchar = br.read();
        while (currentchar != -1) {
            char c = (char) currentchar;
            String charInBinary = encodeChar(c);
            pw.write(charInBinary);
            binaryCount += charInBinary.length();
            currentchar = br.read();
        }

        pw.write(characterBinaryGrid.get(EOF));
        binaryCount += characterBinaryGrid.get(EOF).length();

        for (int i = 0; i < (8 - (binaryCount % 8)) % 8; i++) {
            pw.write('0');
        }
        br.close();
        pw.close();
    }

    public void encodeFile(String fileToCompress) throws IOException {
        String tempFile = fileToCompress + ".bruh";
        encodeFileToHuffmanCodes(fileToCompress, tempFile);

        BufferedReader br = new BufferedReader(new FileReader(tempFile));
        PrintWriter pw = new PrintWriter(new FileWriter(fileToCompress + ".huf"));
        
        int currentchar = br.read();
        int charcount = 0;
        String currBinary = "";

        while (currentchar != -1) {
            currBinary += (char) currentchar;
            charcount++; 

            if (charcount == 8) {
                char c = (char) Integer.parseInt(currBinary, 2);
                pw.write(c);
                currBinary = "";
                charcount = 0;
            } 
            currentchar = br.read();
        }
        br.close();
        pw.close();
    }
}
