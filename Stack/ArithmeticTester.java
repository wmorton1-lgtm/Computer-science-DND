public class ArithmeticTester {
    public static void main(String[] args) {
        String xanderIsGae = "6*7+(4-1)+1+1";
        // String xanderIsGae = "6 * 7";
        System.out.println(xanderIsGae);
        String xIG = Arithmetic.convertClassicToStout(xanderIsGae);
        System.out.println(xIG);
        System.out.println(Arithmetic.evaluateStout(xIG));
    }
}