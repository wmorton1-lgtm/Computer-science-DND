import java.io.IOException;

public class Huffamtester {
    public static void main(String[] args) throws IOException {
        // HuffmanCodeGenerator hi = new HuffmanCodeGenerator("wallermorton.txt");
        // HuffmanEncoder lol = new HuffmanEncoder("codefile.txt");
        // lol.encodeFileToHuffmanCodes("wallermorton.txt", "encodedfile.txt");
        HuffmanDecoder bro =  new HuffmanDecoder("encodedfile.txt");
        bro.decodeFileFromHuffmanCodes("encodedfile.txt", "decodedFile.txt");
    }
}
