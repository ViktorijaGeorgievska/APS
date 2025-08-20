import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Ispit juni
Input:
23
TYPE H
TYPE e
TYPE l
TYPE l
TYPE o
SHOW
TYPE o
TYPE o
TYPE o
SHOW
UNDO
UNDO
UNDO
TYPE _
TYPE t
TYPE h
TYPE e
TYPE e
UNDO
TYPE r
TYPE e
SHOW
UNDO

Output:
Hello
Helloooo
Hello_there
*/

public class TextEditor {
    public static void main(String[] args) throws IOException {
        LinkedStack<String> stack = new LinkedStack<>();
        LinkedStack<String> reverse = new LinkedStack<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            String line = in.readLine();

            if (line.startsWith("TYPE")) {
                String add = line.split(" ")[1];
                stack.push(add);
            } else if (line.startsWith("UNDO")) {
                if (!stack.isEmpty())
                    stack.pop();
            } else if (line.startsWith("SHOW")) {
                while (!stack.isEmpty())
                    reverse.push(stack.pop());

                if (!reverse.isEmpty()) {
                    while (!reverse.isEmpty()) {
                        sb.append(reverse.peek());
                        stack.push(reverse.pop());
                    }
                }
                if (reverse.isEmpty()) {
                    System.out.println(sb);
                    sb = new StringBuilder();
                }
            }
        }
    }
}
