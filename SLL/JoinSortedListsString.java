import java.util.Scanner;

/*
3
Ana Bojana Dejan
3
Andrijana Biba Darko

Ana->Andrijana->Biba->Bojana->Darko->Dejan
*/

public class JoinSortedListsString  {
    public static SLL<String> join (SLL<String> list1, SLL<String> list2) {
        SLL<String> result = new SLL<>();
        SLLNode<String> current1 = list1.getFirst();
        SLLNode<String> current2 = list2.getFirst();

        while (current1 != null && current2 != null) {
            if (current1.element.compareTo(current2.element) < 0) {
                result.insertLast(current1.element);
                current1 = current1.succ;
            }
            else {
                result.insertLast(current2.element);
                current2 = current2.succ;
            }
        }

        if (current1 != null) {
            while (current1 != null) {
                result.insertLast(current1.element);
                current1 = current1.succ;
            }
        }

        if (current2 != null) {
            while (current2 != null) {
                result.insertLast(current2.element);
                current2 = current2.succ;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SLL<String> l1names = new SLL<>();
        SLL<String> l2names = new SLL<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            l1names.insertLast(in.next());
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            l2names.insertLast(in.next());
        }
        in.close();

        SLL<String> sorted = join(l1names, l2names);
        System.out.println(sorted);
    }
}
