
public class Arithmetic {
    // Evaluates a String exp that has an arithmetic expression, written in
    // classic notation
    public static int evaluate(String exp) {
        return evaluateStout(convertClassicToStout(exp));
    }

    // Returns the result of doing operand1 operation operand2,
    // e.g. operate(5, 2, "-") should return 3
    public static int operate(int operand1, int operand2, String operation) {
        if (operation == null || operation.equals("")) {

        }
        if (operation == "-") {
            return operand1 - operand2;
        }
        if (operation == "+") {
            return operand1 + operand2;
        }
        if (operation == "*") {
            return operand1 * operand2;
        }
        if (operation == "%") {
            return operand1 % operand2;
        }
        if (operation == "/") {
            return operand1 / operand2;
        }
        if (operation == "^") {
            return (int) Math.pow(operand1, operand2);
        }
        throw new IllegalArgumentException("operate(): invalid operator");
    }

    // Evaluates a String exp that has an arithmetic expression written in STOUT
    // notation
    public static int evaluateStout(String exp) {
        char[] array = exp.toCharArray();
        for (int i = 0; i < array.length - 2; i++) {
            if (i < 2 && !isOperator(array[i])) {
                
            } else if (isOperator(array[i])) {
                operate(Character.valueOf(array[i - 2]), Character.valueOf(array[i - 1]), array[i] + "");
            } else {
                throw new IllegalArgumentException("evaluateStout(): invalid stout format");
            }
        }
    }

    public static String convertClassicToStout(String exp) {
        MyStack<Character> oppStack = new MyStack<Character>();
        char[] expArray = exp.toCharArray();
        StringBuilder stoutForm = new StringBuilder();


        for (int i = 0; i < expArray.length; i++) {
            char charI = expArray[i];

            if (charI == ' ') {
                
            } else if (charI == '(') {
                oppStack.push(charI);

            } else if (charI == ')') {
                while (!oppStack.empty() && oppStack.peek() != '(') {
                    stoutForm.append(oppStack.pop());
                }
                if (oppStack.empty()) {
                    throw new IllegalArgumentException(
                            "convertClassicToStout(): Invalid character");
                }
                oppStack.pop();

            } else if ((int) charI >= 48 && (int) charI <= 57) {
                stoutForm.append(charI);

            } else {
                if (isOperator(charI)) {
                    if (charI == '-' && ((int) expArray[i+1] >= 48 && (int) expArray[i+1] <= 57)) {
                        
                    }
                    if (oppStack.empty() || isOperatorAGreaterThanB(charI, oppStack.peek())) {
                        oppStack.push(charI);
                    } else {
                        while (!oppStack.empty() && oppStack.peek() != '('
                                && isOperatorAGreaterThanB(oppStack.peek(), charI)) {
                            stoutForm.append(oppStack.pop());
                        }
                        oppStack.push(charI);
                    }
                } else {
                    throw new IllegalArgumentException(
                            "convertClassicToStout(): Invalid character");
                }
            }
        }

        while (!oppStack.empty()) {
            if (oppStack.pop() == '(') {
                throw new IllegalArgumentException("convertClassicToStout(): Invalid character");
            }
            stoutForm.append(oppStack.pop());
        }
        return stoutForm + "";
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^';
    }

    public static boolean isOperatorAGreaterThanB(char a, char b) {
        int AValue = -1;
        int BValue = -1;

        if (a == '+' || a == '-') {
            AValue = 1;
        }
        if (a == '*' || a == '/' || a == '%') {
            AValue = 2;
        }
        if (a == '^') {
            AValue = 3;
        }

        if (b == '+' || b == '-') {
            BValue = 1;
        }
        if (b == '*' || b == '/' || b == '%') {
            BValue = 2;
        }
        if (b == '^') {
            BValue = 3;
        }

        return (AValue > BValue) || (AValue == BValue && b != '^');
    }
}
