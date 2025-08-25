
// APS book basic
public class BinaryTreeString1 {

    public static BTree<String> getStringTree() {
        BTree<String> tree = new BTree<>();

        tree.makeRoot("apple");
        BNode<String> node1 = tree.addChild(tree.root, BNode.LEFT, "pear");
        BNode<String> node2 = tree.addChild(tree.root, BNode.RIGHT, "banana");

        BNode<String> node3 = tree.addChild(node1, BNode.LEFT, "peach");
        BNode<String> node4 = tree.addChild(node2, BNode.RIGHT, "orange");
        BNode<String> node5 = tree.addChild(node3, BNode.LEFT, "lemon");
        BNode<String> node6 = tree.addChild(node3, BNode.RIGHT, "cherry");
        return tree;
    }

    public static void main(String[] args) {
        BTree<String> binaryTree = getStringTree();
        System.out.println(binaryTree);
        binaryTree.inorder();
    }
}
