package com.colson.util.tree;


import com.colson.common.bean.TreeNode;

/**
 *
 * @ClassName:
 * @Description: 二叉树的定义
 * @author
 * @date 2019-7-23 晚上21:32:49
 */
public class BinaryTree {

    private TreeNode root;

    public BinaryTree() {
    }

    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    /**
     * 查找
     * @param key
     * @return
     */
    public TreeNode find(int key) {
        TreeNode curr = root;
        if (null == curr) {
            return null;
        }

        while (true) {
            if (curr != null) {
                if ((int)curr.getData()>key) {
                    curr = curr.getLeftTreeNode();
                } else if ((int)curr.getData()<key) {
                    curr = curr.getRightTreeNode();
                } else if ((int)curr.getData()==key) {
                    break;
                }
            } else {
                break;
            }
        }
        return curr;

    }

    /**
     * 插入操作
     * @param value
     */
    public void insert(int value) {

    }

    /**
     * 中序遍历
     * @param treeNode
     */
    public void inOrder(TreeNode treeNode) {

    }

}
