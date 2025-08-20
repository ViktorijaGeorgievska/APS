import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Ispit Juni vlezna
Input:
10
ADD Dogovor 3
STATUS
ADD Spisok 2
STATUS
PRINT 2
STATUS
PRINT 2
STATUS
PRINT 1
STATUS

Output:
Current status:
Dogovor waiting
Dogovor waiting
Dogovor waiting

Current status:
Dogovor waiting
Dogovor waiting
Dogovor waiting
Spisok waiting
Spisok waiting

Current status:
Dogovor printed
Dogovor printed
Dogovor waiting
Spisok waiting
Spisok waiting

Current status:
Dogovor printed
Dogovor printed
Dogovor printed
Spisok printed
Spisok waiting

Current status:
Dogovor printed
Dogovor printed
Dogovor printed
Spisok printed
Spisok printed
*/

public class Printer {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        ArrayQueue<String> waiting = new ArrayQueue<>(n);
        ArrayQueue<String> helper = new ArrayQueue<>(n);
        ArrayQueue<String> done = new ArrayQueue<>(n);
        String[] line;

        for (int i = 0; i < n; i++) {
            line = input.readLine().split(" ");

            if (line[0].equals("ADD")) {
                String doc = line[1];
                int num = Integer.parseInt(line[2]);
                for (int j = 0; j < num; j++)
                    waiting.enqueue(doc);
            } else if (line[0].equals("STATUS")) {
                System.out.println("Current status:");
                String dequeuedDoc;
                if (!done.isEmpty()) {
                    while (!done.isEmpty()) {
                        dequeuedDoc = done.dequeue();
                        helper.enqueue(dequeuedDoc);
                        System.out.println(dequeuedDoc + " printed");
                    }
                    while (!helper.isEmpty())
                        done.enqueue(helper.dequeue());
                }
                if (!waiting.isEmpty()) {
                    while (!waiting.isEmpty()) {
                        dequeuedDoc = waiting.dequeue();
                        helper.enqueue(dequeuedDoc);
                        System.out.println(dequeuedDoc + " waiting");
                    }
                    while (!helper.isEmpty())
                        waiting.enqueue(helper.dequeue());
                }
                System.out.println();
            } else if (line[0].equals("PRINT")) {
                int printNum = Integer.parseInt(line[1]);
                for (int j = 0; j < printNum; j++)
                    done.enqueue(waiting.dequeue());
            }
        }
    }
}

