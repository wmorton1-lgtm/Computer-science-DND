public class MiniGPTTester {
    public static void main(String[] args) {
        MiniGPT bot = new MiniGPT("thegreatgatsby.txt", 3);
        bot.generateText("PredictionDoc.txt", 100);
    }
}
