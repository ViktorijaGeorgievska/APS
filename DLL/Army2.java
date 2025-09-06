import java.util.Scanner;

/*
Not finished
APS book
Input:
10
1 2 3 4 5 6 7 8 9 10
1 5
6 10
Output:
6 7 8 9 10 1 2 3 4 5
*/

public class Army2 {
    public static void print(DLL<Integer> list) {
        DLLNode<Integer> current = list.getFirst();
        while (current != null) {
            System.out.print(current.element + " ");
            current = current.succ;
        }
    }

    public static void deleteSublist(DLL<Integer> list) {
        DLLNode<Integer> current = list.getFirst();

        print(list);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DLL<Integer> list = new DLL<>();
        int n = in.nextInt();
        for (int i = 0; i < n; i++)
            list.insertLast(in.nextInt());

        int startFirst = in.nextInt();
        int endFirst = in.nextInt();
        int startSecond = in.nextInt();
        int endSecond = in.nextInt();
        in.close();

        DLLNode<Integer> current = list.getFirst();
        DLLNode<Integer> firstStart = null, firstEnd = null;
        DLLNode<Integer> secondStart = null, secondEnd = null;

        while (current != null) {
            if (current.element.equals(startFirst))
                firstStart = current;
            if (current.element.equals(endFirst))
                firstEnd = current;
            if (current.element.equals(startSecond))
                secondStart = current;
            if (current.element.equals(endSecond))
                secondEnd = current;
            current = current.succ;
        }

        if (firstStart.pred != null && secondStart.pred != null &&
                secondStart.pred.element.equals(endSecond)) {
           DLLNode<Integer> tmpS = firstStart;
           DLLNode<Integer> tmpE = firstEnd;
           firstStart = secondStart;
           firstEnd = secondEnd;
           secondStart = tmpS;
           secondEnd = tmpE;
        }

        print(list);
    }
}
