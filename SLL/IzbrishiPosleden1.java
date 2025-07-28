/*
APS book basic
Input:         Output:
5              4->6->9->3
4 6 4 9 3
4
*/

import java.util.Scanner;

public class IzbrishiPosleden1 {
    public static void deleteLast (SLL<Integer> list, int deleteNum) {
        SLLNode<Integer> current = list.getFirst();
        SLLNode<Integer> toDelete = null;

        while (current != null) {
            if (current.element == deleteNum) {
                toDelete = current;                      // ако најдеш зачувај го во друг јазол
            }
            current = current.succ;
        }
        if (toDelete != null) {
            list.delete(toDelete);                      // избриши го јазолот => ќе го избрише поселдно најдениот
        }
        System.out.println(list);
    }

    public static void main(String[] args) {
        SLL<Integer> list = new SLL<>();

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(input.nextInt());
        }

        int deleteNum = input.nextInt();
        input.close();

        deleteLast(list, deleteNum);
    }
}
