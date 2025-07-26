import java.util.Scanner;
/*
Input
6
Centar Stojanoski negative
Centar Trajkovski positive
Centar Petkovski positive
Karpos Stojanoski positive
Karpos Trajkovski negative
Centar Trajkovski positive
Karpos    /  Centar

Output
0.5           0.75
*/
class CovidStatistics {
    String city;
    String surname;
    int cPoz;
    int cNeg;

    public CovidStatistics(String city, String surname, int cPoz, int cNeg) {
        this.city = city;
        this.surname = surname;
        this.cPoz = 0;
        this.cNeg = 0;
    }

    public void setcNeg(int cNeg) {
        this.cNeg = cNeg;
    }
    public void setcPoz(int cPoz) {
        this.cPoz = cPoz;
    }
}

public class Epidemic2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        CBHT<String, CovidStatistics> hashTable = new CBHT<>(2 * n - 1);
        for (int i = 0; i < n; i++) {
            String city = input.next();
            String surname = input.next();
            String pozNeg = input.next();
            CovidStatistics c = new CovidStatistics(city, surname, 0 , 0);

            SLLNode<MapEntry<String, CovidStatistics>> current = hashTable.search(city);
            if (current == null) {
                if (pozNeg.equals("negative")) {
                    c.setcPoz(0);
                    c.setcNeg(1);
                }
                else {
                    c.setcNeg(0);
                    c.setcPoz(1);
                }
                hashTable.insert(city, c);
            }
            else {
                if (pozNeg.equals("negative")) {
                    c.setcNeg(current.element.value.cNeg + 1);
                    c.setcPoz(current.element.value.cPoz);
                }
                else {
                    c.setcPoz(current.element.value.cPoz + 1);
                    c.setcNeg(current.element.value.cNeg);
                }
                hashTable.insert(city, c);
            }
        }
        String city = input.next();
        SLLNode<MapEntry<String, CovidStatistics>> current = hashTable.search(city);
        if (current != null) {
            int cPoz = current.element.value.cPoz;
            int cNeg = current.element.value.cNeg;
            System.out.println((double) cPoz / (cNeg + cPoz));
        }
    }
}
