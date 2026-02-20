package SortingNamesLab;

public class MyGrid {
    int[] internalGrid;


    MyGrid() {
        internalGrid = new int[500];
    }


    public int[] getInternalGrid() {
        return internalGrid;
    }

    public void setInternalGrid(int[] internalGrid) {
        this.internalGrid = internalGrid;
    }

    public  static int nameToIndex(String name) {
        int index = 0;
        for (int i = 0; i < name.length(); i++) {
            index += (Character.valueOf(name.charAt(index))*74)^(name.length() - 6);
        }
        return index % 500;
    }

    
    public int add(String name) {
        // name.hashCode();
        // name.
        // internalGrid[nameToIndex(name)] = name;

    }
}
