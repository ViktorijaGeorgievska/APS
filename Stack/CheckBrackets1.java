import java.util.Scanner;

/*
APS book basic
{s x [s - a] x (s - b) x (s - c)}
{} [s x (s - a) x s - b) x (s - c)
*/

public class CheckBrackets1 {
    public static boolean areCorrect(String expression) {
        ArrayStack<Character> bracketStack = new ArrayStack<>(50);

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '{' || c == '[' || c == '(')
                bracketStack.push(c);
            else if (c == '}' || c == ']' || c == ')') {
                if (bracketStack.isEmpty())
                    return false;
                else if (!checkSameBrackets(bracketStack.pop(), c))
                    return false;
            }
        }
        return bracketStack.isEmpty();            // ако е empty значи сите се во ред и ќе врати true
    }

    public static boolean checkSameBrackets(char leftBracket, char rightBracket) {
        if (leftBracket == '(' && rightBracket == ')')
            return true;
        else if (leftBracket == '{' && rightBracket == '}')
            return true;
        else if (leftBracket == '[' && rightBracket == ']')
            return true;
        return false;
    }

    public static boolean checkBrackets(String expression) {
        LinkedStack<Character> stack = new LinkedStack<>();

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(' || expression.charAt(i) == '{' || expression.charAt(i) == '[') {
                stack.push(expression.charAt(i));
            } else if (expression.charAt(i) == ')') {
                if (stack.isEmpty())
                    return false;
                if (stack.pop() != '(')
                    return false;
            } else if (expression.charAt(i) == ']') {
                if (stack.isEmpty())
                    return false;
                if (stack.pop() != '[')
                    return false;
            } else if (expression.charAt(i) == '}') {
                if (stack.isEmpty())
                    return false;
                if (stack.pop() != '{')
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String expression = input.nextLine();

        System.out.println(expression + " have " + (areCorrect(expression) ? "correct" : "not correct") + " brackets!");
    }
}

   
  

