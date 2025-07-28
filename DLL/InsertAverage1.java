import java.util.Scanner;

/*
APS book basic
Input:           Output:
3  1 2 4         1<->2<->2<->3<->4
*/

public class InsertAverage1 {
    public static void main(String[] args) {
        DLL<Integer> list = new DLL<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(in.nextInt());
        }
        in.close();

        DLLNode<Integer> first = list.getFirst();
        DLLNode<Integer> second = first.succ;

        float avg;
        while (first != null && second != null) {
            avg = Math.round((float) (first.element + second.element) / 2);
            list.insertBefore((int) avg, second);
            first = first.succ;
            second = second.succ;
        }
        System.out.println(list);
    }
}
