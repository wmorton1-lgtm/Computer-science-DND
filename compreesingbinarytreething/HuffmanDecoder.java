import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class HuffmanDecoder {
    private HashMap<String, Character> binaryToCharGrid;
    private char EOF = 26;


    public HuffmanDecoder(String codeFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(codeFile));

        for (int i = 1; i < 129; i++) {
            String currLine = (br.readLine());
            if (!currLine.equals("")) {
                binaryToCharGrid.put(currLine, (char) i);
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
        PrintWriter pw = new PrintWriter(encodedFile + ".dec");
        String currbinary = (char) br.read() + "";
        while (br.ready()) {
            if (isCode(currbinary)) {
                if (decodeChar(currbinary) != EOF) {
                    pw.write(decodeChar(currbinary));
                    currbinary = "";
                } else {
                    // six seven
                }
            }
            currbinary += br.read();
        }
        br.close();
        pw.close();
    }
}
