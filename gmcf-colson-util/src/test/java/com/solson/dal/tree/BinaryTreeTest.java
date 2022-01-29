package com.solson.dal.tree;


import com.colson.common.bean.TreeNode;
import com.colson.util.tree.BinaryTree;

public class BinaryTreeTest {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.setLeftTreeNode(new TreeNode(7));
        root.getLeftTreeNode().setLeftTreeNode(new TreeNode(4));
        root.getLeftTreeNode().setRightTreeNode(new TreeNode(8));
        root.setRightTreeNode(new TreeNode(15));
        root.getRightTreeNode().setLeftTreeNode(new TreeNode(11));
        BinaryTree tree = new BinaryTree(root);

        System.out.println(tree.find(11));
    }





}
