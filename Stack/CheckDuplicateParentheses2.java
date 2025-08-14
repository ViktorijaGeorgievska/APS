import java.util.Scanner;

/*
APS book
Input:
(((a+(b)))+(c+d))
Output:
Duplicate parentheses found!

Input:
((a)+(b))
Output:
/
*/

public class CheckDuplicateParentheses2 {
    public static boolean checkParentheses(String expression) {
        ArrayStack<Character> stack = new ArrayStack<>(50);

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (Character.isLetterOrDigit(currentChar) || currentChar == '(')
                stack.push(currentChar);
            else {                                     // currentChar = )
                if (stack.peek() == '(')               // ако веднаш имаме ( => дупли
                    return true;
                else {
                    while (!stack.isEmpty() && stack.peek() != '(')
                        stack.pop();
                    if (!stack.isEmpty())
                        stack.pop();
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String expression = input.nextLine();
        input.close();

        if (checkParentheses(expression))
            System.out.println("Duplicate parentheses found!");
        else
            System.out.println("/");
    }
}




