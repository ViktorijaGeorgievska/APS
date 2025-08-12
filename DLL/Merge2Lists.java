import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Vlezna
Input:
5 5
1 3 4 6 7
9 8 5 2 1
Output:
9<->8<->7<->6<->5<->4<->3<->2<->1<->1
1<->1<->2<->3<->4<->5<->6<->7<->8<->9
*/

public class Merge2Lists {
    public static void print(DLL<Integer> list) {
        DLLNode<Integer> current = list.getLast();
        while (current.pred != null) {
            System.out.print(current.element + "<->");
            current = current.pred;
        }
        System.out.println(current.element);
        System.out.println();
    }

    // спојува 2 листи (од кои првата е во растечки, втората во опаѓачки редослед) во 1 во опаѓачки редослед
    public static DLL<Integer> merge(DLL<Integer> firstList, DLL<Integer> secondList) {
        DLL<Integer> resultList = new DLL<>();
        DLLNode<Integer> first2 = secondList.getFirst();
        DLLNode<Integer> last1 = firstList.getLast();

        while (first2 != null && last1 != null) {
            if (first2.element >= last1.element) {
                resultList.insertLast(first2.element);
                first2 = first2.succ;
            } else {
                resultList.insertLast(last1.element);
                last1 = last1.pred;
            }
        }
        while (first2 != null) {
            resultList.insertLast(first2.element);
            first2 = first2.succ;
        }
        while (last1 != null) {
            resultList.insertLast(last1.element);
            last1 = last1.pred;
        }
        return resultList;
    }

    public static void main(String[] args) throws IOException {
        DLL<Integer> list1 = new DLL<>();
        DLL<Integer> list2 = new DLL<>();

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] line = input.readLine().split(" ");
        int m = Integer.parseInt(line[0]);
        int n = Integer.parseInt(line[1]);

        line = input.readLine().split(" ");
        for (int i = 0; i < m; i++)
            list1.insertLast(Integer.parseInt(line[i]));

        line = input.readLine().split(" ");
        for (int i = 0; i < n; i++)
            list2.insertLast(Integer.parseInt(line[i]));

        DLL<Integer> result = merge(list1, list2);
        System.out.println(result);
        print(result);
    }
}

