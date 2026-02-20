package SortingNamesLab;

import java.util.ArrayList;

public class GridTester {
    public static void main(String[] args) {
        MyGrid foo = new MyGrid();
        int[] indexList = new int[10];
        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("wallerMorton");
        stringList.add("Xander");
        stringList.add("JAmes");
        stringList.add("EVan");
        stringList.add("JAckson");
        stringList.add("Mrtheistables");
        stringList.add("mattin");
        stringList.add("bob");
        stringList.add("kai cenat");
        stringList.add("duke-dennis");
    
        for (int i = 0; i < indexList.length; i++) {
            indexList[i] = MyGrid.nameToIndex(stringList.get(i));
        }
    
    }
}
