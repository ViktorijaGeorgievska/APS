import java.util.Scanner;

public class SLL2 {
    public static void deleteNode(SLL<String> list, int l) {
        SLLNode<String> first = list.getFirst();
        while (first != null) {
            if (first.element.length() < l) {
                list.delete(first);
            }
            first = first.succ;
        }
        System.out.println(list);
    }

    public static void insertTarget(SLL<String> list, int l) {
        SLLNode<String> first = list.getFirst();

        while (first != null) {
            if (first.element.length() == l) {
                list.insertAfter("Target", first);
            }
            first = first.succ;
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
        deleteNode(list, l);

        insertTarget(list, l);
    }
}
