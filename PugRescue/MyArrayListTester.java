import java.util.ArrayList;

public class MyArrayListTester {
    public static void main(String[] args) {
        MyArrayList<String> dogs = new MyArrayList<String>();
        // Dog jef = new Dog("Jef", "Golden Retriever");
        // Dog pug = new Dog("Pug", "Pugdog");
        // Dog xander = new Dog("Xander", "Golden Retriever");
        // Dog mike = new Dog("Mike", "Orange doogle");
        // Dog jackson = new Dog("Jackson", "Golden doodle");
        // Dog tim = new Dog("Tim", "Golden Retriever");
        // dogs.add(jef);
        // dogs.add(pug);
        // dogs.add(xander);
        // dogs.add(mike);
        // dogs.add(jackson);
        // dogs.add(tim);

        for (int i = 1; i < 6; i++) {
            dogs.add(i + "");
        }

        System.out.println(dogs.toString());
        // for (int i = 0; i < dogs.size(); i++) {
        //     System.out.println(dogs.get(i).toString());
        //     System.out.println(Integer.toString(dogs.objectCount));
        // }

        // *INITIAIZLISNG** ^^^


        // System.out.println("\n _____________INITALIZING OVER____");
        // dogs.remove(pug);
        // System.out.println(dogs.toString());
        // System.out.println("\n");

        // for (int i = 0; i < dogs.size(); i++) {
        //     System.out.println(dogs.get(i).toString());
        // }
        // System.out.println("\n");
        
    }
}
