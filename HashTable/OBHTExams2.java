import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
APS book
Input:
5
27/01/2016 14:00 Rooms Kalkulus 1/Matematika 1
27/01/2016 08:00 Laboratories Napredno programiranje
28/01/2016 08:00 Laboratories Algoritmi i podatochni strukturi
28/01/2016 14:00 Rooms Diskretna matematika 1
28/01/2016 09:00 315 Kalkulus 3
28/01/2016

Output:
08:00 Laboratories Algoritmi i podatochni strukturi
09:00 315 Kalkulus 3
14:00 Rooms Diskretna matematika 1
*/

class SubjectInfo implements Comparable<SubjectInfo> {
    String name;
    String room;
    String hour;

    public SubjectInfo(String name, String room, String hour) {
        this.name = name;
        this.room = room;
        this.hour = hour;
    }

    @Override
    public String toString() {
        return hour + " " + room + " " + name;
    }

    @Override
    public int compareTo(SubjectInfo o) {
        return this.hour.compareTo(o.hour);
    }
}

public class OBHTExams2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader((System.in)));

        int n = Integer.parseInt(input.readLine());
        OBHT<String, ArrayList<SubjectInfo>> hashTable = new OBHT<>(2 * n - 1);
        for (int i = 0; i < n; i++) {
            String line = input.readLine();                                         // 27/01/2016 08:00 Laboratories Napredno programiranje

            String date = line.substring(0, 10);                                    // 27/01/2016
            String time = line.substring(11, 16);                                   // 08:00
            String restInfo = line.substring(17);                        // Laboratories Napredno programiranje

            int firstSpace = restInfo.indexOf(" ");                                // 12
            String room = restInfo.substring(0, firstSpace);                       // Laboratories
            String subjectName = restInfo.substring(firstSpace + 1);    // Napredno programiranje

            SubjectInfo subjectObj = new SubjectInfo(subjectName, room, time);

            int index = hashTable.search(date);
            ArrayList<SubjectInfo> listSubjects;
            if (index != -1)
                listSubjects = hashTable.getBucket(index).value;
            else
                listSubjects = new ArrayList<>();
            listSubjects.add(subjectObj);
            hashTable.insert(date, listSubjects);
        }
        String searchDate = input.readLine();

        int index = hashTable.search(searchDate);
        if (index != -1) {
            MapEntry<String, ArrayList<SubjectInfo>> result = hashTable.getBucket(index);
            ArrayList<SubjectInfo> list = result.value;
            Collections.sort(list);

            for (SubjectInfo s : list)
                System.out.println(s);
        } else
            System.out.println("No exams on date: " + searchDate + "!");
    }
}