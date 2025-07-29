import java.util.Scanner;

/*
Vlezna 2022 I kolokvium
Input:
84 44 87
79 9 67
26 2 81
57 91 59
45 92 8
55 97 80
10 99 4
13 84 28
64 6 90
93 57 23
14 49 36
50 65 12
Output:
84 79 26 57 45
10 13 64 55 93 14 50

55 97 80                    (првиот се брише)
84 44 87
79 9 67
26 2 81
57 91 59
45 92 8
10 99 4
13 84 28
64 6 90
93 57 23
14 49 36
50 65 12
Output:
84 79 26 57 45
10 13 64 55 93 14 50
*/

class GameCard implements Comparable<GameCard> {
    public int id;
    public int power;
    public int numAttacks;

    public GameCard(int id, int power, int numAttacks) {
        this.id = id;
        this.power = power;
        this.numAttacks = numAttacks;
    }

    public int importanceOfCard() {
        return power * numAttacks;
    }

    @Override
    public String toString() {
        return id + " ";
    }

    @Override
    public int compareTo(GameCard object) {
        // return Integer.compare(object.importanceOfCard(), this.importanceOfCard());
        if (this.importanceOfCard() > object.importanceOfCard())
            return -1;
        else if (this.importanceOfCard() < object.importanceOfCard())
            return 1;
        return 0;
    }
}

public class Heroes {
    public static void startHeroesGame(SLL<GameCard> firstFriendCard, SLL<GameCard> secondFriendCard) {
        SLLNode<GameCard> currentFirst = firstFriendCard.getFirst();
        SLLNode<GameCard> currentSecond = secondFriendCard.getFirst();
        SLLNode<GameCard> maxCard = currentFirst;

        // најди max од првата листа
        while (currentFirst != null) {
            if (currentFirst.element.importanceOfCard() > maxCard.element.importanceOfCard()) {
                maxCard = currentFirst;
            }
            currentFirst = currentFirst.succ;
        }

        // избриши го max од првата листа
        currentFirst = firstFriendCard.getFirst();
        if (maxCard == currentFirst) {
            firstFriendCard.deleteFirst();
        }
        while (currentFirst.succ != maxCard && currentFirst.succ != null) {
            currentFirst = currentFirst.succ;
            if (currentFirst.succ == maxCard) {
                currentFirst.succ = currentFirst.succ.succ;
            }
        }

        // најди средина на втората листа
        for (int i = 0; i < secondFriendCard.size() / 2 - 1; i++) {
            currentSecond = currentSecond.succ;
        }
        // додади го max после средината во втората листа
        SLLNode<GameCard> newNode = new SLLNode<>(maxCard.element, currentSecond.succ);
        currentSecond.succ = newNode;

        // печатење
        currentFirst = firstFriendCard.getFirst();
        while (currentFirst != null) {
            System.out.print(currentFirst.element);
            currentFirst = currentFirst.succ;
        }
        System.out.println();
        currentSecond = secondFriendCard.getFirst();
        while (currentSecond != null) {
            System.out.print(currentSecond.element);
            currentSecond = currentSecond.succ;
        }
    }

    public static void main(String[] args) {
        SLL<GameCard> firstFriendCard = new SLL<>();
        SLL<GameCard> secondFriendCard = new SLL<>();

        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 12; i++) {
            int id = in.nextInt();
            int power = in.nextInt();
            int numAttacks = in.nextInt();
            GameCard card = new GameCard(id, power, numAttacks);

            if (i < 6) {
                firstFriendCard.insertLast(card);
            }
            else {
                secondFriendCard.insertLast(card);
            }
        }
        in.close();

        startHeroesGame(firstFriendCard, secondFriendCard);
    }
}
