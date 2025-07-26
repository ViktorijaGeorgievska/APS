/*
Потребно е да се направи компjутерска апликациjа со коjа ´ке се забрза работењето на една аптека. Притоа апликациjата треба да му овозможи на корисникот
(фармацевтот) брзо да пребарува низ огромното множество со лекови кои се внесени во системот. Начинот на коj тоj треба да пребарува е следен: доволно е да
ги внесе првите 3 букви од името на лекот за да може да се прикаже листа од лекови кои ги има во системот.
Работата на фармацевтот е да провери дали внесениот лек го има во системот и да му даде информациjа на клиентот. Информациjата што треба да му jа даде
на клиентот е дали лекот се нао´га на позитивната листа на лекови, коjа е цената и колку парчиња од лекот има на залиха. Доколку лекот постои, клиентот го
нарачува со што кажува колку парчиња ´ке купи. Оваа акциjа фармацевтот треба да jа евидентира во системот (односно да jа намали залихата на лекови за онолку
парчиња колку што му издал на клиентот). Доколку нарачката на клиентот е поголема од залихата на лекот што jа има во системот, не се презема никаква акциjа.

Влез: Од стандарден влез прво се чита броj 𝑁 коj претставува броj на лекови кои ´ке бидат внесени во системот. Во наредните 𝑁 реда се дадени имињата на
лековите, дали ги има на позитивната листа (1/0), цената и броj на парчиња, сите разделени со по едно празно место. Потоа се дадени редови со имиња на лекови
и броj на парчиња нарачани од клиентот. За означување на краj се наведува зборот „END”.

Излез: На стандарден излез треба да се испечати за секоj од влезовите следната информациjа: ИМЕ POS/NEG ЦЕНА КОЛИЧИНА. Доколку лекот не е
наjден се печати „No such drug”. Доколку нарачката на клиентот е поголема од залихата се печати „No drugs available”, инаку „Order made”.

Забелешка: Функциjата со коjа се врши мапирање на имињата на лековите во броj е следна:
ℎ(𝑤) = (100*(100*(100*0+ASCII(𝑐_3))+ASCII(𝑐_2))+ASCII(𝑐_1))%656565, каде зборот 𝑤 = 𝑐1𝑐2𝑐3𝑐4𝑐5 . . . е составен само од големи букви.
Исто така, за лековите да се направи посебна класа коjа како атрибути ´ке ги има наведените карактеристики на лекот во системот.

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
