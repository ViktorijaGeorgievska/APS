/*
APS book
Влез:
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
Излез:
No such drug
HYDROCYKLIN20 NEG 113 20
Order made
*/

// доволно е да ги внесе првите 3 букви од името на лекот за да може да му се излиста листа од лекови кои ги има во системот -> treba da napravime nov hash()

import java.util.Scanner;

class Lek {
    String ime;
    byte pozNeg;
    int cena;
    int zaliha;

    public Lek(String ime, byte pozNeg, int cena, int zaliha) {
        this.ime = ime;
        this.pozNeg = pozNeg;
        this.cena = cena;
        this.zaliha = zaliha;
    }

    public String toString() {
        String s = new String();
        if (pozNeg == 0)
            s = ime + " NEG " + cena + " " + zaliha;
        else
            s = ime + " POZ " + cena + " " + zaliha;

        return s;
    }
}

public class Pharmacy2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        CBHT<String, Lek> hashTable = new CBHT<>(n * 2 + 1);
        for (int i = 0; i < n; i++) {
            String ime = input.next();
            byte pozNeg = input.nextByte();
            int cena = input.nextInt();
            int zaliha = input.nextInt();

            Lek lek = new Lek(ime, pozNeg, cena, zaliha);
            hashTable.insert(ime.toUpperCase(), lek);
        }

        while (true) {
            String imeLek = input.next();
            if (imeLek.equalsIgnoreCase("END"))
                break;
            int brParcinja = input.nextInt();

            SLLNode<MapEntry<String, Lek>> current = hashTable.search(imeLek.toUpperCase());
            if (current == null) {
                System.out.println("No such drug");
            }
            else {
                System.out.println(current.element.value);
                if (current.element.value.zaliha >= brParcinja) {
                    System.out.println("Order made");
                    current.element.value.zaliha -= brParcinja;
                }
                else {
                    System.out.println("No drugs available");
                }
            }
        }
    }
}

