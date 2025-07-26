import java.util.Scanner;

/*
11
<Person>
    <ime>
        Viktorija
    </ime>
    <prezime>
        Georgievska
    </prezime>
    <vozrast>
        20
     </vozrast>
</Person>
*/
public class HtmlTags {
    public static boolean isCorrect(String []commands) {
        LinkedStack<String> stack = new LinkedStack<>();

        for (int i = 0; i < commands.length; i++) {
            if (commands[i].charAt(0) == '<') {
                if (commands[i].charAt(1) == '/') {
                    if (stack.isEmpty())
                        return false;
                    if (!stack.pop().substring(1).equals(commands[i].substring(2)))           // Person> (od otvoreniot tag) == Person> (od zatvoreniot tag)
                        return false;
                }
                else {
                    stack.push(commands[i]);
                }
            }

        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        String []commands = new String[n];
        for (int i = 0; i < n; i++) {
            commands[i] = input.next();
        }
        input.close();

        if (isCorrect(commands))
            System.out.println("The commands are correct!");
        else
            System.out.println("The commands are incorrect!");
    }
}
