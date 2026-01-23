
public class Arithmetic {
    // Evaluates a String exp that has an arithmetic expression, written in
    // classic notation
    public static int evaluate(String exp) {
        return evaluateStout(convertClassicToStout(exp));
    }

    // Returns the result of doing operand1 operation operand2,
    // e.g. operate(5, 2, "-") should return 3
    public static int operate(int operand1, int operand2, String operation) {
        
    }

    // Evaluates a String exp that has an arithmetic expression written in STOUT
    // notation
    public static int evaluateStout(String exp) {
        return 0;
    }

    public static String convertClassicToStout(String exp) {
        MyStack<Character> operatorStack = new MyStack<Character>();
        char[] expArray = exp.toCharArray();
        String operatorListString = "()-+/*^%";
        char[] operatorList = operatorListString.toCharArray();
        StringBuilder stoutForm = new StringBuilder();
        

        for (int i = 0; i < expArray.length; i++) {
            char charI = expArray[i];

            if ((int) charI >= 48 && (int) charI <= 57) { // if character number

            } else if (charI == '(' || charI == ')' || (charI == '-' && expArray[i + 1] == ' ')
                    || charI == '+' || charI == '/' || charI == '*' || charI == '^'
                    || charI == '%') {// if its an operation

                    stoutForm.append(charI);
                if (operatorStack.peek().compareTo(charI) <= 0) {
                    for (char current = operatorStack.peek(); !operatorStack.empty(); operatorStack.pop()) {
                    
                    }
                    operatorStack.push(charI);
                }
            }
        }
        return "";
    }

    public boolean isOperatorAGreaterThanB(char a, char b) {
        int AValue = -1;
        int BValue = -1;
        if (a == ')') { 
            BValue = 0;
        }
        if (b == ')') { 
            BValue = 0;
        }

        if (a == '+' || a == '-') {
            AValue = 1;
        }
        if (b == '+' || b == '-') {
            BValue = 1;
        }

        if (a == '*' || a == '/' || a == '%') {
            AValue = 2;
        }
        if (b == '*' || b == '/' || b == '%') {
            BValue = 2;
        }

        if (a == '^') {
            AValue = 3;
        }
        if (b == '^') {
            AValue = 3;
        }

        if (a == '(') { 
            BValue = 4;
        }
        if (b == '(') { // TO-DO:
            BValue = 4;
        }

        return AValue > BValue;
    } 
}
