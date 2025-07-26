import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
APS book
Input: H H O H H O H H O H H H H H O H O H O O H O O H H H
Output: 8  H  0
*/

public class Molecules2 {
    public static String makeWater(SLL<String> list) {
        ArrayStack<String> hStack = new ArrayStack<>(50);
        ArrayStack<String> oStack = new ArrayStack<>(50);

        int num = 0;
        String result = "";
        SLLNode<String> current = list.getFirst();

        while (current != null) {
            String element = current.element;
            if (element.equals("O")) {
                oStack.push(element);
            } else {
                hStack.push(element);
            }
            current = current.succ;
        }

        while (!oStack.isEmpty()) {
            if (!hStack.isEmpty()) {
                hStack.pop();
                if (!hStack.isEmpty()) {
                    hStack.pop();
                    oStack.pop();
                    num++;
                } else {
                    hStack.push("H");
                    break;
                }
            } else {
                break;
            }
        }
        while (!hStack.isEmpty()) {
            hStack.pop();
            result += "H\n";
        }
        while (!oStack.isEmpty()) {
            oStack.pop();
            result += "O\n";
        }
        System.out.println(num);
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        SLL<String> list = new SLL<>();
        for (int i = 0; i < input.length; i++) {
            list.insertLast(input[i]);
        }
        in.close();
        System.out.println(makeWater(list));
    }
}
