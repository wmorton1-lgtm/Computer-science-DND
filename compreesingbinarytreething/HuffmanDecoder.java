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
        if (binary != null) {
            return binaryToCharGrid.get(binary);
        }
        return (char) 0;
    }

    public void decodeLong(String a, String b) throws IOException {
        decodeFileFromHuffmanCodes(a, b);
    }

    public void decodeFileFromHuffmanCodes(String encodedFile, String decodedFile)
            throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(encodedFile));
        PrintWriter pw = new PrintWriter(new FileWriter(decodedFile)); // null null null null null
                                                                       // CHANGED NAME Here
        String currbinary = "";
        int next = br.read();

        while (next != -1) {
            currbinary += (char) next;

            if (isCode(currbinary)) {
                if (decodeChar(currbinary) != EOF) {
                    pw.write(decodeChar(currbinary));
                    currbinary = "";
                } else {
                    break;
                    // six seven
                }
            }
            next = br.read();
        }
        br.close();
        pw.close();
    }

    public void decodeFile(String encodedFile) throws IOException {
        if (encodedFile.length() < 4
                || !encodedFile.substring(encodedFile.length() - 4).equals(".huf")) {
            throw new IllegalArgumentException("decodefiel() file wasnt named huffman ");
        }

        String tempFile = encodedFile + ".bruh";
        BufferedReader br = new BufferedReader(new FileReader(encodedFile));
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));


        int currentchar = br.read();


        while (currentchar != -1) {
            String currBinary = Integer.toBinaryString(currentchar);
            while (currBinary.length() < 8) {
                currBinary = "0" + currBinary;
            }
            pw.write(currBinary);
            currentchar = br.read();
        }
        br.close();
        pw.close();

        decodeFileFromHuffmanCodes(tempFile, makeFileName(encodedFile));
    }

    public String makeFileName(String fileName) {
        return fileName.substring(0, fileName.length() - 4);
    }
}
