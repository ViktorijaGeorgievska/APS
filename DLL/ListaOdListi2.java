import java.util.Scanner;

/*
APS book
Input:         Output:
3              4200
4
1 2 3 4
2 3 4 5
6 7 8 9
*/

public class ListaOdListi2 {
    public static long findMagicNumber(DLL<DLL<Integer>> list) {
        DLLNode<DLL<Integer>> current = list.getFirst();
        long product = 1;

        while (true) {
            int sum = 0;
            DLLNode<Integer> current1 = current.element.getFirst();

            while (true) {
                sum += current1.element;
                if (current1 == current.element.getLast())
                    break;
                current1 = current1.succ;
            }
            product *= sum;
            if (current == list.getLast())
                break;
            current = current.succ;
        }
        return product;
    }

    public static void main(String[] args) {
        DLL<DLL<Integer>> list = new DLL<>();

        Scanner in = new Scanner(System.in);
        int numLists = in.nextInt();
        int numElements = in.nextInt();

        for (int i = 0; i < numLists; i++) {
            DLL<Integer> tmp = new DLL<>();              // креира внатрешна листа tmp
            for (int j = 0; j < numElements; j++) {
                tmp.insertLast(in.nextInt());            // tmp се пополнува
            }
            list.insertLast(tmp);                        // tmp се додаваат во листата
        }
        in.close();
        System.out.println(findMagicNumber(list));
    }
}
