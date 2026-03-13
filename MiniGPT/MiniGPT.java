import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.module.InvalidModuleDescriptorException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import javax.security.auth.DestroyFailedException;
import javax.security.auth.login.AccountException;

public class MiniGPT {

    private HashMap<String, ArrayList<String>> hashMapOfData;
    private String fileName;

    public MiniGPT(String fileName, int chainOrder) {
        this.fileName = fileName;
        hashMapOfData = new HashMap<String, ArrayList<String>>();
        dataToHashMap(readData(fileName, chainOrder));
    }

    public ArrayList<String[]> readData(String filePath, int chainOrder) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int charAsInt;
            ArrayList<Character> lastXChars = new ArrayList<>();
            ArrayList<String[]> data = new ArrayList<>();

            while ((charAsInt = reader.read()) != -1) {
                // Cast the integer value to a character
                char character = (char) charAsInt;
                if (lastXChars.size() < chainOrder) {
                    lastXChars.add(character);
                    continue;
                } else {
                    String[] instance = new String[2];
                    instance[0] = charListToString(lastXChars);
                    instance[1] = character + "";
                    data.add(instance);
                    lastXChars.remove(0);
                    lastXChars.add(character);
                }
                return data;
            }
            return null;
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
        throw new InvalidModuleDescriptorException("idk what happened");
    }

    public String charListToString(ArrayList<Character> chars) {
        StringBuilder sb = new StringBuilder();
        for (Character character : chars) {
            sb.append(character);
        }
        return sb.toString();
    }

    public void dataToHashMap(ArrayList<String[]> data) {
        for (int i = 0; i < data.size(); i++) {
            String[] currentLine = data.get(i);
            if (!hashMapOfData.containsKey(currentLine[0])) {
                ArrayList<String> options = new ArrayList<String>();
                options.add(currentLine[1]);
                hashMapOfData.put(currentLine[0], options);
            } else {
                hashMapOfData.get(currentLine[0]).add(currentLine[1]);
            }

        }
    }

    public String makeFirstState(int numChars) {
        String firstState = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < numChars; i++) {
                int charAsInt = reader.read();
                char character = (char) charAsInt;
                firstState += character + "";
            }
        } catch (Exception e) {
            System.out.println("FILE NOT found");
        }
        return firstState;
    }

    public void generateText(String outputFileName, int numChars) {
        try (Writer writer = new FileWriter(outputFileName)) {

        } catch (Exception e) {
            System.out.println("NO file name found");
        }
    }

    public String predictNextState(String currentState) {
        ArrayList<String> possiblities = hashMapOfData.get(currentState);
        if (possiblities == null || possiblities.size() == 0) {
            return null;
        }
        return possiblities.get((int) (Math.random() * possiblities.size()));
    }
}
