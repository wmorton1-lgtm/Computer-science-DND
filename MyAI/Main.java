package MyAI;

// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose: 1) Chatbot  2) Simple NN XOR demo");
        String choice = sc.nextLine().trim();

        if ("1".equals(choice)) {
            ChatBot bot = new ChatBot();
            System.out.println("Chatbot started. Type 'quit' or 'bye' to exit.");
            while (true) {
                System.out.print("> ");
                String line = sc.nextLine();
                if (line == null) break;
                if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("bye")) {
                    System.out.println(bot.reply("bye"));
                    break;
                }
                System.out.println(bot.reply(line));
            }
        } else {
            SimpleNN.demo();
        }
        sc.close();
    }
}
