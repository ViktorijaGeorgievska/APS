import java.util.Scanner;
/*
APS book basic
{s x [s - a] x (s - b) x (s - c)}
{} [s x (s - a) x s - b) x (s - c)
*/
public class CheckBrackets1 {
    public static boolean daliKorektni (String expression) {
        ArrayStack<Character> bracketStack = new ArrayStack<>(100);

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);
            if (currentChar == '(' || currentChar == '{' || currentChar == '[') {
                bracketStack.push(currentChar);
            }
            else if (currentChar == ')' || currentChar == '}' || currentChar == ']') {
                if (bracketStack.isEmpty())
                    return false;

                char leftBracket = bracketStack.pop();
                if (!daliSoodvetni(leftBracket, currentChar))
                    return false;
            }
        }
        return bracketStack.isEmpty();      // ако е empty ќе врати true
    }

    public static boolean daliSoodvetni(char leftBracket, char rightBracket) {
        switch (leftBracket) {
            case '(':
                return (rightBracket == ')');
            case '[':
                return (rightBracket == ']');
            case '{':
                return (rightBracket == '}');
        }
        return false;
    }

    public static boolean checkBrackets (String expression) {
        LinkedStack<Character> stack = new LinkedStack<>();

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(' || expression.charAt(i) == '{' || expression.charAt(i) == '[') {
                stack.push(expression.charAt(i));
            }
            else if (expression.charAt(i) == ')') {
                if (stack.isEmpty())
                    return false;
                if (stack.pop() != '(')
                    return false;
            }
            else if (expression.charAt(i) == ']') {
                if (stack.isEmpty())
                    return false;
                if (stack.pop() != '[')
                    return false;
            }
            else if (expression.charAt(i) == '}') {
                if (stack.isEmpty())
                    return false;
                if (stack.pop() != '{')
                    return false;
            }
        }
        if (stack.isEmpty())
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();

        System.out.println(expression + " ima " + (daliKorektni(expression) ? "korektni" : "nekorektni") + " zagradi.");
    }
}
