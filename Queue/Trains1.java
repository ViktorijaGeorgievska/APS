import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
APS book basic
Input:
6
3
0
7
0
1
2
Output: 1 2 3 7
*/
public class Trains1 {
    public static String preRaspredeleni(String[] lines) {
        LinkedStack<Integer> oldTrain = new LinkedStack<>();
        LinkedStack<Integer> newTrain = new LinkedStack<>();
        LinkedQueue<Integer> shina = new LinkedQueue<>();

        int top;
        String out = new String("");

        for (int i = 0; i < lines.length; i++) {
            oldTrain.push(Integer.parseInt(lines[i]));
        }

        while (true) {
            if (!oldTrain.isEmpty() && oldTrain.peek() != 0) {
                newTrain.push(oldTrain.pop());
            }
            else {
                while (!oldTrain.isEmpty() && oldTrain.peek() == 0) {
                    oldTrain.pop();                           // ги вади сите расипани (0)
                }
                if (oldTrain.isEmpty())
                    out += "Site vagoni se rasipani";
                else
                    newTrain.push(oldTrain.pop());            // зема прв функционален
            }
            while (!oldTrain.isEmpty()) {
                while (!oldTrain.isEmpty() && oldTrain.peek() == 0) {
                    oldTrain.pop();                           // чистиме дополнителни 0-ки
                }
                if (oldTrain.isEmpty()) {
                    break;
                }
                else {
                    top = oldTrain.pop();                    // земаме следен функционален
                    if (newTrain.peek() < top) {
                        shina.enqueue(newTrain.pop());       // врвот на novVoz е помал → го преместуваме во шина
                        newTrain.push(top);                  // новиот поголем оди на врв на novVoz
                    } else {
                        shina.enqueue(top);                  // помалиот оди во шината
                    }
                }
            }
            if (!shina.isEmpty()) {
                newTrain.push(shina.dequeue());              // прв вагон од шината оди во novVoz
            }
            while (!shina.isEmpty()) {
                top = shina.dequeue();
                if (newTrain.peek() < top) {
                    oldTrain.push(newTrain.pop());          // враќање на погрешно сортирани вагони
                    newTrain.push(top);                     // новиот поголем оди прв
                } else {
                    oldTrain.push(top);                     // враќање назад за понатамошна обработка
                }
            }
            if (oldTrain.isEmpty() && shina.isEmpty())
                break;
        }
        while (!newTrain.isEmpty()) {
            out += newTrain.pop() + " ";
        }
        return out;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        String[] lines = new String[n];

        for (int i = 0; i < n; i++) {
            lines[i] = input.readLine();
        }
        input.close();

        System.out.println(preRaspredeleni(lines));
    }
}
