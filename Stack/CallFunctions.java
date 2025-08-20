import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Prv kolokvium - dopolnitelni
Најди ја фунцкијата што повикува највеќе фунцкии во неа.
Секоја фунцкија има Call x - x е името на фунцкијата - и Return.

Input:
12
Call a
Call b
Call c
Return
Call d
Return
Call e
Return
Return
Call f
Return
Return

Output:
b 3
Објаснување: во b се повикани c d и e
*/

class Function {
    String name;
    int numCalls = 0;

    public Function(String name) {
        this.name = name;
    }
}

public class CallFunctions {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());
        ArrayStack<Function> functionsStack = new ArrayStack<>(n);
        SLL<Function> helper = new SLL<>();

        for (int i = 0; i < n; i++) {
            String[] line = input.readLine().split(" ");
            if (line[0].equals("Call")) {
                if (!functionsStack.isEmpty())
                    functionsStack.peek().numCalls++;
                functionsStack.push(new Function(line[1]));
            } else if (line[0].equals("Return")) {
                if (!functionsStack.isEmpty()) {
                    Function f = functionsStack.pop();
                    helper.insertLast(f);
                }
            }
        }
        while (!functionsStack.isEmpty())
            helper.insertLast(functionsStack.pop());

        input.close();

        SLLNode<Function> current = helper.getFirst();
        SLLNode<Function> maxNode = current;
        while (current != null) {
            if (current.element.numCalls >= maxNode.element.numCalls)
                maxNode = current;
            current = current.succ;
        }
        System.out.println(maxNode.element.name + " " + maxNode.element.numCalls);
    }
}

