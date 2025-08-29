import java.rmi.dgc.DGC;
import java.util.ArrayList;

public class PugTester {
    public static void main(String[] args) {
        MyArrayList<Dog> dogs = new MyArrayList<Dog>();
        Dog jef = new Dog("Jef", "Golden Retriever");
        Dog xander = new Dog("Xander", "Golden Retriever");
        Dog tim = new Dog("Tim", "Pug Retriever");
        dogs.add(jef);
        dogs.add(tim);
        dogs.add(xander);
        for (int i = 0; i < dogs.size(); i++) {
            System.out.println(dogs.get(i).toString());
            
        }
        System.out.println("\n");
        PugSaver.rescuePugs(dogs);
        for (int i = 0; i < dogs.size(); i++) {
            System.out.println(dogs.get(i).toString());
            
        }

    }
}
