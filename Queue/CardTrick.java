import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Courses
Input: 15 Output: 1
Input: 12 Output: 25
Input: 14 Output: 18
Input: 43 Output: 3
*/

public class CardTrick {
    public static int count(int N) {
        ArrayQueue<Integer> cards = new ArrayQueue<>(51);
        for (int i = 1; i <= 51; i++)
            cards.enqueue(i);
        ArrayStack<Integer> shuffleStack = new ArrayStack<>(7);
        int numShuffles = 0;

        while (cards.peek() != N) {
            for (int i = 1; i <= 7; i++)
                shuffleStack.push(cards.dequeue());
            while (!shuffleStack.isEmpty()) {
                int fromStack = shuffleStack.pop();
                int fromCards = cards.dequeue();
                cards.enqueue(fromStack);
                cards.enqueue(fromCards);
            }
            numShuffles++;
        }
        return numShuffles;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(count(Integer.parseInt(br.readLine())));
    }

}
