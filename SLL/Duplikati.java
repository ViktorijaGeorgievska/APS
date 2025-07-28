import java.util.Scanner;

/* Vtoriot duplikat go brishe
Input:            Output:
6                 1->2->3->4
1 2 1 3 4 2
*/

public class Duplikati {
    // eden do drug broj samo!
    public static void deleteD(SLL<Integer> lista) {

        SLLNode<Integer> dvizi  = lista.getFirst();
        while(dvizi.succ != null) {
            if (dvizi.element == dvizi.succ.element) {
                lista.delete(dvizi.succ);
            }
            else {
                dvizi = dvizi.succ;
            }
        }
    }

    public static void deleteDuplicates (SLL<Integer> list) {
        SLLNode<Integer> current = list.getFirst();

        while (current != null) {
            SLLNode<Integer> helper = current.succ;

            while (helper != null) {
                if (current.element == helper.element) {
                    list.delete(helper);
                    helper = null;
                    break;
                }
                helper = helper.succ;
            }
            current = current.succ;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        SLL<Integer> list = new SLL<>();
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(input.nextInt());
        }
        input.close();

        deleteDuplicates(list);
        System.out.println(list);
    }
}
