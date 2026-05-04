import java.io.IOException;

public class Huffamtester {
    public static void main(String[] args) throws IOException {
        // HuffmanCodeGenerator hi = new HuffmanCodeGenerator("wallermorton.txt");
        HuffmanEncoder lol = new HuffmanEncoder("wallermorton.txt.cf");
        lol.encodeFileToHuffmanCodes("wallermorton.txt", "wallermorton.txt.enc");
    }
}
