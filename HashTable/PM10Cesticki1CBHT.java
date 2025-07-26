/*
Napraveni se N merenja vo razlicni opshtini na grad Skopje vo tekot na edno denonokji na PM10 cesticki na kuben metar.
Vasha zadaca e da najdete prosecna vrednost na kolicestvoto PM10 cesticki za dadenata opshtina.
Input:                    Output:
5                         Average quantity of PM10 for Centar: 126,38 (200.22+134.45+44.345 / 3)
Centar 200.35
Karposh 200.22
Aerodrom 420.33
Centar 134.45
Centar 44.345

Centar   -> key
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// APS book basics
class Merenje {
    double price;
    int counter;

    public Merenje(double price, int counter) {
        this.price = price;
        this.counter = counter;
    }
}

public class PM10Cesticki1CBHT {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        CBHT<String, Merenje> hashTable = new CBHT<>(2 * N + 1);
        for (int i = 0; i < N; i++) {
            String []input = br.readLine().split(" ");
            String opshtina = input[0];
            double cesticki = Double.parseDouble(input[1]);

            SLLNode<MapEntry<String, Merenje>> current = hashTable.search(opshtina);
            if (current != null) {
                double oldPrice = current.element.value.price;
                int oldCounter = current.element.value.counter;

                hashTable.insert(opshtina, new Merenje(oldPrice + cesticki, oldCounter + 1));
            }
            else {
                hashTable.insert(opshtina, new Merenje(cesticki, 1));
            }
        }

        String checkOpshtina = br.readLine();
        SLLNode<MapEntry<String, Merenje>> current = hashTable.search(checkOpshtina);
        if (current != null) {
            double average = current.element.value.price / current.element.value.counter;
            System.out.printf("Average quantity of PM10 for " + checkOpshtina + ": " + "%.2f\n", average);
        }
        else {
            System.out.println("No info about " + checkOpshtina);
        }
    }
}

// ako iskomentirame deloto so prepokrivanje vo funkcijata insert()
//        Scanner input = new Scanner(System.in);
//
//        int N = input.nextInt();
//        CBHT<String, Double> hashTable = new CBHT<>(2 * N + 1);
//        for (int i = 0; i < N; i++) {
//            String opshtina = input.next();
//            double cesticki = input.nextDouble();
//
//            hashTable.insert(opshtina, cesticki);
//        }
//        String checkOpshtina = input.next();
//        SLLNode<MapEntry<String, Double>> current = hashTable.search(checkOpshtina);
//        if (current != null) {
//            double sum = 0;
//            int counter = 0;
//            while (current != null) {
//                sum += current.element.value;
//                counter++;
//                current = current.succ;
//            }
//            System.out.printf("%.2f\n", sum / counter);
//        }
//        else {
//            System.out.println("No info about " + checkOpshtina);
//        }

