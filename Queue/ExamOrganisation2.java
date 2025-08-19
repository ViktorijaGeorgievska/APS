import java.util.Scanner;

/*
APS book
*/

public class ExamOrganisation2 {
    public static void main(String[] args) {
        LinkedQueue<String> theoryQueue = new LinkedQueue<>();
        LinkedQueue<String> exerciseQueue = new LinkedQueue<>();
        LinkedQueue<String> bothQueue = new LinkedQueue<>();

        Scanner input = new Scanner(System.in);
        int numT = input.nextInt();
        for (int i = 0; i < numT; i++) {
            theoryQueue.enqueue(input.next());
        }
        int numE = input.nextInt();
        for (int i = 0; i < numE; i++) {
            exerciseQueue.enqueue(input.next());
        }
        int numB = input.nextInt();
        for (int i = 0; i < numB; i++) {
            bothQueue.enqueue(input.next());
        }
        input.close();

        System.out.println("Polagaat e-test:");
        int term = 1;

        while (!theoryQueue.isEmpty() || !bothQueue.isEmpty()) {
            System.out.println("termin " + term);
            int lab = 0;

            while (lab <= 20 && (!theoryQueue.isEmpty() || !bothQueue.isEmpty())) {
                if (!theoryQueue.isEmpty())
                    System.out.println(theoryQueue.dequeue());
                else if (!bothQueue.isEmpty()) {
                    String student = bothQueue.dequeue();
                    System.out.println(student);
                    exerciseQueue.enqueue(student);           // Се додаваат студентите од комбинираната редица на крај на редот за задачи
                }
                lab++;
            }
            term++;
        }

        System.out.println("Polagaat zadaci:");
        term = 1;
        while (!exerciseQueue.isEmpty()) {
            System.out.println("termin " + term);
            int lab = 0;
            while (lab < 20 && !exerciseQueue.isEmpty()) {
                System.out.println(exerciseQueue.dequeue());
                lab++;
            }
            term++;
        }
    }
}
