import java.util.Scanner;

/*
APS book basic
Input:                 Output:
8                      4->8
1 3 2 4 5 7 6 8        3->7
*/

public class RazdeliLista1 {
    public static void splitList(SLL<Integer> list) {
        SLL<Integer> parni = new SLL<>();
        SLL<Integer> neParni = new SLL<>();

        SLLNode<Integer> current = list.getFirst();
        while (current != null) {
            while (current.succ != null && current.element % 2 != 0 && current.succ.element % 2 != 0) {    // се додека наредните 2 се непарни
                current = current.succ;
            }
            while (current.succ != null && current.element % 2 == 0 && current.succ.element % 2 == 0) {
                current = current.succ;
            }
            if (current.element % 2 != 0) {
                neParni.insertLast(current.element);
            }
            else {
                parni.insertLast(current.element);
            }
            current = current.succ;
        }
        System.out.println(parni);
        System.out.println(neParni);
    }

    public static void main(String[] args) {
        SLL<Integer> list = new SLL<>();

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(input.nextInt());
        }
        input.close();
        splitList(list);
    }
}

