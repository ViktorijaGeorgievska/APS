import java.util.Scanner;

public class hashing2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        CBHT<String, Traveler> hashTable = new CBHT<>(N * 2 - 1);
        CBHT<String, Traveler> hashTable2 = new CBHT<>(N * 2 - 1);
        for (int i = 0; i < N; i++) {
            String name = input.next();
            String surname = input.next();
            int budget = input.nextInt();
            String ip = input.next();
            String time = input.next();
            String city = input.next();
            int price = input.nextInt();
            Traveler t = new Traveler(name, surname, budget, ip, time, city, price, 1);

            SLLNode<MapEntry<String, Traveler>> current = hashTable.search(city);

            if (budget >= price) {
                if (current == null) {
                    hashTable.insert(city, t);
                }
                else {
                    t.setCount(current.element.value.count + 1);
                    hashTable.insert(city, t);
                }
            }

            SLLNode<MapEntry<String, Traveler>> current2 = hashTable2.search(city);
            if (budget >= price) {
                if (current2 == null) {
                    hashTable2.insert(city, t);
                }
                else {
                    if (t.price > current2.element.value.price) {
                        hashTable2.insert(city, t);
                    }
                }
            }
        }

        int M = input.nextInt();
        for (int i = 0; i < M; i++) {
            String name = input.next();
            String surname = input.next();
            int budget = input.nextInt();
            String ip = input.next();
            String time = input.next();
            String city = input.next();
            int price = input.nextInt();

            SLLNode<MapEntry<String, Traveler>> current = hashTable.search(city);
            SLLNode<MapEntry<String, Traveler>> current2 = hashTable2.search(city);

            if (current != null) {
                System.out.println("City: " + city + " has the following number of customers: ");
                System.out.println(current.element.value.count);

                if (current2 != null) {
                    System.out.println("The user who spent the most purchasing for that city is:");
                    System.out.println(current2.element.value.name + " " + current2.element.value.surname + " with salary " + current2.element.value.budget + " from address " + current2.element.value.ip + " who spent " + current2.element.value.price);
                    System.out.println();
                }
            }
        }
    }
}
