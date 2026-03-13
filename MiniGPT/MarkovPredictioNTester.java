public class MarkovPredictioNTester {
    public static void main(String[] args) {
        MarkovPrediction model = new MarkovPrediction("weather.csv");
        System.out.println(model.readData("weather.csv"));
        System.out.println(model.predictNextState("Sunny"));
        System.out.println(model.predictNextState("Cloudy"));
        System.out.println(model.predictNextState("Rainy"));
    }
}
