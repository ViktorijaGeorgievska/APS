import java.util.Scanner;

public class DeleteSublists2 {
    public static void main(String[] args) {
        DLL<Integer> list = new DLL<>();
        DLL<Integer> sublist = new DLL<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(in.nextInt());
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            sublist.insertLast(in.nextInt());
        }
        in.close();

        DLLNode<Integer> current = list.getFirst();

        while (current != null) {
            DLLNode<Integer> tempMain = current;
            DLLNode<Integer> tempSublist = sublist.getFirst();

            // помини ги двете листи се додека се исти
            while (tempSublist != null && tempMain != null && tempSublist.element.equals(tempMain.element)) {
                tempSublist = tempSublist.succ;
                tempMain = tempMain.succ;
            }

            // доколоку е исполнето, значи дека целата е измината и се поклопуваат
            if (tempSublist == null) {
                // колку што е голема tempSublist, толку треба да се избришат
                for (int i = 0; i < m ; i++) {
                    DLLNode<Integer> toDelete = current;
                    current = current.succ;
                    list.delete(toDelete);
                }
            }
            else {
                current = current.succ;
            }
        }
        if (list.getFirst() != null) {
            System.out.println(list);
        } else {
            System.out.println("Prazna lista");
        }
    }
}
