package com.solson.dal.tree;


import com.colson.common.bean.TreeNode;
import com.colson.util.BinaryTreeUtils;
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
        BinaryTreeUtils.print(root);
        System.out.println(tree.count);

        System.out.println(tree.find(2));
        System.out.println("前序遍历：");
        BinaryTreeUtils.beforeOrder(root);
        System.out.println();
        BinaryTreeUtils.beforeOrder2(root);
        System.out.println("\n中序遍历：");
        BinaryTreeUtils.inOrder(root);
        System.out.println();
        BinaryTreeUtils.inOrder2(root);
        System.out.println("\n后序遍历：");
        BinaryTreeUtils.afterOrder(root);
        System.out.println();
        BinaryTreeUtils.afterOrder2(root);
        System.out.println();

        System.out.println(tree.delete(10));
        BinaryTreeUtils.print(root);
        System.out.println(tree.count);

    }

}
