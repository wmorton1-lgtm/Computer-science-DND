import java.io.IOException;

public class HuffmanTester {
    public static void main(String[] args) throws IOException {
        HuffmanCodeGenerator hi = new HuffmanCodeGenerator("wallermorton.txt");
        HuffmanEncoder lol = new HuffmanEncoder("codefile.txt");
        lol.encodeFileToHuffmanCodes("wallermorton.txt", "encodedfile.txt");
        lol.encodeFile("encodedFile.txt");
        HuffmanDecoder bro =  new HuffmanDecoder("codeFile.txt");
        bro.decodeFile("encodedFile.txt.huf");
        bro.decodeFileFromHuffmanCodes("encodedfile.txt", "decodedFile.txt");
    }
}
