package BinaryTree;
// сумата на степените во поддрвото на избран јазол + метода find

import java.util.Scanner;

public class BinaryTreeLab {
    public static BNode<String> find (BNode<String> current, String target) {
        if (current == null)
            return null;

        if (current.info.equals(target))
            return current;

        BNode<String> leftNode = null;
        BNode<String> rightNode = null;
        if (current.left != null)
            leftNode = find(current.left, target);
        if (current.right != null)
            rightNode = find(current.right, target);

        if (leftNode != null)
            return leftNode;
        if (rightNode != null)
            return rightNode;
        return null;
    }

    public static int sumSubtrees (BNode<String> current) {
        if (current == null)
            return 0;

        int degree = 0;
        if (current.left != null)
            degree++;
        if (current.right != null)
            degree++;

        return degree + sumSubtrees(current.left) + sumSubtrees(current.right);
    }

    public static void main(String[] args) {
        BTree<String> tree = new BTree<>();

        Scanner input = new Scanner(System.in);
        String[] lines = input.nextLine().split(" ");

        int N = Integer.parseInt(lines[0]);
        int Q  = Integer.parseInt(lines[1]);
        for (int i = 0; i < N + Q; i++) {
            String[] commands = input.nextLine().split(" ");
            if (commands[0].equals("root")) {
                String root = commands[1];
                tree.makeRoot(root);
                BNode<String> rootNode = tree.root;
            } else if (commands[0].equals("add")) {
                String parentName = commands[1];
                String childName = commands[2];
                String position = commands[3];

                BNode<String> parentNode = find(tree.root, parentName);
                if (position.equals("LEFT"))
                    tree.addChild(parentNode, BNode.LEFT, childName);
                if (position.equals("RIGHT"))
                    tree.addChild(parentNode, BNode.RIGHT, childName);
            } else if (commands[0].equals("ask")) {
                String nodeName = commands[1];
                BNode<String> findNode = find(tree.root, nodeName);

                System.out.println(sumSubtrees(findNode));
            }
        }
    }
}
