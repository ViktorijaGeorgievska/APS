import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
APS book basics
Input:
3
Ivana Ivanovska 15/05/1982
Elena Todorovska 30/05/1984
Maja Petrevska 15/05/1986
15/05/2023

Output:
Ivana Ivanovska 41
Maja Petrevska 37
*/

class Employee implements Comparable<Employee> {
    String name;
    String surname;
    String dateOfBirth;

    public Employee(String name, String surname, String dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public int compareTo(Employee other) {
        int compare = this.name.compareTo(other.name);
        if (compare == 0)
            return this.surname.compareTo(other.surname);
        return compare;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}

public class OBHTBirthdaysMoreDifficult1 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());
        OBHT<String, ArrayList<Employee>> hashtable = new OBHT<>(n * 2 - 1);
        for (int i = 0; i < n; i++) {
            String[] line = input.readLine().split(" ");
            Employee employee = new Employee(line[0], line[1], line[2]);

            String key = line[2].substring(0, 5);
            int index = hashtable.search(key);
            ArrayList<Employee> array;
            if (index == -1) {
                array = new ArrayList<>();
                array.add(employee);
                hashtable.insert(key, array);
            } else {
                MapEntry<String, ArrayList<Employee>> alreadyExist = hashtable.getBucket(hashtable.search(key));
                array = alreadyExist.value;
                array.add(employee);
                hashtable.insert(key, array);
            }
        }
        String searchDate = input.readLine();
        String date = searchDate.substring(0, 5);
        int yearSearch = Integer.parseInt(searchDate.substring(6, 10));

        if (hashtable.search(date) != -1) {
            MapEntry<String, ArrayList<Employee>> result = hashtable.getBucket(hashtable.search(date));
            ArrayList<Employee> array = result.value;
            Collections.sort(array);                             // сортирање на низата по име

            for (Employee employee : array) {
                int year = Integer.parseInt(employee.dateOfBirth.substring(6, 10));
                System.out.println(employee + " " + (yearSearch - year));
            }
        } else
            System.out.println("Empty");
    }
}

