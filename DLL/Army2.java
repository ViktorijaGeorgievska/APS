import java.util.Scanner;

public class Army2 {
    public static void main(String[] args) {
        DLL<Integer> list = new DLL<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(in.nextInt());
        }
        int firstIntervalStart = in.nextInt();
        int firstIntervalEnd = in.nextInt();
        int secondIntervalStart = in.nextInt();
        int secondIntervalEnd = in.nextInt();
        in.close();

        DLLNode<Integer> firstStart = null, firstEnd = null;
        DLLNode<Integer> secondStart = null, secondEnd = null;

        DLLNode<Integer> current = list.getFirst();
        while (current != null) {
            if (current.element.equals(firstIntervalStart))
                firstStart = current;
            if (current.element.equals(firstIntervalEnd))
                firstEnd = current;
            if (current.element.equals(secondIntervalStart))
                secondStart = current;
            if (current.element.equals(secondIntervalEnd))
                secondEnd = current;
            current = current.succ;
        }

        // Ако интервалите се во обратен ред (second пред first), разменувај
        if (firstStart.pred != null && secondStart.pred != null &&
        secondStart.pred.element.equals(firstIntervalEnd)) {
            DLLNode<Integer> tempS = firstStart;
            DLLNode<Integer> tempE = firstEnd;
            firstStart = secondStart;
            firstEnd = secondEnd;
            secondStart = tempS;
            secondEnd = tempE;
        }
    }
}
