import java.util.ArrayList;
import java.util.Scanner;

/*
APS book
Input:
4
IlinkaIvanoska
IgorKulev
MagdalenaKostoska
HristinaMihajloska
3
APS 3
MIS 1
OOS 2
1
HristinaMihajloska

Output:
APS
3
IlinkaIvanoska
IgorKulev
MagdalenaKostoska
MIS
1
IlinkaIvanoska
OOS
2
IgorKulev
MagdalenaKostoska
*/

class Subject {
    String name;
    int numNeededTeachers;

    public Subject(String name, int numNeededTeachers) {
        this.name = name;
        this.numNeededTeachers = numNeededTeachers;
    }
}

public class MidtermWeek2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        ArrayQueue<String> teachers = new ArrayQueue<>(n);
        for (int i = 0; i < n; i++)
            teachers.enqueue(input.next());

        int numSubjects = input.nextInt();
        ArrayQueue<Subject> subjects = new ArrayQueue<>(numSubjects);
        for (int i = 0; i < numSubjects; i++)
            subjects.enqueue(new Subject(input.next(), input.nextInt()));

        int numAbsentTeachers = input.nextInt();
        ArrayList<String> absentTeachers = new ArrayList<>();
        for (int i = 0; i < numAbsentTeachers; i++)
            absentTeachers.add(input.next());
        input.close();

        while (!subjects.isEmpty()) {
            Subject subject = subjects.dequeue();

            System.out.println(subject.name);
            System.out.println(subject.numNeededTeachers);

            for (int i = 0; i < subject.numNeededTeachers;) {
                String teacher = teachers.dequeue();
                teachers.enqueue(teacher);

                if (!absentTeachers.contains(teacher)) {
                    System.out.println(teacher);
                    i++;
                    /* i значи „колку наставници сум успеала да доделам“, а не „колку наставници сум извадила од редицата“.
                    Ако наставникот е отсутен → не може да се додели → i не смее да се зголеми.
                    Ако наставникот е присутен → го доделуваме → тогаш i++.
                    */
                }
            }
        }
    }
}