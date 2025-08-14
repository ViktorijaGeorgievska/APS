import java.util.Scanner;

/*
APS book basic
Input: 5 9 + 2 * 6 5 * +
Output: he result is: 58.0
*/

public class CalcPostfix1 {
    public static void calculate(String expression) {
        ArrayStack<Float> calcStack = new ArrayStack<>(50);

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == ' ')
                continue;
            else if (Character.isDigit(c))
                calcStack.push((float) c - '0');
            else {
                if (calcStack.size() >= 2) {
                    float num1 = calcStack.pop();
                    float num2 = calcStack.pop();

                    if (c == '+')
                        calcStack.push(num1 + num2);
                    else if (c == '-')
                        calcStack.push(num1 - num2);
                    else if (c == '*')
                        calcStack.push(num1 * num2);
                    else if (c == '/')
                        calcStack.push(num1 / num2);
                    else
                        System.out.println("The expression is not valid! Missing operand on position " + i + ".");
                }
            }
        }
        if (calcStack.size() != 1)
            System.out.println("The expression is not valid! Missing operator!");
        else
            System.out.println("The result is: " + calcStack.pop());
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String expression = input.nextLine();

        calculate(expression);
    }
}



