import java.sql.ShardingKeyBuilder;

public class LinkedListTester {
    public static void main(String[] args) {
        String[] values = new String[5];
        values[0] = "one";
        values[1] = "two";
        values[2] = "three";
        values[3] = "four";
        values[4] = "five";
        SinglyLinkedList xander = new SinglyLinkedList<String>(values);

        xander.toString();

    }
}
