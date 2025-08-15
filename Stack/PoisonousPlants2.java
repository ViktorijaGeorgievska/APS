import java.util.Scanner;

/*
APS book
Input:
7
6 5 8 4 7 10 9
Output:
2

Input:
5
1 1 1 1 1
Output:
0
*/

class Plant {
    int pesticide;
    int days;

    public Plant(int pesticide, int days) {
        this.pesticide = pesticide;
        this.days = days;
    }
}

public class PoisonousPlants2 {
    public static void checkPlant(SLL<Integer> list) {
        ArrayStack<Plant> stack = new ArrayStack<>(50);
        SLLNode<Integer> current = list.getFirst();
        int maxDays = 0;

        while (current != null) {
            int daysToDie = 0;                                                    // стартна вредност: претпоставка дека нема да умре

            // проверуваме додека има растенија на стек и тековното има повеќе пестициди од врвот
            while (!stack.isEmpty() && current.element > stack.peek().pesticide) {
                daysToDie = Math.max(daysToDie, stack.peek().days + 1);           // земаме максимум помеѓу моменталниот daysToDie и (денот на врвот + 1)
                stack.pop();                                                      // го вадиме врвот од стекот (не е релевантен за следните)
                // System.out.println("in while: " + stack.pop().pesticide);
            }

            stack.push(new Plant(current.element, daysToDie));
            maxDays = Math.max(maxDays, daysToDie);

            current = current.succ;
        }
        System.out.println(maxDays);
    }

    public static void main(String[] args) {
        SLL<Integer> list = new SLL<>();

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++)
            list.insertLast(input.nextInt());
        input.close();

        checkPlant(list);
    }
}




