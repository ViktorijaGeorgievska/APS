import java.util.Scanner;

public class SLL3 {
    public static void shiftAtEnd(SLL<String> list, int l) {
        SLLNode<String> current = list.getFirst();

        while (current != null) {
            if (current.element.length() == l) {
                list.insertLast(current.element);
                list.delete(current);
                current = current.succ;
            }
            current = current.succ;
        }

        System.out.println(list);
    }

    public static void main(String[] args) {
        SLL<String> list = new SLL<>();

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(input.next());
        }
        int l = input.nextInt();
        input.close();

        System.out.println(list);
        shiftAtEnd(list, l);
    }
}
