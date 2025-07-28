import java.util.Scanner;

/*
APS book
Input:              Output:
5                   1->5->2->4->3
1 2 3 4 5
*/

public class ReorderList2 {
    // same logic as mirror()
    public static SLLNode<Integer> reverseList (SLLNode<Integer> node) {
        SLLNode<Integer> prev = null, current = node, next;

        while (current != null) {
            next = current.succ;
            current.succ = prev;
            prev = current;
            current = next;
        }
        node = prev;
        return node;
    }

    public static void reorder (SLL<Integer> list) {
        SLLNode<Integer> middle = list.getFirst();

        // search for the middle element of the list
        for (int i = 1; i < list.size() / 2; i++)
            middle = middle.succ;
        //System.out.println("middle:" + middle.element);

        // split the list into 2 halves
        SLLNode<Integer> node1 = list.getFirst();         // node1 -> 1
        SLLNode<Integer> node2 = middle.succ;             // node2 -> 3
        middle.succ = null;

        // reverse the second half
        node2 = reverseList(node2);

        // alternately connect the nodes
        SLLNode<Integer> helper = new SLLNode<>(null, null);

        // current is the pointer to the helper node from where the new list will be formed
        SLLNode<Integer> current = helper;

        while (node1 != null || node2 != null) {
            // first add from the first list
            if (node1 != null) {
                current.succ = node1;
                current = current.succ;
                node1 = node1.succ;
            }
            // then add from the second list
            if (node2 != null) {
                current.succ = node2;
                current = current.succ;
                node2 = node2.succ;
            }
            helper = helper.succ;
        }
    }

    public static void main(String[] args) {
        SLL<Integer> list = new SLL<>();

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(input.nextInt());
        }
        input.close();
        reorder(list);
        System.out.println(list);
    }
}
