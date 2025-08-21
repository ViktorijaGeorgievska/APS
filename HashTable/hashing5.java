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
IP network: 10.73.112 has the following number of users:
2
The user who spent the most from that network is:
Viktor Jovev with salary 2200 from address 10.73.112.79 who spent 1800
*/

public class hashing5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        CBHT<String, Traveler> hashTable = new CBHT<>(n * 2 - 1);
        CBHT<String, Traveler> hashTable2 = new CBHT<>(n * 2 - 1);
        for (int i = 0; i < n; i++) {
            String name = input.next();
            String surname = input.next();
            int budget = input.nextInt();
            String ip = input.next();
            String time = input.next();
            String city = input.next();
            int price = input.nextInt();

            String newIp = ip.split("\\.")[0] + "." + ip.split("\\.")[1] + "." + ip.split("\\.")[2];

            Traveler traveler = new Traveler(name, surname, budget, ip, time, city, price,1);

            SLLNode<MapEntry<String, Traveler>> current = hashTable.search(newIp);
            if (budget >= price) {
                if (current == null) {
                    hashTable.insert(newIp, traveler);
                }
                else {
                    traveler.setCount(current.element.value.count + 1);
                    hashTable.insert(newIp, traveler);
                }
            }

            SLLNode<MapEntry<String, Traveler>> current2 = hashTable2.search(newIp);
            if (budget >= price) {
                if (current == null) {
                    hashTable2.insert(newIp, traveler);
                }
                else {
                    if (traveler.price > current2.element.value.price) {
                        hashTable2.insert(newIp, traveler);
                    }
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
            int price = input.nextInt();

            String newIp = ip.split("\\.")[0] + "." + ip.split("\\.")[1] + "." + ip.split("\\.")[2];

            SLLNode<MapEntry<String, Traveler>> current = hashTable.search(newIp);
            SLLNode<MapEntry<String, Traveler>> current2 = hashTable2.search(newIp);

            System.out.println("IP network: " + newIp + " has the following number of users:");
            System.out.println(current.element.value.count);
            System.out.println("The user who spent the most from that network is:");
            System.out.println(current2.element.value.name + " " + current2.element.value.surname + " with salary " + current2.element.value.budget + " from address "  + current2.element.value.ip + " who spent " + current2.element.value.price);
            System.out.println();
        }
    }
}

