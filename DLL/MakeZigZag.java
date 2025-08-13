import java.util.Scanner;

/*
12
==============
4
1 5 1 5
==============
5
1 12 2 15 10
==============
7
6 10 8 9 2 4 3
==============
4
5 1 5 1
==============
6
5 1 6 1 5 1
==============
6
5 1 5 1 6 1
==============
5
1 5 10 5 1
==============
5
5 10 7 5 7
==============
4
1 10 10 1
==============
1
5
==============
5
10 10 10 10 10
==============
50
1 5 1 1 5 5 1 1 5 5 1 1 5 5 1 5 1 5 1 1 5 1 5 5 1 5 5 1 1 5 5 1 1 1 5 5 5 1 5 5 1 5 5 1 5 1 5 1 5 5
==============
*/

public class MakeZigZag {
    // 2 тест примери не поминуваат
    public static void makeZigZag(DLL<Integer> list) {
        DLLNode<Integer> first = list.getFirst();
        DLLNode<Integer> second = first.succ;

        while (first != null && second != null) {
            if (first.element >= second.element) {
                DLLNode<Integer> nextSecond = second.succ;
                list.delete(second);
                second = nextSecond;
            } else {                      // first < second
                if (second.succ != null && second.element < second.succ.element) {
                    DLLNode<Integer> toDelete = second.succ;
                    list.delete(toDelete);
                    first = first.succ;
                } else {
                    first = first.succ.succ;
                }
                if (first != null)
                    second = first.succ;
            }
        }
    }

    public static void zigZagUsingFlag (DLL<Integer> list) {
        DLLNode<Integer> current = list.getFirst();
        boolean less = true;

        while (current != null && current.succ != null) {
            if (less) {                    // current < current.succ
                if (current.element >= current.succ.element)
                    list.delete(current.succ);
                else
                    current = current.succ;
            } else {                       // current > current.succ
                if (current.element < current.succ.element)
                    list.delete(current.succ);
                else
                    current = current.succ;
            }
            less = !less;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int testCase = input.nextInt();
        for (int t = 1; t <= testCase; t++) {
            System.out.println("Test case " + t);
            input.next();

            int n = input.nextInt();
            DLL<Integer> list = new DLL<>();
            for (int i = 0; i < n; i++)
                list.insertLast(input.nextInt());

            System.out.println(list);
            zigZagUsingFlag(list);
            System.out.println(list);
        }
        input.close();
    }
}