import java.util.Scanner;

public class DLL2 {
    public static void shift(DLL<Integer> list, int m, int shiftK) {
        DLLNode<Integer> first = list.getFirst();
        DLLNode<Integer> find = null;

        while (first != null) {
            if (first.element == m) {
                find = first;
            }
            first = first.succ;
        }
        for (int i = 0; i < shiftK; i++) {

        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DLL<Integer> list = new DLL<>();

        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(input.nextInt());
        }
        int m = input.nextInt();
        int k = input.nextInt();
        input.close();

        System.out.println(list);
        shift(list, m, k);
    }
}
