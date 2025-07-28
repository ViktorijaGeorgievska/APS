import java.util.Scanner;

/*
Input:           Output:
6                1->2->2->4->3->5
1 4 3 2 5 2
3
*/

public class PomaliOdx {
    public static void smallerThanX(SLL<Integer> list, int x) {
        SLLNode<Integer> current = list.getFirst();

        for (int i = 0; i < list.size(); i++) {
            if (current.element >= x) {
                list.insertLast(current.element);
                list.delete(current);
            }
            current = current.succ;
        }

//        vtoro resenie so flag
//        SLLNode<Integer> current = list.getFirst();
//        SLLNode<Integer> last = null;
//        boolean daliEdnasPremesti = false;
//
//        while (current != last) {         // koga ke dojde do prviot element shto e >= x (marker do kade da odi listata)
//            if (current.element >= x) {
//                list.insertLast(current.element);
//                list.delete(current);
//
//                if (daliEdnasPremesti == false) {
//                    daliEdnasPremesti = true;
//
//                    last = list.getFirst();
//                    while (last.succ != null) {
//                        last = last.succ;
//                    }
//                }
//            }
//            current = current.succ;
//        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        SLL<Integer> list = new SLL<>();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(in.nextInt());
        }
        int x = in.nextInt();
        in.close();

        smallerThanX(list, x);
        System.out.println(list);
    }
}
