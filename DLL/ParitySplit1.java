import java.util.Scanner;

/*
APS book basic
Input:                Output:
5                     2<->4
1 2 3 4 5             1<->5<->3
*/

public class ParitySplit1 {
    public static void split(DLL<Integer> list) {
        DLL<Integer> evenList = new DLL<>();
        DLL<Integer> oddList = new DLL<>();
        DLLNode<Integer> first = list.getFirst();
        DLLNode<Integer> last = list.getLast();

        while (first != last) {
            if (first.element % 2 == 0)
                evenList.insertLast(first.element);
            else
                oddList.insertLast(first.element);

            if (last.element % 2 == 0)
                evenList.insertLast(last.element);
            else
                oddList.insertLast(last.element);

            first = first.succ;
            last = last.pred;
        }
        // za first == last
        if (first.element % 2 == 0)
            evenList.insertLast(first.element);
        else
            oddList.insertLast(first.element);
        
        System.out.println(evenList);
        System.out.println(oddList);
    }
    
    public static void main(String[] args) {
        DLL<Integer> list = new DLL<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) 
            list.insertLast(in.nextInt());
        in.close();

        split(list);
    }
}
