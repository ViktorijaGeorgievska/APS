import java.util.Scanner;

/*
APS book
Input:          Output:
5               3 1 2
4 2 1 5 3       5 4
*/

public class AverageSplit2 {
    public static int average (DLL<Integer> list) {
        DLLNode<Integer> current = list.getFirst();
        int sum = 0;

        while (current != null) {
            sum += current.element;
            current = current.succ;
        }
        return sum / list.getSize();
    }

    public static void print (DLL<Integer> list) {
        DLLNode<Integer> current = list.getFirst();
        while (current != null) {
            System.out.print(current.element + " ");
            current = current.succ;
        }
    }

    public static void splitByAverage(DLL<Integer> list) {
        int average = average(list);
        DLLNode<Integer> current = list.getLast();
        DLL<Integer> lower = new DLL<>();
        DLL<Integer> upper = new DLL<>();

        while (current != null) {
            if (current.element <= average) {
                lower.insertLast(current.element);
            }
            else {
                upper.insertLast(current.element);
            }
            current = current.pred;
        }
        print(lower);
        System.out.println();
        print(upper);
    }

    public static void main(String[] args) {
        DLL<Integer> list = new DLL<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++)
            list.insertLast(in.nextInt());
        in.close();

        splitByAverage(list);
    }
}
