
/*
APS book
Output: The sum is 26 (19 + 7) - nodes with only left child
*/

public class BTreeIntSumLeftChildren2 {
    public static BTree<Integer> getIntegerTree() {
        BTree<Integer> tree = new BTree<>();

        tree.makeRoot(1);
        BNode<Integer> node1 = tree.addChild(tree.root, BNode.LEFT, 7);
        BNode<Integer> node2 = tree.addChild(tree.root, BNode.RIGHT, 9);
        BNode<Integer> node3 = tree.addChild(node1, BNode.LEFT, 2);
        BNode<Integer> node4 = tree.addChild(node2, BNode.RIGHT, 19);
        BNode<Integer> node5 = tree.addChild(node4, BNode.LEFT, 8);
        return tree;
    }

    public static int sumLeftChildren(BNode<Integer> node) {
        if (node == null)
            return 0;

        int sum = 0;
        if (node.left != null && node.right == null)
            sum += node.info;

        sum += sumLeftChildren(node.left);
        sum += sumLeftChildren(node.right);

        return sum;
    }

    public static void main(String[] args) {
        BTree<Integer> tree = getIntegerTree();

        System.out.println("The sum is " + sumLeftChildren(tree.root));
    }
}
