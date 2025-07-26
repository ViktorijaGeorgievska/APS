import java.util.Objects;
import java.util.Scanner;

/*
Input
7
Hristina F
Magdalena F
Ivana F
Ivan M
Elena F
Ana F
Makedonka F
F
MARIJA
Ivana
Kristina
Anastasija
END

Output
MAKEDONKA
MAGDALENA
No such name
IVANA
F IVANA 1
No such name
ANA
No such name
*/

class Name implements Comparable<Name>{
    String name;

    public Name(String name){
        this.name = name.toUpperCase();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }
    @Override
    public int hashCode() {
        return (100 * name.charAt(0) + name.charAt(1)) % 9091;
    }
    @Override
    public String toString() {
        return name;
    }
    @Override
    public int compareTo(Name o) {
        return name.compareTo(o.name);
    }
}

public class NameStatistics2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        CBHT<String, Integer> hashTableF = new CBHT<>(9091);
        CBHT<String, Integer> hashTableM = new CBHT<>(9091);
        for (int i = 0; i < n; i++) {
            String name = input.next();
            String pol = input.next();

            if (pol.compareTo("F") == 0) {
                SLLNode<MapEntry<String, Integer>> female = hashTableF.search(name);
                if (female == null) {
                    hashTableF.insert(name, 1);
                }
                else {
                    hashTableF.insert(name, female.element.value + 1);
                }
            }
            else if (pol.compareTo("M") == 0) {
                SLLNode<MapEntry<String, Integer>> male = hashTableM.search(name);
                if (male == null) {
                    hashTableM.insert(name, 1);
                }
                else {
                    hashTableM.insert(name, male.element.value + 1);
                }
            }
        }

        String checkPol = input.next();
        while (true) {
            String names = input.next();
            if (names.equals("END"))
                break;

            if (checkPol.equals("F")) {
                SLLNode<MapEntry<String, Integer>> currentFemale = hashTableF.search(names);
                if (currentFemale != null) {
                    System.out.println(names.toUpperCase());
                    System.out.println("F" + " " + names.toUpperCase() + " " + currentFemale.element.value);
                }
                else {
                    System.out.println("No such name");
                }
            }
            else if (checkPol.equals("M")) {
                SLLNode<MapEntry<String, Integer>> currentMale = hashTableM.search(names);
                if (currentMale != null) {
                    System.out.println(names.toUpperCase());
                    System.out.println("M" + " " + names.toUpperCase() + " " + currentMale.element.value);
                }
                else {
                    System.out.println("No such name");
                }
            }
        }
    }
}
