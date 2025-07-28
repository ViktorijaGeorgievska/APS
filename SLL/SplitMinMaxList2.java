import java.util.Scanner;

/*
APS book
Input:                   Output:
9                        1->5->3->2->4
1 5 7 3 2 9 4 8 6        7->9->8->6
*/

public class SplitMinMaxList2 {
    public static void split (SLL<Integer> list) {
        SLL<Integer> closerToMax = new SLL<>();
        SLL<Integer> closerToMin = new SLL<>();

        SLLNode<Integer> current = list.getFirst();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        while (current != null) {
            if (current.element > max)
                max = current.element;
            if (current.element < min)
                min = current.element;

            current = current.succ;
        }

        current = list.getFirst();
        while (current != null) {
            if (Math.abs(current.element - max) < Math.abs(current.element - min))
                closerToMax.insertLast(current.element);
            else
                closerToMin.insertLast(current.element);
            current = current.succ;
        }

        System.out.println(closerToMin);
        System.out.println(closerToMax);
    }

    public static void main(String[] args) {
        SLL<Integer> lista = new SLL<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            lista.insertLast(in.nextInt());
        }
        in.close();
        split(lista);
    }
}
