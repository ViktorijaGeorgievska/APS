import java.util.Scanner;

/*
Courses + Lab
Input:
5
1 2 3 4 5
2
*/

public class DLL_Labs {
    // Output: 1<->2<->3<->4<->5 => 4<->5<->1<->2<->3
    public static void shiftKToRight(DLL<Integer> list, int k) {
        DLLNode<Integer> last = list.getLast();

        for (int i = 0; i < k; i++) {
            list.insertFirst(last.element);
            list.deleteLast();
            last = list.getLast();
        }
        System.out.println(list);
    }

    // Output: 1<->2<->3<->4<->5 => 3<->4<->5<->1<->2
    public static void shiftKToLeft(DLL<Integer> list, int k) {
        DLLNode<Integer> first = list.getFirst();

        for (int i = 0; i < k; i++) {
            list.insertLast(first.element);
            list.deleteFirst();
            first = list.getFirst();
        }
        System.out.println(list);
    }

    // Output: 1<->2<->3<->4<->5 => 4<->5<->1<->2<->3
    public static void replaceLastKToBeginning(DLL<Integer> list, int k) {
        DLLNode<Integer> last = list.getLast();

        for (int i = 0; i < k; i++) {
            list.insertFirst(last.element);
            list.deleteLast();
            last = last.pred;
        }
        System.out.println(list);
    }

    // Output: 1<->2<->3<->4<->5 => 3<->4<->5<->1<->2
    public static void replaceFirstKToEnd(DLL<Integer> list, int k) {
        DLLNode<Integer> first = list.getFirst();

        for (int i = 0; i < k; i++) {
            list.insertLast(first.element);
            list.deleteFirst();
            first = first.succ;
        }
        System.out.println(list);
    }

    public static void main(String[] args) {
        DLL<Integer> list = new DLL<>();

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++)
            list.insertLast(input.nextInt());
        int k = input.nextInt();
        input.close();

        System.out.println(list);
        // call one of the functions!
    }
}
