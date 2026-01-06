public class SortedArratListTester {
    public static void main(String[] args) {
        String[] reglist = {"aller", "sigma", "zander"};
        SortedArrayList<String> list = new SortedArrayList<String>();
        for (int i = 0; i < reglist.length; i++) {
            list.add(reglist[i]);
        }
        System.out.println(list.toString());
        
    }
}
