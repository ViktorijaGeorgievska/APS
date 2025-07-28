import java.util.Scanner;

/*
APS book basic
Input:                   Input:
5  1 2 3 1 2             5  1 2 3 2 1
Output:                  Output:
-1                       1
*/

public class Palindrom1 {
    public static void main(String[] args) {
        DLL<Integer> list = new DLL<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(in.nextInt());
        }
        in.close();

        DLLNode<Integer> first = list.getFirst();
        DLLNode<Integer> last = list.getLast();

        boolean found = false;
        while (first != last && last.succ != first) {
            if (first.element != last.element) {
                found = true;
            }
            first = first.succ;
            last = last.pred;
        }
        if (found) {
            System.out.println("-1");
        } else {
            System.out.println("1");
        }
    }
}
