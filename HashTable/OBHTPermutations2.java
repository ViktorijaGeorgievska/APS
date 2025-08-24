import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
APS book
Input:
4
stop
tsop
ooos
toos
tosp

Output:
2
*/

public class OBHTPermutations2 {
    public static String makeWord (String word) {
        char[] letters = word.toCharArray();
        Arrays.sort(letters);

        return new String(letters);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader((System.in)));

        int n = Integer.parseInt(input.readLine());
        OBHT<String, Integer> hashTable = new OBHT<>(2 * n - 1);
        for (int i = 0; i < n; i++) {
            String word = input.readLine();

            String sortedWord = makeWord(word);

            int index = hashTable.search(sortedWord);
            if (index != -1)
                hashTable.insert(sortedWord, hashTable.getBucket(index).value + 1);
            else
                hashTable.insert(sortedWord, 1);
        }
        String searchWord = input.readLine();
        searchWord = makeWord(searchWord);
        if (hashTable.search(searchWord) != -1)
            System.out.println(hashTable.getBucket(hashTable.search(searchWord)).value);
        else
            System.out.println(0);
    }
}
