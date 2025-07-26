import java.util.Scanner;

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
