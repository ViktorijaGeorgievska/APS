import java.util.Scanner;

/*
APS book
Input:
22
1 2 3 4 5 6 1 2 3 4 5 6 1 2 6 5 1 3 4 1 5 2
3
4 5 6
Output:
1 2 3 1 2 3 1 2 6 5 1 3 4 1 5 2
*/

public class DeleteSublists2 {
    public static void print(DLL<Integer> list) {
        DLLNode<Integer> current = list.getFirst();
        while (current != null) {
            System.out.print(current.element + " ");
            current = current.succ;
        }
    }

    public static void deleteSublist(DLL<Integer> list, DLL<Integer> subList) {
        DLLNode<Integer> currentList = list.getFirst();

        while (currentList != null) {
            DLLNode<Integer> currentSubList = subList.getFirst();
            DLLNode<Integer> tmpList = currentList;

            while (tmpList != null && currentSubList != null && tmpList.element.equals(currentSubList.element)) {
                tmpList = tmpList.succ;
                currentSubList = currentSubList.succ;
            }

            if (currentSubList == null) {
                for (int i = 0; i < subList.getSize(); i++) {
                    DLLNode<Integer> toDelete = currentList;
                    currentList = currentList.succ;
                    list.delete(toDelete);
                }
            } else {
                currentList = currentList.succ;
            }
        }

        if (list.getSize() != 0)
            print(list);
        else
            System.out.println("Empty list!");
    }

    public static void main(String[] args) {
        DLL<Integer> list = new DLL<>();
        DLL<Integer> subList = new DLL<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++)
            list.insertLast(in.nextInt());

        int m = in.nextInt();
        for (int i = 0; i < m; i++)
            subList.insertLast(in.nextInt());
        in.close();

        deleteSublist(list, subList);
    }
}
