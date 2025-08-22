import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
APS book
Input:
5
ACEROLA 0 100 1000
ACIKLOVIR 1 1650 87
HYDROCYKLIN 0 55 10
GENTAMICIN 1 152 90
HYDROCYKLIN20 0 113 20
hydroCyklinn
2
hydroCyklin20
2
END

Output:
No such drug
HYDROCYKLIN20 NEG 113 20
Order made
------------------------
Input:
2
Penicillin 1 300 50
morphine 0 500 5
PENICILLIN
20
MORPHINE
2
Morphine
6
END

Output:
PENICILLIN POS 300 50
Order made
MORPHINE NEG 500 5
Order made
MORPHINE NEG 500 3
No drugs available

доволно е да ги внесе првите 3 букви од името на лекот за да може да му се излиста листа од лекови кои ги има во системот -> треба да направиме nov hash()
    String keyString = (String) key;
        return ((100 * (100 * (100 * 0 + keyString.charAt(2)) + keyString.charAt(1)) + keyString.charAt(0)) % 656565) % buckets.length;
*/
class Drug {
    String name;
    int pozNeg;
    int price;
    int quantity;

    public Drug(String name, int pozNeg, int price, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.pozNeg = pozNeg;
    }

    @Override
    public String toString() {
        if (pozNeg == 1)
            return name.toUpperCase() + " POS " + price + " " + quantity;
        else
            return name.toUpperCase() + " NEG " + price + " " + quantity;
    }
}
public class CBHTPharmacy2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        CBHT<String, Drug> pharmacyHashtable = new CBHT<>(n * 2 + 1);
        for (int i = 0; i < n; i++) {
            String[] input = in.readLine().split(" ");
            String name = input[0].toUpperCase();
            int pozNeg = Integer.parseInt(input[1]);
            int price = Integer.parseInt(input[2]);
            int quantity = Integer.parseInt(input[3]);
            Drug newDrug = new Drug(name, pozNeg, price, quantity);

            pharmacyHashtable.insert(name, newDrug);
        }

        while (true) {
            String searchDrug = in.readLine();
            if (searchDrug.equalsIgnoreCase("END"))
                break;
            int numNeeded = Integer.parseInt(in.readLine());

            SLLNode<MapEntry<String, Drug>> result = pharmacyHashtable.search(searchDrug.toUpperCase());
            if (result == null)
                System.out.println("No such drug");
            else {
                System.out.println(result.element.value);
                int numFound = result.element.value.quantity;
                if (numFound > numNeeded) {
                    System.out.println("Order made");
                    result.element.value.quantity -= numNeeded;
                } else
                    System.out.println("No drugs available");
            }
        }
    }
}
