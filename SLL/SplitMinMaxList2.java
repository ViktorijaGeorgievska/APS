import java.util.Scanner;
import static java.lang.Math.abs;

/*
APS book
Input:                   Output:
9                        1->5->3->2->4
1 5 7 3 2 9 4 8 6        7->9->8->6
*/

public class SplitMinMaxList2 {
    public static void main(String[] args) {
        SLL<Integer> lista = new SLL<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            lista.insertLast(in.nextInt());
        }
        in.close();

        SLL<Integer> closerToMin = new SLL<>();
        SLL<Integer> closerToMax = new SLL<>();
        SLLNode<Integer> current = lista.getFirst();
        SLLNode<Integer> maxNode = current;
        SLLNode<Integer> minNode = current;

        current = current.succ;
        // наоѓаме min и max елемент
        while (current != null) {
            if (current.element > maxNode.element) {
                maxNode = current;
            }
            if (current.element < minNode.element) {
                minNode = current;
            }
            current = current.succ;
        }

        current = lista.getFirst();
        while (current != null) {
            if (abs(current.element - minNode.element) <= abs(current.element - maxNode.element)) {
                closerToMin.insertLast(current.element);
            }
            else {
                closerToMax.insertLast(current.element);
            }
            current = current.succ;
        }
        System.out.println(closerToMin);
        System.out.println(closerToMax);
    }
}
