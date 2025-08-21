import java.util.Scanner;

/*
Courses
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
The user who spent the most purchasing for that city is:
Sara Dobreva with salary 2700 from address 10.73.60.29 who spent 2500
*/

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

