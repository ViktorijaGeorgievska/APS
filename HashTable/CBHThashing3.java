import java.io.IOException;
import java.util.Scanner;

public class hashing3 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        CBHT<String, Traveler> hashtable = new CBHT<>(n * 2 - 1);
        CBHT<String, Traveler> hashtable2 = new CBHT<>(n * 2 - 1);
        for (int i = 0; i < n; i++) {
            String name = in.next();
            String surname = in.next();
            int budget = in.nextInt();
            String ip = in.next();
            String time = in.next();
            String city = in.next();
            int ticketPrice = in.nextInt();
            Traveler traveler = new Traveler(name, surname, budget, ip, time, city, ticketPrice, 0);

            // броиме колку од градот се вклучиле пред 12 часот
            SLLNode<MapEntry<String, Traveler>> search = hashtable.search(city);
            if (search != null) {
                String[] travelTime = search.element.value.time.split(":");
                if (Integer.parseInt(travelTime[0]) >= 12) {
                    traveler.setCount(search.element.value.count + 1);
                    hashtable.insert(city, traveler);
                }
            } else {
                hashtable.insert(city, traveler);
            }

            // кои се вклучил најрано од сите вклучени пред 12 часот
            SLLNode<MapEntry<String, Traveler>> search2 = hashtable2.search(city);
            int min = 0;
            boolean found = false;
            if (budget >= ticketPrice) {
                if (search2 != null) {
                    String[] travelTime = search2.element.value.time.split(":");
                    int t = Integer.parseInt(travelTime[0]) + Integer.parseInt(travelTime[1])/60;
                    if (t >= 12) {
                        if (!found) {
                            min = t;
                            found = true;
                        }
                        if (t < min) {
                            min = t;
                        }
                        hashtable2.insert(city, traveler);
                    }
                } else {
                    hashtable2.insert(city, traveler);
                }
            }
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            String name = in.next();
            String surname = in.next();
            int budget = in.nextInt();
            String ip = in.next();
            String time = in.next();
            String city = in.next();
            int ticketPrice = in.nextInt();

            SLLNode<MapEntry<String, Traveler>> search = hashtable.search(city);
            SLLNode<MapEntry<String, Traveler>> search2 = hashtable2.search(city);

            if (search != null) {
                System.out.println("City: " + city + " has the following number of customers: ");
                System.out.println(search.element.value.count);

                if (search2 != null) {
                    System.out.println("The user who logged on earliest after noon from that city is: ");
                    String nameSurname = search2.element.value.name + " " + search2.element.value.surname;
                    int salary = search2.element.value.budget;
                    String ipAddress = search2.element.value.ip;
                    String timeBuying = search2.element.value.time;
                    System.out.println(nameSurname + " with salary " + salary + " from address " + ipAddress + " who logged in at " + timeBuying);
                    System.out.println();
                }
            }
        }
    }
}
