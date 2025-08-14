import java.util.Scanner;

/*
APS book
Input:
H H O H H O H H O H H H H H O H O H O O H O O H H H
Output:
8  
H  
0
*/

public class Molecules2 {
    public static void makeWater(SLL<String> letters) {
        ArrayStack<String> stackH = new ArrayStack<>(50);
        ArrayStack<String> stackO = new ArrayStack<>(50);

        SLLNode<String> current = letters.getFirst();
        int numMolecules = 0;

        while (current != null) {
            if (current.element.equals("H"))
                stackH.push(current.element);
            else if (current.element.equals("O"))
                stackO.push(current.element);

            if (stackH.size() >= 2 && !stackO.isEmpty()) {
                stackH.pop();
                stackH.pop();
                stackO.pop();
                numMolecules++;
            }
            current = current.succ;
        }
        
        System.out.println(numMolecules);
        if (!stackH.isEmpty())
            while (!stackH.isEmpty())
                System.out.println(stackH.pop());
        if (!stackO.isEmpty())
            while (!stackO.isEmpty())
                System.out.println(stackO.pop());
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        SLL<String> letters = new SLL<>();
        String[] line = input.nextLine().split(" ");
        for (int i = 0; i < line.length; i++)
            letters.insertLast(line[i]);
        input.close();

        makeWater(letters);
    }
}
