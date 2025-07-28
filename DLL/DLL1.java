import java.util.Scanner;

public class DLL1 {
    public static void shiftRightK(DLL<Integer> list, int k) {
        DLLNode<Integer> last = list.getLast();

        for (int i = 0; i < k; i++) {
            list.insertFirst(last.element);
            list.delete(last);
            last = list.getLast();
        }
        System.out.println(list);
    }

    public static void main(String[] args) {
        DLL<Integer> list = new DLL<>();

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(input.nextInt());
        }
        int k = input.nextInt();
        input.close();

        System.out.println(list);
        shiftRightK(list, k);
    }
}
