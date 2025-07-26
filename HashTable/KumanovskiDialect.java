import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KumanovskiDialect {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] dictionary = new String[N];
        CBHT<String, String> table = new CBHT<>(N * 2 + 1);
        for (int i = 0; i < N; i++) {
            dictionary[i] = br.readLine();
            String dialectWord = dictionary[i].split(" ")[0];
            String mkdWord = dictionary[i].split(" ")[1];

            table.insert(dialectWord, mkdWord);
        }

        String inputText = br.readLine();
        String[] text = inputText.split(" ");

        if (N == 0) {
            System.out.println(inputText);
            return;
        }

        StringBuilder correctText = new StringBuilder();
        char punctuation = '#';

        for (String word : text) {
            punctuation = '#';
            if (word.charAt(word.length() - 1) == '.' || word.charAt(word.length() - 1) == ',' ||              // zivot.
                    word.charAt(word.length() - 1) == '!' || word.charAt(word.length() - 1) == '?') {
                punctuation = word.charAt(word.length() - 1);                                                  // punctuation = .
                word = word.substring(0, word.length() - 1);                                                   // word = zivot
            }

            String searchWord = word.toLowerCase();
            String value;

            SLLNode<MapEntry<String, String>> searchNode = table.search(searchWord);
            if (searchNode != null) {
                value = searchNode.element.value;

                // Check if first letter of the word is upper
                if (Character.isUpperCase(word.charAt(0))) {
                    correctText.append(Character.toUpperCase(value.charAt(0))).append(value.substring(1));
                } else {
                    correctText.append(value);
                }
            } else {
                correctText.append(word);
            }

            // Check if there is punctuation
            if (punctuation != '#') {
                correctText.append(punctuation);
            }
            correctText.append(" ");
        }
        System.out.println(correctText);
    }
}
