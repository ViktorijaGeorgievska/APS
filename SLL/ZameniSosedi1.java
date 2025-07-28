import java.util.Scanner;

/*
APS book basic
Input:         Output:
5              6->4->9->4->3
4 6 4 9 3
*/

public class ZameniSosedi1 {
    public static void main(String[] args) {
        SLL<Integer> list = new SLL<>();

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(input.nextInt());
        }
        input.close();

        SLLNode<Integer> current = list.getFirst();
        while (current != null && current.succ != null) {
            Integer tmp = current.element;                        // чувај вредност на current
            current.element = current.succ.element;               // вредноста на current нека биде вредноста на наредниот
            current.succ.element = tmp;                           // вредноста на наредниот нека биде вредноста на current

            current = current.succ.succ;
        }
        System.out.println(list);
    }
}
