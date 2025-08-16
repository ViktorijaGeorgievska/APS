import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
APS book
Input:
7 3
APS OS Мrezhi AOK Objektno Strukturno Kalkulus
APS Objektno Мrezhi

Output:
APS 3
OS 1
Мrezhi 2
AOK 2
Objektno 3
Strukturno 3
Kalkulus 3
*/

class Book {
    String title;
    int numTaken;

    public Book(String title, int numTaken) {
        this.title = title;
        this.numTaken = numTaken;
    }

    @Override
    public String toString() {
        return title + " " + numTaken;
    }
}

public class ExamSession2 {
    public static void numTakenBooks(ArrayStack<Book> books, SLL<String> exams) {
        ArrayStack<Book> helpStack = new ArrayStack<>(50);
        SLLNode<String> currentExam = exams.getFirst();

        while (currentExam != null) {
            while (!books.peek().title.equals(currentExam.element) && !books.isEmpty()) {
                Book book = books.pop();
                book.numTaken++;
                helpStack.push(book);
            }
            if (!books.isEmpty()) {
                Book neededBook = books.pop();
                neededBook.numTaken++;

                while (!helpStack.isEmpty())
                    books.push(helpStack.pop());

                books.push(neededBook);
            }
            currentExam = currentExam.succ;
        }
        ArrayStack<Book> helpStack2 = new ArrayStack<>(50);
        while (!books.isEmpty())
            helpStack2.push(books.pop());
        while (!helpStack2.isEmpty())
            System.out.println(helpStack2.pop());
    }

    public static void main(String[] args) throws IOException {
        ArrayStack<Book> books = new ArrayStack<>(50);
        SLL<String> exams = new SLL<>();

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = input.readLine().split(" ");
        int M = Integer.parseInt(firstLine[0]);
        int N = Integer.parseInt(firstLine[1]);

        String[] secondLine = input.readLine().split(" ");
        for (int i = 0; i < M; i++)
            books.push(new Book(secondLine[i], 0));

        String[] thirdLine = input.readLine().split(" ");
        for (int i = 0; i < N; i++)
            exams.insertLast(thirdLine[i]);
        
        input.close();
        numTakenBooks(books, exams); 
    }
}
