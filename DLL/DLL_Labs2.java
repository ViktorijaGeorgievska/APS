import java.util.Scanner;
// 9 i 10 Not finished

/*
Courses + Lab
Input:
5
1 2 3 4 5
3        first occurrence of M
2        move that node k times to the left / right
*/

public class DLL_Labs2 {
    // Output: 1<->2<->3<->4<->5 => 3<->1<->2<->4<->5
    public static void shiftMtoLeft(DLL<Integer> list, int M, int k) {
        DLLNode<Integer> current = list.getFirst();

        while (current != null && !current.element.equals(M))
            current = current.succ;

        if (current == null) {
            System.out.println("Elementot ne postoi vo listata");
            System.out.println(list);
            return;
        }

        DLLNode<Integer> target = current;
        for (int i = 0; i < k && target.pred != null; i++) {
            int value = target.element;
            DLLNode<Integer> predCurr = target.pred;

            list.delete(target);                                    // извади го од листата
            list.insertBefore(value, predCurr);                     // вметни го пред претходникот

            target = predCurr.pred;                                 // ажурирај ја новата референца
//            if (target == null)
//                target = list.getFirst();
        }
        System.out.println(list);
    }

    //public static void shiftMtoRight (DLL<Integer> list, int M, int k) {

    //}

    public static void main(String[] args) {
        DLL<Integer> list = new DLL<Integer>();

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++)
            list.insertLast(input.nextInt());
        int M = input.nextInt();
        int k = input.nextInt();
        input.close();

        System.out.println(list);
        shiftMtoLeft(list, M, k);
    }
}
