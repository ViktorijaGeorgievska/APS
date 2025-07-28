import java.util.Scanner;

/*
APS book
Input:                 Output:
9                      4->8->3->3->5->7
4 6 8 3 1 3 5 7 2
*/

public class DeleteNodes2 {
     public static void delete (SLL<Integer> list) {
        SLLNode<Integer> current = list.getFirst();
        int skipCounter = 0;

        while (current != null) {
            for (int i = 0; i < skipCounter; i++) {
                if (current == null)
                    break;
                current = current.succ;
            }

            if (current != null && current.succ != null)
                list.delete(current.succ);
            else
                break;
            skipCounter++;
            current = current.succ;
        }
        System.out.println(list);
    }

    public static void main(String[] args) {
        SLL<Integer> list = new SLL<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(in.nextInt());
        }
        in.close();

        delete(list);
    }
}
