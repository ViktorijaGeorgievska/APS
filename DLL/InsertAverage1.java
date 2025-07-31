import java.util.Scanner;

/*
APS book basic
Input:           Output:
3  1 2 4         1<->2<->2<->3<->4
*/

public class InsertAverage1 {
    public static void insertAvg(DLL<Integer> list) {
        DLLNode<Integer> first = list.getFirst();

        while (first.succ != null) {
            DLLNode<Integer> next = first.succ;
            int avg = (Math.round((float) (first.element + next.element) / 2));

            list.insertBefore(avg, next);
            first = next;
        }
        System.out.println(list);
    }
    
    public static void main(String[] args) {
        DLL<Integer> list = new DLL<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) 
            list.insertLast(in.nextInt());
        in.close();
        
        insertAvg(list);
    }
}
