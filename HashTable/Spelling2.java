import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
Input:                 Output:
4                      Ccat
where
is
my
cat
Where is my Ccat?
*/

// APS book
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
            hash += word.charAt(i);              // Збир на ASCII вредностите на сите карактери
        }
        hash += word.length();                   // Должина на зборот
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

public class Spelling2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        OBHT<Word, String> tabela = new OBHT<>(n * 2);

        for (int i = 0; i < n; i++) {
            String wordInput = input.readLine();
            Word wordObj = new Word(wordInput);
            String newWord = wordObj.word.toLowerCase().replaceAll("[?!.,]","");

            tabela.insert(new Word(newWord), wordObj.word.replaceAll("[?!.,]",""));
        }
        String []checkText = input.readLine().split(" ");
        int m = 0;
        for (int i = 0; i < checkText.length; i++) {
            String original = checkText[i];
            checkText[i] = original.toLowerCase().replaceAll("[?!.,]","");
            Word searchWord = new Word(checkText[i]);

            if (tabela.search(searchWord) == -1) {
                System.out.println(original.replaceAll("[?!.,]", ""));
            } else {
                m++;
            }
        }
        if (m == checkText.length) {
            System.out.println("Bravo");
        }
    }
}
