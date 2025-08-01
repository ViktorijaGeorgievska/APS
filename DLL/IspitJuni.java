import java.util.Scanner;

/*
Input:
34
TYPE H
TYPE e
TYPE l
TYPE l
TYPE o
SHOW
TYPE o
TYPE o
TYPE o
SHOW
UNDO
UNDO
UNDO
TYPE _
TYPE t
TYPE h
TYPE e
TYPE e
UNDO
TYPE r
TYPE e
SHOW
TYPE !
SHOW
TYPE !
TYPE !
TYPE !
SHOW
TYPE 1
TYPE 1
SHOW
UNDO
UNDO
SHOW
Output:
Hello
Helloooo
Hello_there
Hello_there!
Hello_there!!!!
Hello_there!!!!11
Hello_there!!!!
*/

public class ExamJune {
    public static void print (DLL<Character> list) {
        DLLNode<Character> current = list.getFirst();
        while (current != null) {
            System.out.print(current.element);
            current = current.succ;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DLL<Character> list = new DLL<>();

        Scanner in = new Scanner(System.in);
        int numLines = in.nextInt();
        in.nextLine();
        for (int i = 0; i < numLines; i++) {
            String []parts = in.nextLine().split(" ");

            if (parts[0].equals("TYPE"))
                list.insertLast(parts[1].charAt(0));
            else if (parts[0].equals("UNDO"))
                list.deleteLast();
            else if (parts[0].equals("SHOW"))
                print(list);
        }
    }
}
