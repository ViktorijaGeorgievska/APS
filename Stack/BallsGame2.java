import java.util.Scanner;

/*
APS book
Input:
4
R
R
R
G
Output:
G
==============
Input:
3
B
B
B
Output:
B B B
*/

public class BallsGame2 {
    public static void game(ArrayStack<Character> firstBox) {
        ArrayStack<Character> secondBox = new ArrayStack<>(50);
        ArrayStack<Character> thirdBox = new ArrayStack<>(50);

        if (firstBox.pop() == 'R') {

        } else if (firstBox.pop() == 'G') {

        } else if (firstBox.pop() == 'B') {

        }

        if (firstBox.isEmpty() && secondBox.isEmpty()) {
            while (!thirdBox.isEmpty())
                System.out.println(thirdBox.pop());
        }
    }

    public static void main(String[] args) {
        ArrayStack<Character> firstBox = new ArrayStack<>(50);

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++)
            firstBox.push(input.next().charAt(0));
        input.close();

        game(firstBox);
    }
}
