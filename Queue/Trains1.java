import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
APS book basic
Input:
10
3
0
0
7
0
1
2
0
0
10
Output:
1 2 3 7 10
*/

public class Trains1 {
    public static StringBuilder preRaspredeleni(String[] input) {
        ArrayStack<Integer> oldTrain = new ArrayStack<>(input.length);
        ArrayStack<Integer> newTrain = new ArrayStack<>(input.length);
        ArrayQueue<Integer> shina = new ArrayQueue<>(input.length);
        int top;
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < input.length; i++)
            oldTrain.push(Integer.parseInt(input[i]));

        while (true) {
            if (!oldTrain.isEmpty() && oldTrain.peek() != 0)             
                newTrain.push(oldTrain.pop());
            else {
                while (!oldTrain.isEmpty() && oldTrain.peek() == 0)
                    oldTrain.pop();
                if (oldTrain.isEmpty())
                    out.append("Site vagoni se rasipani!");
                else
                    newTrain.push(oldTrain.pop());
            }

            while (!oldTrain.isEmpty()) {
                while (!oldTrain.isEmpty() && oldTrain.peek() == 0)
                    oldTrain.pop();
                if (oldTrain.isEmpty())
                    break;
                else {
                    top = oldTrain.pop();
                    if (newTrain.peek() < top) {
                        shina.enqueue(newTrain.pop());
                        newTrain.push(top);
                    } else 
                        shina.enqueue(top);
                }
            }
            if (!shina.isEmpty())
                newTrain.push(shina.dequeue());
            while (!shina.isEmpty()) {
                top = shina.dequeue();
                if (newTrain.peek() < top) {
                    oldTrain.push(newTrain.pop());
                    newTrain.push(top);
                } else
                    oldTrain.push(top);
            }
            if (oldTrain.isEmpty() && shina.isEmpty())
                break;
        }
        while (!newTrain.isEmpty())
            out.append(newTrain.pop() + " ");

        return out;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());
        String[] line = new String[n];
        for (int i = 0; i < n; i++)
            line[i] = input.readLine();
        input.close();

        System.out.println(preRaspredeleni(line));
    }
}
