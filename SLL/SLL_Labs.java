import java.util.Scanner;

/*
Courses + Lab
Input:
4
Apple
Banana
Pear
Watermelon
6
*/

public class SLL_Labs {
    // Output: Apple->Banana->Pear->Watermelon => Banana->Watermelon
    public static void deleteString(SLL<String> list, int length) {
        SLLNode<String> current = list.getFirst();

        while (current != null) {
            if (current.element.length() < length)
                list.delete(current);
            current = current.succ;
        }
        System.out.println(list);
    }

    // Output: Apple->Banana->Pear->Watermelon => Apple->Banana->Target->Pear->Watermelon
    public static void insertStringAfter(SLL<String> list, int length) {
        SLLNode<String> current = list.getFirst();

        while (current != null) {
            if (current.element.length() == length) {
                list.insertAfter("Target", current);
                current = current.succ;
            }
            current = current.succ;
        }
        System.out.println(list);
    }

    // Output: Apple->Banana->Pear->Watermelon => Apple->Banana->Pear->Outlier->Watermelon
    public static void insertStringBefore(SLL<String> list, int length) {
        SLLNode<String> current = list.getFirst();

        while (current != null) {
            if (current.element.length() > length)
                list.insertBefore("Outlier", current);
            current = current.succ;
        }
        System.out.println(list);
    }

    // Output: Apple->Banana->Pear->Watermelon => Apple->Pear->Watermelon->Banana
    public static void shiftAtEnd(SLL<String> list, int length) {
        SLLNode<String> current = list.getFirst();

        for (int i = 0; i < list.size(); i++) {
            if (current.element.length() == length) {
                list.insertLast(current.element);
                list.delete(current);
            }
            current = current.succ;
        }
        System.out.println(list);
    }

    // Output: Apple->Banana->Pear->Watermelon => Banana->Apple->Pear->Watermelon
    public static void shiftAtStart(SLL<String> list, int length) {
        SLLNode<String> current = list.getFirst();
        SLLNode<String> toAdd = null;

        while (current != null) {
            if (current.element.length() == length)
                toAdd = current;
            current = current.succ;
        }
        if (toAdd != null) {
            if (toAdd != list.getFirst()) {
                current = list.getFirst();
                while (current != toAdd)
                    current = current.succ;
                list.insertFirst(toAdd.element);
                list.delete(toAdd);
            }
        }
        System.out.println(list);
    }

    public static void main(String[] args) {
        SLL<String> list = new SLL<>();

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++)
            list.insertLast(input.next());
        int length = input.nextInt();
        input.close();

        System.out.println(list);
        // call one of the functions!
    }
}
