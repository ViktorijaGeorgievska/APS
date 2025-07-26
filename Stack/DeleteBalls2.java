import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
APS book
Input: R+ G- G+ G+ R+ B- B+ R- G+ R- B- B+ B+ R+
Output:
4
R- B- G- G-
*/

public class DeleteBalls2 {
    public static String deleteBalls(SLL<String> list) {
        LinkedStack<String> redStack = new LinkedStack<>();
        LinkedStack<String> blueStack = new LinkedStack<>();
        LinkedStack<String> greenStack = new LinkedStack<>();

        int numPairs = 0;
        String result = "";
        SLLNode<String> current = list.getFirst();

        while (current != null) {
            String ball = current.element;
            char color = ball.charAt(0);
            char sign = ball.charAt(1);

            if (color == 'R') {
                if (!redStack.isEmpty()) {
                    if (redStack.peek().equals(ball)) {
                        redStack.push(ball);
                    }
                    else {
                        redStack.pop();
                    }
                }
                else {
                    redStack.push(ball);
                }
            }
            if (color == 'B') {
                if (!blueStack.isEmpty()) {
                    if (blueStack.peek().equals(ball)) {
                        blueStack.push(ball);
                    } else {
                        blueStack.pop();
                    }
                }
                else {
                    blueStack.push(ball);
                }
            }
            if (color == 'G') {
                if (!greenStack.isEmpty()) {
                    if (greenStack.peek().equals(ball)) {
                        greenStack.push(ball);
                    } else {
                        greenStack.pop();
                    }
                }
                else {
                    greenStack.push(ball);
                }
            }
            current = current.succ;
        }
        while (!redStack.isEmpty()) {
            numPairs++;
            if (redStack.pop().charAt(1) == '+') {
                result += "R- ";
            } else {
                result += "R+ ";
            }
        }
        while (!blueStack.isEmpty()) {
            numPairs++;
            if (blueStack.pop().charAt(1) == '+') {
                result += "B- ";
            } else {
                result += "B+ ";
            }
        }
        while (!greenStack.isEmpty()) {
            numPairs++;
            if (greenStack.pop().charAt(1) == '+') {
                result += "G- ";
            } else {
                result += "G+ ";
            }
        }
        System.out.println(numPairs);
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String[] line = input.readLine().split(" ");

        SLL<String> balls = new SLL<>();
        for (int i = 0; i < line.length; i++) {
            balls.insertLast(line[i]);
        }
        System.out.println(deleteBalls(balls));
    }
}
