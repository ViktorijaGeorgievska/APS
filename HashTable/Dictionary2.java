import java.util.Scanner;
/*
Input:
5
cat macka
dog kuche
lion lav
parrot papagal
fish riba
--------------
parrot
giraffe
lion
KRAJ

Output:
papagal
/
lav
*/

public class Dictionary2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        OBHT<String, String> hashTable = new OBHT<>(n * 2 - 1);
        for (int i = 0; i < n; i++) {
            String eng = input.next();
            String mkd = input.next();

            hashTable.insert(eng, mkd);
        }

        while (true) {
            String word = input.next();
            if (word.equalsIgnoreCase("END"))
                break;

            int b = hashTable.search(word);
            if (b != OBHT.NONE) {
                MapEntry<String, String> current = hashTable.getMap(b);
                System.out.println(current.value);
            }
            else {
                System.out.println("/");
            }
        }
    }
}
