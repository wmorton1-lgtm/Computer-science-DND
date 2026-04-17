import java.io.IOException;

public class rlecompressiontester {
    public static void main(String[] args) throws IOException {
        RLECompression.encode("toCompress.txt");
        RLECompression.compress("toCompress.txt");
        RLECompression.decompress("toCompress.txt.bw.rle");
    }
}
