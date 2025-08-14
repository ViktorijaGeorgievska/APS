import java.util.Scanner;

/*
APS book
Input:
a+b*(c^d-e)^(f+g*h)-i
Output:
abcd^e-fgh*+^*+i-

Input:
(5+9)*2+6*5
Output:
59+2*65*+
*/

public class InfixToPostfix2 {
    public static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        ArrayStack<Character> operatorStack = new ArrayStack<>(50);

        for (int i = 0; i < infix.length(); i++) {
            char currentChar = infix.charAt(i);

            if (Character.isLetterOrDigit(currentChar))
                postfix.append(currentChar);                       // Ако е операнд, додади го на постфикс изразот
            else if (currentChar == '(')
                operatorStack.push(currentChar);                   // Ако е (, стави го на стекот на оператори
            else if (currentChar == ')') {
                // Премести оператори од стекот на оператори на постфикс се додека не наидеш на (
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(')
                    postfix.append(operatorStack.pop());
                operatorStack.pop();                               // Отстрани го ( од стекот на оператори
            } else {
                // Операторите имаат различни приоритети
                // => Премести оператори од стекот на оператори на постфикс доколку имаат поголем или ист приоритет
                while (!operatorStack.isEmpty() && precedence(currentChar) <= precedence(operatorStack.peek())) {
                    postfix.append(operatorStack.pop());
                }
                operatorStack.push(currentChar);
            }
        }
        // Премести ги сите преостанати оператори од стекот на оператори на постфикс
        while (!operatorStack.isEmpty())
            postfix.append(operatorStack.pop());

        return postfix.toString();
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String infixExpression = input.nextLine();
        input.close();

        String postfixExpression = infixToPostfix(infixExpression);
        System.out.println(postfixExpression);
    }
}





