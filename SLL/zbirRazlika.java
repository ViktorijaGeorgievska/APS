import java.util.Scanner;

/*
Input:                 Output:
8                      2->6->16->14
1 2 3 4 55 6 71 8
*/

public class zbirRazlika {
    public static void evaluate(SLL<Integer> list) {
        SLLNode<Integer> current = list.getFirst();

        while (current != null) {
            SLLNode<Integer> check = current.succ;

            while (check != null) {
                if (current.element % 2 == 0 && check.element % 2 == 0) {
                    current.element = current.element + check.element;
                    list.delete(check);
                    //check = null;
                    break;
                }
                else if (current.element % 2 != 0 && check.element % 2 != 0) {
                    current.element = Math.abs(current.element - check.element);
                    list.delete(check);
                    //check = null;
                    break;
                }
                check = check.succ;
            }
            current = current.succ;
        }
    }

    public static void main(String[] args) {
        SLL<Integer> list = new SLL<>();

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(input.nextInt());
        }
        input.close();

        evaluate(list);
        System.out.println(list);
    }
}
