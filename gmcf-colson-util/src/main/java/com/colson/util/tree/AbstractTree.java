package com.colson.util.tree;

import com.colson.common.bean.TreeNode;

import java.util.List;

/**
 * @author song
 * @description: 树抽象类
 * @date 2022/6/28 下午3:37
 */
public abstract class AbstractTree<E> {

    public int count = 0;

    /**
     * 查询
     * @param key
     * @return
     */
    public abstract TreeNode<E> find(E key);

    /**
     * 插入
     * @param data
     * @return
     */
    public abstract boolean insert(E data);

    /**
     * 删除
     * @param data
     * @return
     */
    public abstract boolean delete(E data);

    /**
     * 节点个数
     * @return
     */
    public int count() {
        return this.count;
    }

    /**
     * 前序遍历
     * 根结点 -> 左孩子 -> 右孩子
     * @param root
     */
    public abstract List<E> beforeOrder(TreeNode root);

    /**
     * 中序遍历
     * 左孩子 -> 根结点 -> 右孩子
     * @param root
     */
    public abstract List<E> inOrder(TreeNode root);

    /**
     * 后序遍历
     * 左孩子 -> 右孩子 -> 根结点
     * @param root
     */
    public abstract List<E> afterOrder(TreeNode root);

}
