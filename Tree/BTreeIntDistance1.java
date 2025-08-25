import java.util.Scanner;

// APS book basics
public class BTreeIntDistance1 {
    // lowest common ancestor - методата за наоѓање на најдолен заеднички предок на два јазли дадени со соодветните вредности
    private static BNode<Integer> findLCA(BNode<Integer> node, int firstValue, int secondValue) {
        if (node == null)
            return null;

        // Check if the current node's info matches either of the two values
        if (node.info.equals(firstValue) || node.info.equals(secondValue))
            return node;                // This node is LCA

        // Recursively search for the LCA in the left and right subtrees
        BNode<Integer> leftLCA = findLCA(node.left, firstValue, secondValue);
        BNode<Integer> rightLCA = findLCA(node.right, firstValue, secondValue);

        if (leftLCA != null && rightLCA != null)
            return node;               // LCA is the root of the subtrees

        if (leftLCA == null && rightLCA == null)
            return null;               // not found in either subtree

        if (leftLCA != null)
            return leftLCA;
        else
            return rightLCA;
    }

    // Returns the distance between firstNode and secondNode in the binary tree
    public static int getDistance(BTree<Integer> tree, int firstNode, int secondNode) {
        // Find the Lowest Common Ancestor (LCA) of firstNode and secondNode
        BNode<Integer> LCANode = findLCA(tree.root, firstNode, secondNode);

        // Calculate the level (depth) of firstNode and secondNode with respect to the LCA
        int d1 = tree.getLevelR(LCANode, firstNode, 0);
        int d2 = tree.getLevelR(LCANode, secondNode, 0);

        return d1 + d2;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        BTree<Integer> tree = BinaryTreeInteger1.getIntegerTree();
        System.out.println(tree);

        int firstNode = input.nextInt();
        int secondNode = input.nextInt();
        int distance = getDistance(tree, firstNode, secondNode);
        if (distance != 0)
            System.out.println("Dist(" + firstNode + ", " + secondNode + ") = " + distance);
        else
            System.out.println("Some of the values are not present in the tree");
    }
}

