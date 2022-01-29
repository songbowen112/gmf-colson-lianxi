package com.colson.common.bean;

/**
 * 二叉树节点
 * @param <E>
 */
public class TreeNode<E> {

    /**
     * 左节点
     */
    private transient TreeNode<E> leftTreeNode;

    /**
     * 右节点
     */
    private transient TreeNode<E> rightTreeNode;

    /**
     * 存储数据
     */
    private transient E data;

    /**
     * 是否删除
     */
    private transient boolean isDelete;

    public TreeNode() {
    }

    public TreeNode(E data) {
        this(null,null,data,false);
    }

    public TreeNode(TreeNode leftTreeNode, TreeNode rightTreeNode, E data, boolean isDelete) {
        this.leftTreeNode = leftTreeNode;
        this.rightTreeNode = rightTreeNode;
        this.data = data;
        this.isDelete = isDelete;
    }

    /**
     * 获取 左节点
     *
     * @return leftTreeNode 左节点
     */
    public TreeNode getLeftTreeNode() {
        return this.leftTreeNode;
    }

    /**
     * 设置 左节点
     *
     * @param leftTreeNode 左节点
     */
    public void setLeftTreeNode(TreeNode leftTreeNode) {
        this.leftTreeNode = leftTreeNode;
    }

    /**
     * 获取 右节点
     *
     * @return rightTreeNode 右节点
     */
    public TreeNode getRightTreeNode() {
        return this.rightTreeNode;
    }

    /**
     * 设置 右节点
     *
     * @param rightTreeNode 右节点
     */
    public void setRightTreeNode(TreeNode rightTreeNode) {
        this.rightTreeNode = rightTreeNode;
    }

    /**
     * 获取 存储数据
     *
     * @return data 存储数据
     */
    public E getData() {
        return this.data;
    }

    /**
     * 设置 存储数据
     *
     * @param data 存储数据
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * 获取 是否删除
     *
     * @return isDelete 是否删除
     */
    public boolean isIsDelete() {
        return this.isDelete;
    }

    /**
     * 设置 是否删除
     *
     * @param isDelete 是否删除
     */
    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "[leftTreeNode=" + leftTreeNode +
                ", rightTreeNode=" + rightTreeNode +
                ", data=" + data +
                ", isDelete=" + isDelete +
                ']';
    }
}
