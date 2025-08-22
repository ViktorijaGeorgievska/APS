import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
APS book
Input:
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

Output:
MAKEDONKA
MAGDALENA
No such name
IVANA
F IVANA 1
No such name
ANA
No such name
*/

class Name implements Comparable<Name> {
    String name;

    public Name(String name) {
        this.name = name.toUpperCase();
    }

    @Override
    public int hashCode() {
        return (100 * name.charAt(0) + name.charAt(1)) % 9091;
    }

    @Override
    public int compareTo(Name o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Name temp = (Name) obj;
        return this.name.equals(temp.name);
    }

    @Override
    public String toString() {
        return name;
    }
}

public class CBHTNameStatistics2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        CBHT<Name, Integer> femaleHashtable = new CBHT<>(9091);
        CBHT<Name, Integer> maleHashtable = new CBHT<>(9091);

        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            String[] input = in.readLine().split(" ");
            String name = input[0];
            String pol = input[1];
            Name newName = new Name(name.toUpperCase());

            if (pol.equals("F")) {
                SLLNode<MapEntry<Name, Integer>> searchNode = femaleHashtable.search(newName);
                if (searchNode != null)
                    femaleHashtable.insert(newName, searchNode.element.value + 1);
                else
                    femaleHashtable.insert(newName, 1);
            } else if (pol.equals("M")) {
                SLLNode<MapEntry<Name, Integer>> searchNode = maleHashtable.search(newName);
                if (searchNode != null)
                    maleHashtable.insert(newName, searchNode.element.value + 1);
                else
                    maleHashtable.insert(newName, 1);
            }
        }
        String searchPol = in.readLine().toUpperCase();
        String searchName = in.readLine().toUpperCase();

        while (!searchName.equals("END")) {
            if (searchPol.compareTo("F") == 0) {
                SLLNode<MapEntry<Name, Integer>> resultF1 = femaleHashtable.getFirst(new Name(searchName));
                SLLNode<MapEntry<Name, Integer>> currentF;

                for (currentF = resultF1; currentF != null; currentF = currentF.succ)
                    System.out.println(currentF.element.key.name);

                SLLNode<MapEntry<Name, Integer>> resultF = femaleHashtable.search(new Name(searchName));
                if (resultF != null)
                    System.out.println(searchPol + " " + resultF.element.key.toString() + " " + resultF.element.value.toString());
                else
                    System.out.println("No such name");
            }
            if (searchPol.compareTo("M") == 0) {
                SLLNode<MapEntry<Name, Integer>> resultM1 = maleHashtable.getFirst(new Name(searchName));
                SLLNode<MapEntry<Name, Integer>> currentM;

                for (currentM = resultM1; currentM != null; currentM = currentM.succ)
                    System.out.println(currentM.element.key.name);

                SLLNode<MapEntry<Name, Integer>> resultM = maleHashtable.search(new Name(searchName));
                if (resultM != null)
                    System.out.println(searchPol + " " + resultM.element.key.toString() + " " + resultM.element.value.toString());
                else
                    System.out.println("No such name");
            }
            searchName = in.readLine().toUpperCase();
        }
    }
}

