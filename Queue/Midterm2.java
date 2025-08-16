import java.util.LinkedList;
import java.util.Scanner;

/*
APS book
Input:
2
4
IlinkaIvanoska
IgorKulev
MagdalenaKostoska
HristinaMihajloska
3
VladimirTrajkovik
SlobodanKalajdziski
AnastasMisev
1
IlinkaIvanoska

Output:
1
IlinkaIvanoska
VladimirTrajkovik
2
SlobodanKalajdziski
AnastasMisev
3
IgorKulev
MagdalenaKostoska
4
HristinaMihajloska
*/
public class Midterm2 {
    public static void main(String[] args) {
        LinkedQueue<String> mathQueue = new LinkedQueue<>();
        LinkedQueue<String> theRestQueue = new LinkedQueue<>();
        LinkedList<String> mathReal = new LinkedList<>();

        Scanner in = new Scanner(System.in);
        int numStudentsPerHours = in.nextInt();
        int numMath = in.nextInt();
        for (int i = 0; i < numMath; i++) {
            mathQueue.enqueue(in.next());
        }
        int numRest = in.nextInt();
        for (int i = 0; i < numRest; i++) {
            theRestQueue.enqueue(in.next());
        }

        int realMath = in.nextInt();
        for (int i = 0; i < realMath; i++) {
            mathReal.add(in.next());
        }
        in.close();

        String student;
        int t = 1;

        while (!mathQueue.isEmpty()) {
            System.out.println(t);
            for (int i = 0; i < numStudentsPerHours;) {
                if (!mathQueue.isEmpty()) {
                    student = mathQueue.peek();
                    if (!mathReal.contains(student)) {
                        theRestQueue.enqueue(mathQueue.dequeue());
                    } else {
                        student = mathQueue.dequeue();
                        i++;
                        System.out.println(student);
                    }
                }
                else if (!theRestQueue.isEmpty()) {
                    student = theRestQueue.dequeue();
                    i++;
                    System.out.println(student);
                }
                else
                    break;
            }
            t++;
            if (mathQueue.isEmpty())
                break;
        }
        if (mathQueue.isEmpty()) {
            while (!theRestQueue.isEmpty()) {
                System.out.println(t);
                for (int i = 0; i < numStudentsPerHours;) {
                    if (!theRestQueue.isEmpty()) {
                        student = theRestQueue.dequeue();
                        i++;
                        System.out.println(student);
                    }
                    else
                        break;
                }
                t++;
            }
        }
    }
}
