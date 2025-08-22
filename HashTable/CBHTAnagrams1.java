import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays; 

/*
APS book basics
Input:      
6           
eat
tea
tan
ate
nat
bat
ant
Output:
2
*/

public class CBHTAnagrams1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        CBHT<String, Integer> hashTable = new CBHT<>(n * 2 - 1);
        for (int i = 0; i < n; i++) {
            String words = br.readLine();                  // tea
            char[] letters = words.toCharArray();          // ['t', 'e', 'a']
            Arrays.sort(letters);                          // ['a', 'e', 't']
            String sortedLetters = new String(letters);    // aet

            SLLNode<MapEntry<String, Integer>> current = hashTable.search(sortedLetters);
            if (current == null) 
                hashTable.insert(sortedLetters, 1);
            else 
                hashTable.insert(sortedLetters, current.element.value + 1);
        }
        String checkWord = br.readLine();
        SLLNode<MapEntry<String, Integer>> current = hashTable.search(checkWord);

        if (current != null)
            System.out.println(current.element.value);
        else
            System.out.println("/");
    }
}

