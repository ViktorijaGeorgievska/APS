import java.util.Scanner;

// APS book basics
public class BinaryTreeIntegerSumSubtree1 {
    public static int sumMinLeftSubtree(BNode<Integer> node, int value) {
        if (node == null)
            return 0;

        int tmpSum = sumMinLeftSubtree(node.left, value) + sumMinLeftSubtree(node.right, value);
        if (node.info < value)                   // доколку вред на nodes во левото поддрво е помала од вред на value-node на кој сметаме сума
            return tmpSum + node.info;           // се додава вредноста на тие nodes
        else
            return tmpSum;
    }

    public static int sumMaxRightSubTree(BNode<Integer> node, int value) {
        if (node == null)
            return 0;

        int tmpSum = sumMaxRightSubTree(node.left, value) + sumMaxRightSubTree(node.right, value);
        if (node.info > value)                  // доколку вред на nodes во десното поддрво е поголема од вред на value-node на кој сметаме сума
            return tmpSum + node.info;          // се додава вредноста на тие nodes
        else
            return tmpSum;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        BTree<Integer> tree = BinaryTreeInteger1.getIntegerTree();      // ф-ја за градење б.дрво напишана во класата BinaryTreeInteger1
        System.out.println(tree);

        int value = input.nextInt();
        BNode<Integer> node = tree.findNode(value);
        if (node != null) {
            System.out.println("The sum of the left subtree of " + value + " is: " + sumMinLeftSubtree(node.left, value));
            System.out.println("The sum of the right subtree of " + value + " is: " + sumMaxRightSubTree(node.right, value));
        } else
            System.out.println("Node with value " + value + " does NOT exist in the given binary tree!");
    }
}

