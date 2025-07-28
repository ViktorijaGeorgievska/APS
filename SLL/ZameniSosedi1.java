import java.util.Scanner;

/*
APS book basic
Input:         Output:
5              6->4->9->4->3
4 6 4 9 3
*/

public class SwapPairs1 {
    public static void transformList(SLL<Integer> list) {
        SLLNode<Integer> first = list.getFirst();

        while (first != null && first.succ != null) {
            SLLNode<Integer> second = first.succ;
 
            Integer helper = first.element;             // чувај вредност на first
            first.element = second.element;             // вредноста на first нека биде вредноста на наредниот (second)
            second.element = helper;                    // вредноста на наредниот (second) нека биде вредноста на first
            
            first = first.succ.succ;
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
        input.close();
        
        transformList(list);
    }
}


