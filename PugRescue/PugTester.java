import java.rmi.dgc.DGC;
import java.util.ArrayList;

public class PugTester {
    public static void main(String[] args) {
        ArrayList<Dog> dogs = new ArrayList<Dog>();
        Dog jef = new Dog("Jef", "Golden Retriever");
        Dog tim = new Dog("Tim", "Pug Retriever");
        dogs.add(jef);
        dogs.add(tim);
        for (int i = 0; i < dogs.size(); i++) {
            System.out.println(dogs.get(i).toString());
            
        }
        PugSaver.rescuePugs(dogs);
        for (int i = 0; i < dogs.size(); i++) {
            System.out.println(dogs.get(i).toString());
            
        }

    }
}
