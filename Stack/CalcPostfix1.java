import java.util.Scanner;

/*
APS book basic
Input: 5 9 + 2 * 6 5 * +
Output: Rezultatot e 58.0
*/

public class CalcPostfix1 {
    public static Double postfix(String expression) {
        LinkedStack<Double> stack = new LinkedStack<>();
        Double r = null;

        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);
            if (current == ' ')
                continue;
            else if (Character.isDigit(current)) {
                stack.push((double) current - '0');
            }
            else {
                if (stack.size() >= 2) {
                    Double lastNum = stack.pop();
                    Double beforeLastNum = stack.pop();
                    if (current == '+') {
                        stack.push(beforeLastNum + lastNum);
                    }
                    else if (current == '-') {
                        stack.push(beforeLastNum - lastNum);
                        break;
                    }
                    else if (current == '*') {
                        stack.push(beforeLastNum * lastNum);
                    }
                    else if (current == '/') {
                        stack.push(beforeLastNum / lastNum);
                    }
                    else {
                        System.out.println("Nevaliden vlez - nedostasuva operand na pozicija: " + i);
                    }
                }
            }
        }
        if (stack.size() != 1) {
            System.out.println("Nevaliden vlez - nedostasuva operator");
        }
        else {
            r = stack.pop();
        }
        return r;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();

        System.out.println("Rezultatot e: " + postfix(expression));
    }
}
