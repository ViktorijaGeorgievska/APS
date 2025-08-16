import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Наредени луѓе со број на барања, им се извршува едно барање и се враќаат назад во редот. Печати редослед на завршување на луѓето.

Input
5
Nenad 3
Slave 1
Martin 2
Ana 1
Igor 2

Output
Slave
Ana
Martin
Igor
Nenad
*/

class Request {
    String name;
    int numRequests;

    public Request(String name, int numRequests) {
        this.name = name;
        this.numRequests = numRequests;
    }
}

public class IKolokviumVlezna {
    public static void main(String[] args) throws IOException {
        LinkedQueue<Request> queue = new LinkedQueue<>();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numPeople = Integer.parseInt(in.readLine());
        for (int i = 0; i < numPeople; i++) {
            String[] lines = in.readLine().split(" ");
            String name = lines[0];
            int numRequests = Integer.parseInt(lines[1]);
            Request requestObj = new Request(name, numRequests);

            queue.enqueue(requestObj);
        }
        if (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                Request person = queue.dequeue();
                person.numRequests--;
                if (person.numRequests != 0) {
                    queue.enqueue(person);
                } else {
                    System.out.println(person.name);
                }
            }
        }
    }
}
