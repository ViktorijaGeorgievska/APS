import java.util.Scanner;

/*
APS book
Input:
LM OZ OM OM LM SZ SM LZ OM LZ SZ SM SM LM
Output:
5
(OZ, OM); (SZ, SM); (LM, LZ); (SZ, SM); (LZ, LM);
*/

public class DancePartners2 {
    public static void formDancePartners(SLL<String> dancers) {
        SLLNode<String> current = dancers.getFirst();
        ArrayStack<String> basics = new ArrayStack<>(30);
        ArrayStack<String> standard = new ArrayStack<>(30);
        ArrayStack<String> latin = new ArrayStack<>(30);
        int numPairs = 0;
        StringBuilder output = new StringBuilder();

        while (current != null) {
            if (current.element.startsWith("O")) {
                if (!basics.isEmpty()) {
                    String onStack = basics.peek();
                    if ((onStack.endsWith("M") && current.element.endsWith("Z"))
                            || (onStack.endsWith("Z") && current.element.endsWith("M"))) {
                        output.append("(" + basics.pop() + ", " + current.element + "); ");
                        numPairs++;
                    }
                } else
                    basics.push(current.element);
            } else if (current.element.startsWith("S")) {
                if (!standard.isEmpty()) {
                    String onStack = standard.peek();
                    if ((onStack.endsWith("M") && current.element.endsWith("Z"))
                            || (onStack.endsWith("Z") && current.element.endsWith("M"))) {
                        output.append("(" + standard.pop() + ", " + current.element + "); ");
                        numPairs++;
                    }
                } else
                    standard.push(current.element);
            } else {
                if (!latin.isEmpty()) {
                    String onStack = latin.peek();
                    if ((onStack.endsWith("M") && current.element.endsWith("Z"))
                            || (onStack.endsWith("Z") && current.element.endsWith("M"))) {
                        output.append("(" + latin.pop() + ", " + current.element + "); ");
                        numPairs++;
                    }
                } else
                    latin.push(current.element);
            }
            current = current.succ;
        }
        System.out.println(numPairs);
        System.out.println(output);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] line = input.nextLine().split(" ");

        SLL<String> list = new SLL<>();
        for (String s : line)
            list.insertLast(s);

        formDancePartners(list);
    }
}
