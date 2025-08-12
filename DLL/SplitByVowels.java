import java.util.Scanner;

/*
Vlezna
Input:
a b c d e f g h i j
a i e
Output:
j b c h d g f
===================
Input:
a b c d e f g h i j o
a o i e
Output:
b j c d h g f
*/

public class SplitByVowels {
    public static void print(DLL<Character> list) {
        DLLNode<Character> current = list.getFirst();
        while (current != null) {
            System.out.print(current.element + " ");
            current = current.succ;
        }
        System.out.println();
    }

    public static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static void split(DLL<Character> list) {
        DLL<Character> vowels = new DLL<>();
        DLL<Character> consonants = new DLL<>();

        DLLNode<Character> first = list.getFirst();
        DLLNode<Character> last = list.getLast();

        while (first != null && last != null && first != last.succ) {
            if (isVowel(first.element))
                vowels.insertLast(first.element);
            else
                consonants.insertLast(first.element);

            if (first == last)               // доколоку го обработиме средниот елемент (за непарна листа)
                break;

            first = first.succ;

            if (isVowel(last.element))
                vowels.insertLast(last.element);
            else
                consonants.insertLast(last.element);
            last = last.pred;
        }
        print(vowels);
        print(consonants);
    }

    public static void main(String[] args) {
        DLL<Character> list = new DLL<>();

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] letters = input.split(" ");

        for (String letter : letters)
            list.insertLast(letter.charAt(0));
        split(list);
    }
}
