import java.util.Scanner;

/*
APS book
Input:
R+ G- G+ G+ R+ B- B+ R- G+ R- B- B+ B+ R+
Output:
5
R+ G+ G+ B+
*/

public class DeleteBalls2 {
    public static void deleteBalls(SLL<String> balls) {
        ArrayStack<String> redStack = new ArrayStack<>(50);
        ArrayStack<String> blueStack = new ArrayStack<>(50);
        ArrayStack<String> greenStack = new ArrayStack<>(50);

        SLLNode<String> current = balls.getFirst();
        int numPairs = 0;
        StringBuilder alone = new StringBuilder();

        while (current != null) {
            char color = current.element.charAt(0);
            char sign = current.element.charAt(1);

            if (color == 'R') {
                if (redStack.isEmpty())
                    redStack.push(color + "" + sign);
                else {
                    String inStack = redStack.peek();
                    if (inStack.equals(color + "" + sign))        // ако се исти, стави на стек
                        redStack.push(color + "" + sign);
                    else {                                        // ако се различни, извади од стек
                        redStack.pop();
                        numPairs++;
                    }
                }
            } else if (color == 'B') {
                if (blueStack.isEmpty())
                    blueStack.push(color + "" + sign);
                else {
                    String inStack = blueStack.peek();
                    if (inStack.equals(color + "" + sign))
                        blueStack.push(color + "" + sign);
                    else {
                        blueStack.pop();
                        numPairs++;
                    }
                }
            } else if (color == 'G') {
                if (greenStack.isEmpty())
                    greenStack.push(color + "" + sign);
                else {
                    String inStack = greenStack.peek();
                    if (inStack.equals(color + "" + sign))
                        greenStack.push(color + "" + sign);
                    else {
                        greenStack.pop();
                        numPairs++;
                    }
                }
            }
            current = current.succ;
        }
        if (!redStack.isEmpty())
            while (!redStack.isEmpty())
                alone.append(redStack.pop() + " ");
        if (!greenStack.isEmpty())
            while (!greenStack.isEmpty())
                alone.append(greenStack.pop() + " ");
        if (!blueStack.isEmpty())
            while (!blueStack.isEmpty())
                alone.append(blueStack.pop() + " ");

        System.out.println(numPairs);
        System.out.println(alone);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        SLL<String> balls = new SLL<>();
        String[] line = input.nextLine().split(" ");
        for (int i = 0; i < line.length; i++)
            balls.insertLast(line[i]);
        input.close();
        
        deleteBalls(balls);
    }
}


