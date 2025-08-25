import java.util.Scanner;

// APS book basics
public class BinaryTreeIntegerSum1 {
    public static int sumOfBT(BNode<Integer> node) {
        if (node == null)
            return 0;
        return (node.info + sumOfBT(node.left) + sumOfBT(node.right));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        BTree<Integer> tree = BinaryTreeInteger1.getIntegerTree();      // ф-ја за градење б.дрво напишана во класата BinaryTreeInteger1
        System.out.println(tree);

        int sum = sumOfBT(tree.root);
        System.out.println("Sum of all elements in the tree is " + sum);
    }
}
