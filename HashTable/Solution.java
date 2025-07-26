import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

class Person implements Comparable<Person> {
    private String name;
    private int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "<" + name + ", " + age +">";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return (age * name.charAt(0)) % 10;
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }
}

class Project {
    private int workTime;
    private int salaryPerHour;

    Project(int workTime, int salaryPerHour) {
        this.workTime = workTime;
        this.salaryPerHour = salaryPerHour;
    }

    @Override
    public String toString() {
        return "<" + workTime + ", " + salaryPerHour +">";
    }

    int salary() {
        return workTime * salaryPerHour;
    }
}

public class Solution {
    public static void main(String[] args) {
        CBHT<Person, Project> table = new CBHT<Person, Project>(10);

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String name = in.next();
            int age = in.nextInt();
            int workTime = in.nextInt();
            int salaryPerHour = in.nextInt();

            Person person = new Person(name, age);
            Project project = new Project(workTime, salaryPerHour);

            SLLNode<MapEntry<Person, Project>> current = table.search(person);
            if (current != null) {
                if (project.salary() > current.element.value.salary()) {
                    table.insert(person, project);
                }
            }
            else {
                table.insert(person, project);
            }
        }
        System.out.println(table);
    }
}

