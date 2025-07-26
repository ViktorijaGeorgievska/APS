import java.util.Scanner;
/*
Input
4
Ivan 20.7.1976
Ivan 16.7.1988
Ana 18.7.1966
Ivan 5.6.1988
7
Output
Ivan Ana
*/

// APS book
public class BirthdaysLessDifficult {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        CBHT<Integer, String> hashTable = new CBHT<>(2 * N - 1);

        for (int i = 0; i < N; i++) {
            String name = in.next();
            String date = in.next();
            int month = Integer.parseInt(date.split("\\.")[1]);

            hashTable.insert(month, name);
        }
        int month = in.nextInt();
        SLLNode<MapEntry<Integer, String>> curr = hashTable.search(month);
        if (curr != null) {
            while (curr != null) {
                System.out.print(curr.element.value + " ");
                curr = curr.succ;
            }
        }
    }
}
