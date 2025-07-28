import java.util.Scanner;

/*
APS book
Input:              Output:
3                   5->7->1->1->9->4->5->6->8->9->4
5 7 9
8
1 1 4 5 6 8 9 4
*/

public class JoinNazimenicno2 {
    public static void main(String[] args) {
        SLL<Integer> firstList = new SLL<>();
        SLL<Integer> secondList = new SLL<>();
        SLL<Integer> result = new SLL<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            firstList.insertLast(in.nextInt());
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            secondList.insertLast(in.nextInt());
        }
        in.close();

        SLLNode<Integer> current1 = firstList.getFirst();
        SLLNode<Integer> current2 = secondList.getFirst();

        while (current1.succ != null && current2.succ != null) {
            for (int i = 0; i < 2; i++) {
                result.insertLast(current1.element);
                current1 = current1.succ;
            }
            for (int i = 0; i < 2; i++) {
                result.insertLast(current2.element);
                current2 = current2.succ;
            }
        }
        if (current1 != null) {
            while (current1 != null) {
                result.insertLast(current1.element);
                current1 = current1.succ;
            }
        }
        if (current2 != null) {
            while (current2 != null) {
                result.insertLast(current2.element);
                current2 = current2.succ;
            }
        }
        System.out.println(result);
    }
}
