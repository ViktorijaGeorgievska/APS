import java.util.Scanner;

/*
Input:
4
macka cat
kuche dog
lav lion
papagal parrot
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

public class CBHTDictionary {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        CBHT<String, String> hashTableDictionary = new CBHT<>(2 * n - 1);
        for (int i = 0; i < n; i++) {
            String mkd = input.next();
            String eng = input.next();

            hashTableDictionary.insert(eng, mkd);
        }
        System.out.println("Searching...");
        while(true) {
            String word = input.next();
            if (word.equalsIgnoreCase("KRAJ"))
                break;

            SLLNode<MapEntry<String, String>> current = hashTableDictionary.search(word);  // vrakja pokazuvac kon jazolot so toj key
            if (current != null) {
                System.out.println(current.element.value);
            }
            else {
                System.out.println("/");
            }
        }
    }
}
