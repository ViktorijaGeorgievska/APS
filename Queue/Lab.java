import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Reader {
    String nameSurname;
    int isScience;
    int isSciFiction;
    int isHistory;

    public Reader(String nameSurname, int isScience, int isSciFiction, int isHistory) {
        this.nameSurname = nameSurname;
        this.isScience = isScience;
        this.isSciFiction = isSciFiction;
        this.isHistory = isHistory;
    }

    public boolean isDone() {
        return isScience == 0 && isSciFiction == 0 && isHistory == 0;
    }

    @Override
    public String toString() {
        return nameSurname;
    }
}

public class Lab {
    public static void main(String[] args) throws IOException {
        LinkedQueue<Reader> reader = new LinkedQueue<>();

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        for (int i = 0; i < n; i++) {
            String line = input.readLine();
            int science = Integer.parseInt(input.readLine());
            int sciFiction = Integer.parseInt(input.readLine());
            int history = Integer.parseInt(input.readLine());
            Reader r = new Reader(line, science, sciFiction, history);

            reader.enqueue(r);
        }

        int sciCounter = 0, sciFiCounter = 0, hisCounter = 0;

        while (!reader.isEmpty()) {
            Reader current = reader.dequeue();
            boolean served = false;

            if (sciCounter < 2 && current.isScience == 1) {
                current.isScience = 0;
                sciCounter++;
                served = true;
            } else if (sciCounter >= 2 && sciFiCounter < 1 && current.isSciFiction == 1) {
                current.isSciFiction = 0;
                sciFiCounter++;
                served = true;
            } else if (sciCounter >= 2 && sciFiCounter >= 1 && hisCounter < 2 && current.isHistory == 1) {
                current.isHistory = 0;
                hisCounter++;
                served = true;
            }

            if (current.isDone()) {
                System.out.println(current);
            }
            else
                reader.enqueue(current);         // врати го назад во ред ако има уште книги


           // Reset counters when a full cycle is completed or when no more progress can be made
            if ((sciCounter == 2 && sciFiCounter == 1 && hisCounter == 2) ||
                    (!served && sciCounter >= 2 && sciFiCounter >=1 && hisCounter >= 2)) {
                sciCounter = 0;
                sciFiCounter = 0;
                hisCounter = 0;
            }
        }
    }
}
