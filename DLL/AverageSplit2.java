import java.util.Scanner;

/*
APS book
Input:          Output:
5               3 1 2
4 2 1 5 3       5 4
*/

public class AverageSplit2 {
    public static void main(String[] args) {
        DLL<Integer> list = new DLL<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(in.nextInt());
        }
        in.close();

        DLL<Integer> lowerThanAverage = new DLL<>();
        DLL<Integer> graterThanAverage = new DLL<>();

        DLLNode<Integer> current = list.getFirst();
        int sum = 0;
        while (current != null) {
            sum += current.element;
            current = current.succ;
        }
        int average = sum / n;

        current = list.getLast();
        while (current != null) {
            if (current.element <= average) {
                lowerThanAverage.insertLast(current.element);
            } else {
                graterThanAverage.insertLast(current.element);
            }
            current = current.pred;
        }

        current = lowerThanAverage.getFirst();
        while (current != null) {
            System.out.print(current.element + " ");
            current = current.succ;
        }
        System.out.println();

        current = graterThanAverage.getFirst();
        while (current != null) {
            System.out.print(current.element + " ");
            current = current.succ;
        }
        System.out.println();
    }
}
