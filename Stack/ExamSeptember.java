import java.util.Scanner;

/*
Dopolnitelni prva zadaca
Input:
ABACDCBD
Output:
ACBD

Input:
AC
Output:
AC

Input:
ABDC
Output:
 (empty)
*/

public class ExamSeptember {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        LinkedStack<Character> stack = new LinkedStack<>();
        String line = input.nextLine();
        char[] array = line.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (!stack.isEmpty()) {
                if (array[i] == 'A' && stack.peek() == 'B')
                    stack.pop();
                else if (array[i] == 'B' && stack.peek() == 'A')
                    stack.pop();
                else if (array[i] == 'C' && stack.peek() == 'D')
                    stack.pop();
                else if (array[i] == 'D' && stack.peek() == 'C')
                    stack.pop();
                else
                    stack.push(array[i]);
            } else
                stack.push(array[i]);
        }
        LinkedStack<Character> helper = new LinkedStack<>();
        if (!stack.isEmpty()) {
            while (!stack.isEmpty())
                helper.push(stack.pop());
            while (!helper.isEmpty())
                System.out.print(helper.pop());
        } else
            System.out.println(" ");
    }
}
