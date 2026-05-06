import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class HuffmanDecoder {
    private HashMap<String, Character> binaryToCharGrid;
    private char EOF = 26;


    public HuffmanDecoder(String codeFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(codeFile));
        binaryToCharGrid = new HashMap<String, Character>();
        for (int i = 1; i < 129; i++) {
            String currLine = (br.readLine());
            if (currLine != null && !currLine.equals("")) {
                binaryToCharGrid.put(currLine, (char) (i - 1));
            }
        }
        br.close();
    }

    public boolean isCode(String binary) {
        return binaryToCharGrid.get(binary) != null;
    }

    public char decodeChar(String binary) {
        return binaryToCharGrid.get(binary);
    }

    public void decodeFileFromHuffmanCodes(String encodedFile, String decodedFile)
            throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(encodedFile));
        PrintWriter pw = new PrintWriter(new FileWriter(decodedFile)); // null null null null null CHANGED NAME Here
        String currbinary = "";
        while (br.ready()) {
            if (isCode(currbinary)) {
                if (decodeChar(currbinary) != EOF) {
                    pw.write(decodeChar(currbinary));
                    currbinary = "";
                } else {
                    break;
                    // six seven
                }
            }
            currbinary += br.read();
        }
        br.close();
        pw.close();
    }
}
