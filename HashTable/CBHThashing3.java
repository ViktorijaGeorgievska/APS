import java.util.LinkedList;
import java.util.Scanner;

/*
Courses + Lab
Input:
5
Jovan Todorov    1000    10.73.112.200     16:30   Bitola     760
Mitko Janev      4350    132.28.112.200    12:15   Krusevo    4000
Sara Dobreva     2700    10.73.60.29       14:35   Bitola     2500
Mence Trajanova  4000    10.73.112.112     11:25   Bitola     4200
Viktor Jovev     2200    10.73.112.79      15:15   Strumica   1800

1
Jovan Todorov    1000    10.73.112.200     16:30   Bitola     760

Output:
City: Bitola has the following number of customers:
2
The user who logged on earliest after noon from that city is:
Sara Dobreva with salary 2700 from address 10.73.60.29 who logged in at 14:35
*/

public class CBHThashing3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        CBHT<String, LinkedList<Traveler>> hashTable1 = new CBHT<>(n * 2 - 1);    // само број колку луѓе има на истата мрежа кои се вклучиле после 12h
        for (int i = 0; i < n; i++) {
            String name = input.next();
            String surname = input.next();
            int budget = input.nextInt();
            String ip = input.next();
            String time = input.next();
            String city = input.next();
            int ticketPrice = input.nextInt();
            Traveler t = new Traveler(name, surname, budget, ip, time, city, ticketPrice, 1);

            SLLNode<MapEntry<String, LinkedList<Traveler>>> searchNode = hashTable1.search(city);
            int hour = Integer.parseInt(time.split(":")[0]);
            LinkedList<Traveler> list;                                                 // во листа се чуваат сите Travelers (за полесно да се најде најрано време!)
            if (hour >= 12) {
                if (searchNode == null) {
                    list = new LinkedList<>();
                    list.add(t);
                    hashTable1.insert(city, list);
                }
                else {
                    list = searchNode.element.value;
                    list.element().count += 1;
                    list.add(t);
                    hashTable1.insert(city, list);
                }
            }
        }
        int m = input.nextInt();
        for (int i = 0; i < m; i++) {
            String name = input.next();
            String surname = input.next();
            int budget = input.nextInt();
            String ip = input.next();
            String time = input.next();
            String city = input.next();
            int ticketPrice = input.nextInt();

            SLLNode<MapEntry<String, LinkedList<Traveler>>> result = hashTable1.search(city);
            if (result != null) {
                LinkedList<Traveler> listTravelers = result.element.value;
                Traveler earliest = listTravelers.getFirst();
                for (Traveler t : listTravelers) {
                    int hour = Integer.parseInt(t.time.split(":")[0]);
                    int minutes = Integer.parseInt(t.time.split(":")[1]);
                    if (hour < Integer.parseInt(earliest.time.split(":")[0])) {
                        earliest = t;
                    } else if (hour == Integer.parseInt(earliest.time.split(":")[0])) {
                        if (minutes < Integer.parseInt(earliest.time.split(":")[1]))
                            earliest = t;
                    }
                }

                System.out.println("City: " + city + " has the following number of users: ");
                System.out.println(result.element.value.element().count);
                System.out.println("The user who logged on earliest after noon from that network is: ");
                System.out.println(earliest);
                System.out.println();
            }
        }
    }
}
