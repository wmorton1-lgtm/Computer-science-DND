import java.rmi.dgc.DGC;
import java.util.ArrayList;

public class PugTester {
    public static void main(String[] args) {
        ArrayList<Dog> dogs = new ArrayList<Dog>();
        Dog jef = new Dog("Jef", "Golden Retriever");
        Dog pug = new Dog("Pug", "Pugdog");
        Dog xander = new Dog("Xander", "Golden Retriever");
        Dog mike = new Dog("Mike", "Orange doogle");
        Dog jackson = new Dog("Jackson", "Golden doodle");
        Dog tim = new Dog("Tim", "Golden Retriever");
        dogs.add(jef);
        dogs.add(pug);
        dogs.add(xander);
        dogs.add(mike);
        dogs.add(jackson);
        dogs.add(tim);

        System.out.println("before savedogs");
        for (int i = 0; i < dogs.size(); i++) {
            System.out.println(dogs.get(i).toString());

        }
        System.out.println("\n");
        System.out.println("after save dogs");
        PugSaver.rescuePugs(dogs);
        for (int i = 0; i < dogs.size(); i++) {
            System.out.println(dogs.get(i).toString());

        }
        
    }
}
