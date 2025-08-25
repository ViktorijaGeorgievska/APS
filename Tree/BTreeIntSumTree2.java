
/*
APS book
Output: The given tree is a SumTree
*/

public class BTreeIntSumTree2 {
    public static BTree<Integer> getIntegerTree() {
        BTree<Integer> tree = new BTree<>();

        tree.makeRoot(26);
        BNode<Integer> node1 = tree.addChild(tree.root, BNode.LEFT, 10);
        BNode<Integer> node2 = tree.addChild(tree.root, BNode.RIGHT, 3);
        BNode<Integer> node3 = tree.addChild(node1, BNode.LEFT, 4);
        BNode<Integer> node4 = tree.addChild(node1, BNode.RIGHT, 6);
        BNode<Integer> node5 = tree.addChild(node2, BNode.RIGHT, 3);
        return tree;
    }

    public static int isSumTree(BNode<Integer> node) {
        if (node == null)
            return 0;

        int leftSum = isSumTree(node.left);
        int rightSum = isSumTree(node.right);

        if (node.left == null && node.right == null)
            return node.info;
        if (node.info == leftSum + rightSum)
            return node.info + leftSum + rightSum;
        else
            return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        BTree<Integer> tree = getIntegerTree();

        if (isSumTree(tree.root) != Integer.MIN_VALUE)
            System.out.println("The given tree is a SumTree");
        else
            System.out.println("The given tree is NOT a SumTree");
    }
}
