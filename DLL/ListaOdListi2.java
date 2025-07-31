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
        DLLNode<DLL<Integer>> outCurrent = list.getFirst();
        long product = 1;

        while (outCurrent != null) {
            int sum = 0;
            DLLNode<Integer> inCurrent = outCurrent.element.getFirst();

            while (inCurrent != null) {
                sum += inCurrent.element;
                inCurrent = inCurrent.succ;
            }

            product *= sum;
            outCurrent = outCurrent.succ;
        }
        return product;
    }

    public static void main(String[] args) {
        DLL<DLL<Integer>> outsideList = new DLL<>();            // креира надворешна листа outsideList

        Scanner in = new Scanner(System.in);
        int numLists = in.nextInt();
        int numElements = in.nextInt();

        for (int i = 0; i < numLists; i++) {
            DLL<Integer> insideList = new DLL<>();              // креира внатрешна листа insideList
            for (int j = 0; j < numElements; j++) {
                insideList.insertLast(in.nextInt());            // insideList се пополнува
            }
            outsideList.insertLast(insideList);                 // insideList се додаваат во листата outsideList
        }
        in.close();
        System.out.println(findMagicNumber(outsideList));
    }
}
