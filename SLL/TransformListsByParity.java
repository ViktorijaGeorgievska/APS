import java.util.Scanner;

/*
Vlezna 2022
Input:
14
1 5 2 3 0 6 4 3 7 9 1 4 6 8
Output:
1 -> 5 -> 3 -> 3 -> 7 -> 9 -> 1
2 -> 0 -> 6 -> 4 -> 4 -> 6 -> 8
*/

public class TransformListsByParity {
    public static void listTransformation (SLL<Integer> list) {
        SLLNode<Integer> current = list.getFirst();
        SLLNode<Integer> oddHead = null, oddTail = null;
        SLLNode<Integer> evenHead = null, evenTail = null;

        while (current != null) {
            SLLNode<Integer> next = current.succ;            // во next го чуваме наредниот елемент
            current.succ = null;                             // а current.succ е null во секоја итерација,
                                                             // за кога ќе дојде крај од една од листите да се прекине и самата листа

            if (current.element % 2 != 0) {
                if (oddHead == null) {
                    oddHead = current;
                } else {
                    oddTail.succ = current;
                }
                oddTail = current;
            } else {
                if (evenHead == null) {
                    evenHead = current;
                } else {
                    evenTail.succ = current;
                }
                evenTail = current;
            }
            current = next;
        }
        
        while (oddHead != null) {
            System.out.print(oddHead.element);
            if (oddHead.succ != null)
                System.out.print(" -> ");
            oddHead = oddHead.succ;
        }
        System.out.println();
        while (evenHead != null) {
            System.out.print(evenHead.element);
            if (evenHead.succ != null)
                System.out.print(" -> ");
            evenHead = evenHead.succ;
        }
    }

    public static void main(String[] args) {
        SLL<Integer> list = new SLL<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(in.nextInt());
        }
        in.close();

        listTransformation(list);
    }
}
