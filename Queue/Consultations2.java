import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
APS book
Input:
3
IlinkaIvanoska A
MagdalenaKostoska A
HristinaMihajloska B
1
IgorKulev

Output:
IlinkaIvanoska
IgorKulev
HristinaMihajloska
MagdalenaKostoska
*/

class Student {
    String name;
    String typeQuestion;

    public Student(String name, String typeQuestion) {
        this.name = name;
        this.typeQuestion = typeQuestion;
    }
}

public class Consultations2 {
    public static void main(String[] args) throws IOException {
        LinkedQueue<Student> apsStudents = new LinkedQueue<>();
        LinkedQueue<String> mmsStudents = new LinkedQueue<>();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            String []lines = in.readLine().split(" ");

            Student s = new Student(lines[0], lines[1]);
            apsStudents.enqueue(s);
        }
        int m = Integer.parseInt(in.readLine());
        for (int i = 0; i < m; i++) {
            mmsStudents.enqueue(in.readLine());
        }

        while (!apsStudents.isEmpty()) {
            Student s = apsStudents.dequeue();
            System.out.println(s.name);
            String type = s.typeQuestion;
            if (!apsStudents.isEmpty() && apsStudents.peek().typeQuestion.equals(type)) {
                apsStudents.enqueue(apsStudents.dequeue());

                if (!mmsStudents.isEmpty()) {
                    System.out.println(mmsStudents.dequeue());
                }
            }
        }
        while (!mmsStudents.isEmpty()) {
            System.out.println(mmsStudents.dequeue());
        }
    }
}
