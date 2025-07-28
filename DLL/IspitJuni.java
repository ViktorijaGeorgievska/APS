import java.util.Scanner;
/*
7
TYPE h
TYPE e
UNDO
TYPE l
SHOW
TYPE l
SHOW

hll
*/

public class IspitJuni {
    public static void main(String[] args) {
        DLL<String> list = new DLL<>();
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            if (input.next().equals("TYPE")) {
                list.insertLast(input.next());
            } else if (input.next().equals("UNDO")) {
                list.deleteLast();
            } else if (input.next().equals("SHOW")) {
                StringBuilder sb = new StringBuilder();
                DLLNode<String> temp = list.getFirst();
                while (temp != null) {
                    sb.append(temp);
                    temp = temp.succ;
                }
                System.out.println(sb);
            }
        }
    }
}
