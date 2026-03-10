import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MarkovPrediction {

    // Please define your own variables and data structures
    // private HashMap<String, String[]> chanceHashMap;

    // private ArrayList<String[]> internalDataList;
    private HashMap<String, ArrayList<String>> hashMapOfData;

    public MarkovPrediction(String filePath) {
        hashMapOfData = new HashMap<String, ArrayList<String>>();
        dataToHashMap(readData(filePath));
    }

    // public void fillWithArrayLists() {
    // for (int i = 0; i < hashMapOfData.size(); i++) {
    // hashMapOfData.
    // }
    // }

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

    public ArrayList<String[]> readData(String filePath) {
        ArrayList<String[]> dataList = new ArrayList<String[]>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(",");
                dataList.add(currentLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    // Method to predict the next state given a current state
    public String predictNextState(String currentState) {
        ArrayList<String> possiblities = hashMapOfData.get(currentState);
        return possiblities.get((int) (Math.random() * possiblities.size()));
    }

}
