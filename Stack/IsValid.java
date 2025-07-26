import java.util.Scanner;

public class IsValid {
    public static boolean isValid (SLL<String> functions) {
        SLLNode<String> current = functions.getFirst();
        LinkedStack<String> stack = new LinkedStack<>();

        while (current != null) {
            if (current.element.startsWith("func")) {
                stack.push(current.element);
            }
            else if (current.element.startsWith("end")) {
                if (!stack.isEmpty()) {
                    if (stack.peek().equals(current.element.substring(3))) {
                        stack.pop();
                    }
                }
                else {
                    return false;
                }
            }
            current = current.succ;
        }
        if (stack.isEmpty())
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        SLL<String> functions = new SLL<>();

        while (true) {
            String line = in.nextLine();
            if (line.equals("x"))
                break;
            functions.insertLast(line);
        }

        if (isValid(functions))
            System.out.println("Valid");
        else
            System.out.println("Invalid");
    }
}
