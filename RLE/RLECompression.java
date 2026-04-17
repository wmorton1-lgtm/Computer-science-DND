import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class RLECompression {

    // Creates a compressed version with fileName + ".rle.bw"
    public static void compress(String fileName) throws IOException {
        bwTransform(fileName);
        encode(fileName + ".bw");
    }

    // Decompresses a .rle.bw file
    public static void decompress(String fileName) throws IOException {
        decode(fileName);
        invertBWTransform(fileName.substring(0, fileName.length() - 4));
    }

    // Encodes the contents of a file using the RLE compression scheme:
    // single characters are left alone, and runs of 2+ characters are encoded as
    // that letter twice, followed by the length of the run, cast as a char
    public static void encode(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        PrintWriter pw = new PrintWriter(fileName + ".rle");
        char previousChar = (char) br.read();
        char c = 0;
        int count = 1;
        while (br.ready()) {
            c = (char) br.read();
            if (previousChar == c) {
                count++;
            } else {
                if (count > 1) {
                    pw.print("" + previousChar + previousChar + count);
                    count = 1;
                } else {
                    pw.print(previousChar + "");
                }
            }
            previousChar = c;
        }
        if (count > 1) {
            pw.print("" + previousChar + previousChar + count);
        } else {
            pw.print(previousChar + "");
        }
        br.close();
        pw.close();
    }

    // Decodes the above scheme
    public static void decode(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        PrintWriter pw = new PrintWriter(fileName.substring(0, fileName.length() - 4));

        char previousChar = (char) br.read();
        boolean printLastChar = true;
        while (br.ready()) {
            char c = (char) br.read();
            if (previousChar == c) {
                int toRepeat = (char) br.read() - '0';
                for (int i = 0; i < toRepeat; i++) {
                    pw.print(previousChar);
                }
                if (br.ready()) {
                    previousChar = (char) br.read();
                } else {
                    printLastChar = false;
                }

            } else {
                pw.print(previousChar);
                previousChar = c;
            }
        }

        if (printLastChar == true) {
            pw.print(previousChar);
        }

        br.close();
        pw.close();
    }

    public static void bwTransform(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        // Add a null character at the beginning, as a
        // "beginning of file" character
        StringBuilder originalText = new StringBuilder("" + '\0');

        while (br.ready()) {
            char c = (char) br.read();
            originalText.append(c);
        }
        br.close();

        String[] rotations = new String[originalText.length()];
        rotations[0] = originalText.toString();
        // TO-DO
        // Now do the Burrows-Wheeler Transform
        for (int index = 1; index < rotations.length; index++) {
            rotations[index] = rotations[index - 1].substring(1) + rotations[index - 1].charAt(0);
        }
        Arrays.sort(rotations);
        // And then write the transformation into a file
        PrintWriter pw = new PrintWriter(fileName + ".bw");
        for (int i = 0; i < rotations.length; i++) {
            pw.print(rotations[i].charAt(rotations[i].length() - 1));
        }
        pw.close();
    }

    public static void invertBWTransform(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        StringBuilder originalText = new StringBuilder();

        while (br.ready()) {
            char c = (char) br.read();
            originalText.append(c);
        }
        br.close();

        StringBuilder[] reconstructions = new StringBuilder[originalText.length()];
        for (int i = 0; i < reconstructions.length; i++) {
            reconstructions[i] = new StringBuilder("" + originalText.charAt(i));
        }
        // TO-DO
        // Now undo the Burrows-Wheeler transform
        for (int i = 1; i < originalText.length(); i++) {
            for (int j = 0; j < reconstructions.length - 1; j++) {
                for (int k = 0; k < reconstructions.length - 1 - j; k++) {
                    if (reconstructions[k].toString()
                            .compareTo(reconstructions[k + 1].toString()) > 0) {
                        StringBuilder temp = reconstructions[k];
                        reconstructions[k] = reconstructions[k + 1];
                        reconstructions[k + 1] = temp;
                    }
                }
            }
            for (int index = 0; index < reconstructions.length; index++) {
                reconstructions[index].insert(0, originalText.charAt(index));
            }
        }

        for (int i = 0; i < reconstructions.length - 1; i++) {
            for (int j = 0; j < reconstructions.length - 1 - i; j++) {
                if (reconstructions[j].toString()
                    .compareTo(reconstructions[j + 1].toString()) > 0) {
                StringBuilder temp = reconstructions[j];
                reconstructions[j] = reconstructions[j + 1];
                reconstructions[j + 1] = temp;
            }
            }
        }
        // TO-DO
        // And write the appropriate reconstruction into the file, without the null char
        PrintWriter pw = new PrintWriter(fileName.substring(0, fileName.length() - 3));
        for (int i = 0; i < reconstructions.length; i++) {
            if (reconstructions[i].charAt(0) == '\0') {
                pw.print(reconstructions[i].substring(1));
            }
        }
        pw.close();
    }
}
