import java.util.Scanner;

/*
Input:                 Output:
5  1 2 3 4 5           4 2 5 3 1
*/

public class FlipList2 {
    // решение од книгата, по оптимално (со промена на врските на јазлите)
    public static void flipList (DLL<Integer> list) {
        DLL<Integer> helper = new DLL<>();
        DLLNode<Integer> help = list.getLast();

        while (help != null) {
            if (help.element % 2 == 0) {
                helper.insertLast(help.element);

                if (help == list.getFirst()) {
                    list.deleteFirst();
                } else if (help == helper.getLast()) {
                    list.deleteLast();
                } else {
                    (help.pred).succ = help.succ;
                    (help.succ).pred = help.pred;
                }
            }
            help = help.pred;
        }

        help = list.getLast();
        while (help != null) {
            helper.insertLast(help.element);          // додавање на непарните броеви
            help = help.pred;
        }
        System.out.println(helper);
    }

    // мое решение
    public static void flip (DLL<Integer> list) {
        DLL<Integer> resultList = new DLL<>();
        DLLNode<Integer> last = list.getLast();

        while (last != null) {
            if (last.element % 2 == 0) {
                resultList.insertLast(last.element);
                list.delete(last);
            }
            last = last.pred;
        }

        last = list.getLast();
        while (last != null) {
            resultList.insertLast(last.element);
            last = last.pred;
        }
        System.out.println(resultList);
    }

    public static void main(String[] args) {
        DLL<Integer> list = new DLL<>();
        
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) 
            list.insertLast(in.nextInt());
        in.close();

        flip(list);
    }
}
