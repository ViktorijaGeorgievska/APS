import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Ispit januari
Input:
18
/begin{section}
/begin{text}
more text
/end{text}
/begin{subsection}
/begin{text}
text text
text text
/end{text}
/begin{subsubsection}
/begin{text}
text text
/end{text}
/end{subsubsection}
/begin{text}
text text
/end{text}
/end{subsection}
/end{section}
----------------
10
/begin{section}
/begin{subsection}
/begin{text}
Subsection content
/end{text}
/end{subsection}
/begin{text}
Some more text
/end{text}
/end{section}
Output: true

Input:
4
/begin{text}
text text
/end{text}
/end{section}
----------------
7
/begin{section}
/begin{subsubsection}
/begin{text}
text text
/end{text}
/end{subsubsection}
/end{section}
Output:
false
*/

public class CheckText {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        ArrayStack<String> stack = new ArrayStack<>(n);
        boolean correct = true;

        for (int i = 0; i < n; i++) {
            String input = in.readLine();

            if (input.contains("begin")) {
                String[] subString = input.split("\\{");

                if (subString[1].contains("subsubsection")) {
                    if (stack.peek().contains("subsection")) {
                        stack.push(subString[1]);
                        continue;
                    } else {
                        correct = false;
                        break;
                    }
                }

                if (subString[1].contains("text")) {
                    if (!stack.isEmpty()) {
                        stack.push(subString[1]);
                        continue;
                    } else {
                        correct = false;
                        break;
                    }
                }
                stack.push(subString[1]);                         // push section/subsection (доколку не се иполнети претходните 2 if)
            } else if (input.contains("end")) {
                String[] subString = input.split("\\{");

                if (stack.isEmpty()) {
                    correct = false;
                    break;
                } else if (stack.peek().contains(subString[1]))   // дали последното на стек е исто со тоа што сакаме да гo извадиме
                    stack.pop();
            }
        }
        in.close();
        if (correct)
            System.out.println("true");
        else
            System.out.println("false");
    }
}


