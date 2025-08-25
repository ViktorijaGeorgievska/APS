import java.util.Scanner;

// APS book basics
public class BinaryTreeInteger1 {
    public static BTree<Integer> getIntegerTree() {
        BTree<Integer> tree = new BTree<>();

        tree.makeRoot(20);
        BNode<Integer> node1 = tree.addChild(tree.root, BNode.LEFT, 18);
        BNode<Integer> node2 = tree.addChild(tree.root, BNode.RIGHT, 19);
        BNode<Integer> node3 = tree.addChild(node1, BNode.LEFT, 16);
        BNode<Integer> node4 = tree.addChild(node1, BNode.RIGHT, 14);
        BNode<Integer> node5 = tree.addChild(node2, BNode.LEFT, 17);
        BNode<Integer> node6 = tree.addChild(node2, BNode.RIGHT, 15);
        BNode<Integer> node7 = tree.addChild(node3, BNode.LEFT, 12);
        BNode<Integer> node8 = tree.addChild(node3, BNode.RIGHT, 10);
        BNode<Integer> node9 = tree.addChild(node4, BNode.LEFT, 8);
        BNode<Integer> node10 = tree.addChild(node4, BNode.RIGHT, 6);
        BNode<Integer> node11 = tree.addChild(node5, BNode.LEFT, 13);
        BNode<Integer> node12 = tree.addChild(node5, BNode.RIGHT, 11);
        BNode<Integer> node13 = tree.addChild(node6, BNode.LEFT, 9);
        BNode<Integer> node14 = tree.addChild(node6, BNode.RIGHT, 7);
        return tree;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        BTree<Integer> tree = getIntegerTree();
        System.out.println(tree);

        System.out.println("Enter value to search for in the tree: ");
        int searchValue = in.nextInt();
        BNode<Integer> searchNode = tree.findNode(searchValue);
        if (searchNode != null)
            System.out.println("Node with value " + searchValue + " exists in the given tree!");
        else
            System.out.println("Node with value " + searchValue + " does NOT exist in the given tree!");

        System.out.println("===========================================================================");

        System.out.println("Enter an integer value to get its level in the binary tree: ");
        int value = in.nextInt();
        int level = tree.getLevel(value);

        if (level != 0)
            System.out.println("Level of " + value + " is " + level);
        else
            System.out.println(value + " is not present in the binary tree!");
    }
}
