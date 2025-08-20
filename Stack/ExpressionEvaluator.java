import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExpressionEvaluator {
    public static int evaluateExpression(String expression) {
        ArrayStack<Integer> numbersStack = new ArrayStack<>(expression.length());
        ArrayStack<Character> operatorsStack = new ArrayStack<>(expression.length());

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                int number = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    number = number * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                numbersStack.push(number);
                i--;
            } else if (c == '*' || c == '+') {
                while (!operatorsStack.isEmpty() && precedence(operatorsStack.peek()) >= precedence(c)) {
                    computeExpression(numbersStack, operatorsStack.pop());
                }
                operatorsStack.push(c);
            }
        }
        while (!operatorsStack.isEmpty())
            computeExpression(numbersStack, operatorsStack.pop());
        return numbersStack.pop();
    }

    private static int precedence(char operator) {
        return (operator == '*') ? 2 : 1;                  // operator == '*' => return 2; operator == '+' => return 1;
    }

    private static void computeExpression(ArrayStack<Integer> numStack, char operator) {
        int num1 = numStack.pop();
        int num2 = numStack.pop();
        if (operator == '*')
            numStack.push(num1 * num2);
        else if (operator == '+')
            numStack.push(num1 + num2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }
}
