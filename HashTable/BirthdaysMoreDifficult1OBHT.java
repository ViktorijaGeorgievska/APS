import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Employees implements Comparable<Employees> {
    String name;
    String surname;
    String dateBirth;

    public Employees(String name, String dateBirth, String surname) {
        this.name = name;
        this.dateBirth = dateBirth;
        this.surname = surname;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    @Override
    public int compareTo(Employees o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname;
    }
}

public class BirthdaysMoreDifficult1OBHT {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());
        OBHT<String, ArrayList<Employees>> hashtable = new OBHT<>(2 * n);
        for (int i = 0; i < n; i++) {
            String []lines = input.readLine().split(" ");
            Employees employees = new Employees(lines[0], lines[1], lines[2]);

            String key = lines[2].substring(0, 5);          // 15/04/

            if (hashtable.search(key) != -1) {
                MapEntry<String, ArrayList<Employees>> result = hashtable.getBucket(hashtable.search(key));
                ArrayList<Employees> array = result.value;
                array.add(employees);
                hashtable.insert(key, array);
            } else {
                ArrayList<Employees> array = new ArrayList<>();
                array.add(employees);
                hashtable.insert(key, array);
            }
        }
        String searchDate = input.readLine();
        String date = searchDate.substring(0, 5);
        int year = Integer.parseInt(searchDate.substring(6, 10));

        if (hashtable.search(date) != -1) {
            MapEntry<String, ArrayList<Employees>> result = hashtable.getBucket(hashtable.search(date));
            ArrayList<Employees> array = result.value;

            Employees []p = new Employees [array.size()];
            for (int i = 0; i < p.length; i++) {
                p[i] = array.get(i);
            }
            Arrays.sort(p);
            for (int i = 0; i < p.length; i++) {
                int yearSearch = Integer.parseInt(p[i].getDateBirth().substring(6, 10));
                System.out.println(p[i].toString() + " " + (year - yearSearch));
            }
        }
        else {
            System.out.println("Empty");
        }
    }
}
