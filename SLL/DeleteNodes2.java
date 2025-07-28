import java.util.Scanner;

/*
APS book
Input:                 Output:
9                      4->8->3->3->5->7
4 6 8 3 1 3 5 7 2
*/

public class DeleteNodes2 {
    public static void main(String[] args) {
        SLL<Integer> list = new SLL<>();

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(input.nextInt());
        }
        input.close();

        SLLNode<Integer> current = list.getFirst();
        int skipCount = 0;

        while (current != null) {
            for (int i = 0; i < skipCount; i++) {
                if (current == null)
                    break;
                current = current.succ;
            }

            if (current != null && current.succ != null) {
                list.delete(current.succ);
            }
            else {
                break;
            }
            skipCount++;
            current = current.succ;
        }
        if (list.getFirst() == null) {
            System.out.println("Prazna lista");
        } else {
            System.out.println(list);
        }
    }
}
