import java.io.IOException;

public class Huffamtester {
    public static void main(String[] args) throws IOException {
        HuffmanCodeGenerator hi = new HuffmanCodeGenerator("wallermorton.txt");
        System.out.println(hi.getFrequency('a'));
    }
}
