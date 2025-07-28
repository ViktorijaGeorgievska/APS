import java.util.Scanner;

/*
APS book basic
Input:                Output:
5                     2<->4
1 2 3 4 5             1<->5<->3
*/

public class ParitySplit1 {
    public static void main(String[] args) {
        DLL<Integer> list = new DLL<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(in.nextInt());
        }
        in.close();

        DLL<Integer> parna = new DLL<>();
        DLL<Integer> neParna = new DLL<>();
        DLLNode<Integer> first = list.getFirst();
        DLLNode<Integer> last = list.getLast();

        while (first != last && last.succ != first) {
            if (first.element % 2 == 0) {
                parna.insertLast(first.element);
            } else {
                neParna.insertLast(first.element);
            }
            first = first.succ;
            if (last.element % 2 == 0) {
                parna.insertLast(last.element);
            } else {
                neParna.insertLast(last.element);
            }
            last = last.pred;
        }
        if (first == last) {
            if (first.element % 2 == 0) {
                parna.insertLast(first.element);
            }
            else {
                neParna.insertLast(first.element);
            }
        }
        System.out.println(parna);
        System.out.println(neParna);
    }
}
