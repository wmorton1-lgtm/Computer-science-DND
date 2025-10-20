package MyAI;

// ChatBot.java
import java.util.*;
import java.util.regex.*;

public class ChatBot {
    private final Map<Pattern, String[]> rules = new LinkedHashMap<>();
    private final Random rnd = new Random();

    public ChatBot() {
        // Simple rule examples (regex -> possible replies)
        rules.put(Pattern.compile(".*\\bhello\\b.*", Pattern.CASE_INSENSITIVE),
                  new String[] {"Hi there!", "Hello! How can I help?", "Hey — what's up?"});
        rules.put(Pattern.compile(".*\\bhow are you\\b.*", Pattern.CASE_INSENSITIVE),
                  new String[] {"I'm a program, but thanks for asking!", "Doing great — ready to help."});
        rules.put(Pattern.compile(".*\\bname\\b.*", Pattern.CASE_INSENSITIVE),
                  new String[] {"You can call me JavaAI.", "I'm JavaAI — your tiny assistant."});
        rules.put(Pattern.compile(".*\\b(joke|funny)\\b.*", Pattern.CASE_INSENSITIVE),
                  new String[] {"Why do programmers prefer dark mode? Because light attracts bugs!", "I told a CPU a joke — it didn't laugh, it just cached it."});
        rules.put(Pattern.compile(".*\\bbye\\b.*", Pattern.CASE_INSENSITIVE),
                  new String[] {"Goodbye!", "See you later!", "Bye — take care!"});
        // fallback handled below
    }

    public String reply(String input) {
        input = input.trim();
        if (input.isEmpty()) return "Say something — I'm listening.";

        for (Map.Entry<Pattern, String[]> e : rules.entrySet()) {
            Matcher m = e.getKey().matcher(input);
            if (m.matches()) {
                String[] options = e.getValue();
                return options[rnd.nextInt(options.length)];
            }
        }

        // Simple transformation: reflect pronouns
        String reflected = reflectPronouns(input);
        return "Tell me more about " + reflected + ".";
    }

    private String reflectPronouns(String s) {
        String[][] swaps = {
            {"\\bI\\b", "you"}, {"\\bme\\b", "you"}, {"\\bmy\\b", "your"},
            {"\\bI'm\\b", "you're"}, {"\\bmine\\b", "yours"}, {"\\byou\\b", "I"},
            {"\\byour\\b", "my"}, {"\\byours\\b", "mine"}
        };
        String out = s;
        for (String[] pair : swaps) {
            out = out.replaceAll("(?i)" + pair[0], pair[1]);
        }
        return out;
    }

    // You can add rules at runtime
    public void addRule(String regex, String... replies) {
        rules.put(Pattern.compile(regex, Pattern.CASE_INSENSITIVE), replies);
    }
}
