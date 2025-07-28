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
    public static void join (SLL<Integer> list1, SLL<Integer> list2) {
        SLL<Integer> result = new SLL<>();
        SLLNode<Integer> current1 = list1.getFirst();
        SLLNode<Integer> current2 = list2.getFirst();

        while (current1 != null && current2 != null) {
            for (int i = 0; i < 2; i++) {
                if (current1 == null)
                    break;
                result.insertLast(current1.element);
                current1 = current1.succ;
            }
            for (int i = 0; i < 2; i++) {
                if (current2 == null)
                    break;
                result.insertLast(current2.element);
                current2 = current2.succ;
            }

            while (current1 != null)
            {
                result.insertLast(current1.element);
                current1 = current1.succ;
            }
            while (current2 != null)
            {
                result.insertLast(current2.element);
                current2 = current2.succ;
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        SLL<Integer> list1 = new SLL<>();
        SLL<Integer> list2 = new SLL<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            list1.insertLast(in.nextInt());
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            list2.insertLast(in.nextInt());
        }
        in.close();

        join(list1, list2);
    }
}
