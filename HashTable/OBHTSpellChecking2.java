import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Courses + APS book
Input:
4
where
is
my
cat
Where is my Ccat?

Output:
Ccat
*/

class Word implements Comparable<Word> {
    String word;

    public Word(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        Word temp = (Word) o;
        return this.word.equals(temp.word);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < word.length(); i++) {
            hash += word.charAt(i);                // Збир на ASCII вредностите на сите карактери
        }
        hash += word.length();                     // Должина на зборот
        return hash;
    }

    @Override
    public int compareTo(Word o) {
        return this.word.compareTo(o.word);
    }

    @Override
    public String toString() {
        return word;
    }
}

public class OBHTSpellChecking2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());
        OBHT<Word, String> hashtable = new OBHT<>(n * 2 - 1);
        for (int i = 0; i < n; i++) {
            String inputWord = input.readLine().replaceAll("[?!.,]", "");
            Word wordObj = new Word(inputWord);

            String newWord = wordObj.word.toLowerCase().replaceAll("[?!.,]", "");
            Word newWordObj = new Word(newWord);
            hashtable.insert(newWordObj, wordObj.word);
        }
        String[] checkText = input.readLine().split(" ");
        int validWords = 0;
        int m = 0;

        for (int i = 0; i < checkText.length; i++) {
            String original = checkText[i];
            checkText[i] = original.toLowerCase().replaceAll("[?!.,]", "");
            if (checkText[i].isEmpty())               // Доколку checkText[i] остане без текст (кога имаме само . во input) тоа да не се брои како валиден збор
                continue; 
            validWords++;

            Word searchWord = new Word(checkText[i]);
            if (hashtable.search(searchWord) == -1)
                System.out.println(original.replaceAll("[?!.,]", ""));
            else
                m++;
        }
        if (m == validWords)
            System.out.println("Bravo");
    }
}

