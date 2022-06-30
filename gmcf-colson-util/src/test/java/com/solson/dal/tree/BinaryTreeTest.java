package com.solson.dal.tree;


import com.colson.common.bean.TreeNode;
import com.colson.util.tree.BinaryTree;

/**
 * 测试二叉树遍历
 */
public class BinaryTreeTest {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        BinaryTree tree = new BinaryTree(root);
        tree.insert(9);
        tree.insert(10);
        tree.insert(2);
        tree.insert(1);
        tree.insert(5);
        tree.insert(6);
        tree.insert(3);
        tree.print(root);

        System.out.println(tree.find(2));
        System.out.println("前序遍历：");
        tree.beforeOrder(root);
        System.out.println();
        tree.beforeOrder2(root);
        System.out.println("\n中序遍历：");
        tree.inOrder(root);
        System.out.println();
        tree.inOrder2(root);
        System.out.println("\n后序遍历：");
        tree.afterOrder(root);
        System.out.println();
        tree.afterOrder2(root);
        System.out.println();
    }

}
