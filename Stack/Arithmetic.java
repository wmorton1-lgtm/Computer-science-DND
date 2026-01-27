
public class Arithmetic {
    // Evaluates a String exp that has an arithmetic expression, written in
    // classic notation
    public static int evaluate(String exp) {
        return evaluateStout(convertClassicToStout(exp));
    }

    // Returns the result of doing operand1 operation operand2,
    // e.g. operate(5, 2, "-") should return 3
    public static int operate(int operand1, int operand2, String operation) {
        if (operation == null || operation.isEmpty()) {
            throw new IllegalArgumentException("operate invalid");
        }
        if (operation.equals("-")) {
            return operand1 - operand2;
        }
        if (operation.equals("+")) {
            return operand1 + operand2;
        }
        if (operation.equals("*")) {
            return operand1 * operand2;
        }
        if (operation.equals("%")) {
            return operand1 % operand2;
        }
        if (operation.equals("/")) {
            return operand1 / operand2;
        }
        if (operation.equals("^")) {
            return (int) Math.pow(operand1, operand2);
        }
        throw new IllegalArgumentException("operate(): invalid operator");
    }

    // Evaluates a String exp that has an arithmetic expression written in STOUT
    // notation
    public static int evaluateStout(String exp) {
        if (exp == null || exp.isBlank()) {
            throw new IllegalArgumentException("evalSt(): empty exp");
        }

        MyStack<Integer> stack = new MyStack<Integer>();
        int i = 0;

        while (i < exp.length()) {
            char charI = exp.charAt(i);

            if (Character.isWhitespace(charI)) {
                i++;
            }

            else if (Character.isDigit(charI) || (charI == '-' && i + 1 < exp.length()
                    && Character.isDigit(exp.charAt(i + 1)))) {
                int isPositive = 1;
                if (charI == '-') {
                    isPositive = -1;
                    i++;
                }
                int num = 0;
                while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
                    num = num * 10 + (exp.charAt(i) - '0');
                    i++;
                }
                stack.push(isPositive * num);
            } else if (isOperator(charI)) {
                if (stack.empty()) {
                    throw new IllegalArgumentException("");
                }
                int num2 = stack.pop();
                if (stack.empty()) {
                    throw new IllegalArgumentException("");
                }
                int num1 = stack.pop();
                stack.push(operate(num1, num2, String.valueOf(charI)));
                i++;

            } else {
                throw new IllegalArgumentException("evStrout(): invalid character broo");
            }


        }

        if (stack.empty()) {
            throw new IllegalArgumentException("empty result");
        }
        int result = stack.pop();
        if (!stack.empty()) {
            throw new IllegalArgumentException("empty result");
        }
        return result;
    }

    public static String convertClassicToStout(String exp) {
        if (exp == null || exp.isBlank()) {
            throw new IllegalArgumentException("ccToStout: expression is nul");
        }

        MyStack<Character> oppStack = new MyStack<Character>();
        StringBuilder stoutForm = new StringBuilder();

        boolean isNextCharANumber = true;
        int i = 0;

        while (i < exp.length()) {
            char charI = exp.charAt(i);

            if (Character.isWhitespace(charI)) {
                i++;

            } else if (Character.isDigit(charI) || (charI == '-' && isNextCharANumber
                    && i + 1 < exp.length() && Character.isDigit(exp.charAt(i + 1)))) {

                int numberStart = i;
                if (charI == '-') {
                    i++;
                }
                while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
                    i++;
                }

                stoutForm.append(exp.substring(numberStart, i)).append(" ");
                isNextCharANumber = false;
                continue;
            } else if (charI == '(') {
                oppStack.push(charI);
                i++;
                isNextCharANumber = true;

            } else if (charI == ')') {
                while (!oppStack.empty() && oppStack.peek() != '(') {
                    stoutForm.append(oppStack.pop()).append(" ");
                }
                if (oppStack.empty()) {
                    throw new IllegalArgumentException(
                            "convertClassicToStout(): Invalid character");
                }
                oppStack.pop();
                i++;
                isNextCharANumber = false;
            } else if (isOperator(charI)) {
                if (charI == '-' && (isNextCharANumber)) {
                    int j = i + 1;
                    while (j < exp.length() && Character.isWhitespace(exp.charAt(j))) {
                        j++;
                    }
                    if (j < exp.length() && exp.charAt(j) == '(') {
                        stoutForm.append("0 ");
                    }
                }

                while (!oppStack.empty() && oppStack.peek() != '('
                        && isOperatorAGreaterThanB(oppStack.peek(), charI)) {
                    stoutForm.append(oppStack.pop()).append(" ");
                }
                oppStack.push(charI);
                i++;
                isNextCharANumber = true;

            } else {
                throw new IllegalArgumentException("convertClassicToStout(): Invalid character");
            }
        }
        while (!oppStack.empty()) {
            char currentOpp = oppStack.pop();
            if (currentOpp == '(') {
                throw new IllegalArgumentException("() wrong");
            }
            stoutForm.append(currentOpp).append(" ");
        }

        return stoutForm.toString().trim();
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
