package com.colson.util.tree;


import com.colson.common.bean.TreeNode;
import com.colson.util.stack.LinkedStack;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName:
 * @Description: 二叉树的定义
 *               7
 *            4     9
 *          3     8  10
 * @author
 * @date 2019-7-23 晚上21:32:49
 */
public class BinaryTree extends AbstractTree<Integer> {

    transient TreeNode<Integer> root;

    public BinaryTree() {
    }

    public BinaryTree(TreeNode root) {
        this.root = root;
        this.count = findNodeNum(root);

    }

    private int findNodeNum(TreeNode node) {
        int count = 0;
        TreeNode curr = node;
        LinkedStack<TreeNode> linkedStack = new LinkedStack<>();
        while (null != curr || linkedStack.size() > 0) {
            if (null != curr) {
                linkedStack.push(curr);
                curr = curr.getLeftTreeNode();
                count++;
            } else {
                TreeNode pop = linkedStack.pop();
                curr = pop.getRightTreeNode();
            }
        }
        return count;
    }

    @Override
    public TreeNode find(Integer key) {
        TreeNode<Integer> curr = root;
        while (null != curr) {
            if (curr.getData() > key) {
                //当前值比查找值大 则继续遍历左子树
                curr = curr.getLeftTreeNode();
            } else if (curr.getData() < key) {
                //当前值小于查找值 则继续遍历右子树
                curr = curr.getRightTreeNode();
            } else {
                //查找到 返回
                return curr;
            }
        }
        //查不到返回null
        return null;
    }

    @Override
    public boolean insert(Integer data) {
        this.count++;
        //如果第一个节点为空 设置为第一个节点
        TreeNode<Integer> newNode = new TreeNode<>(data);
        if (null == root) {
            root = newNode;
            return true;
        }
        TreeNode<Integer> curr = root;
        TreeNode parentNode = null;
        while (null != curr) {
            parentNode = curr;

            if (curr.getData() > data) {
                //当前值比查找值大 则继续遍历左子树
                curr = curr.getLeftTreeNode();
                if (null == curr) {
                    parentNode.setLeftTreeNode(newNode);
                    return true;
                }
            } else {
                //当前值小于查找值 则继续遍历右子树
                curr = curr.getRightTreeNode();
                if (null == curr) {
                    parentNode.setRightTreeNode(newNode);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 删除共三种情况
     * 1 该节点是叶子节点
     * 2 该节点有一个叶子节点
     * 3 该节点有两个叶子节点
     *
     * @param data
    */
    @Override
    public boolean delete(Integer data) {
        TreeNode<Integer> curr = root;
        TreeNode parentNode = null;
        //当前节点是否为左节点
        boolean isLeftNode = false;

        //定位data的位置
        while (curr.getData() != data) {
            parentNode = curr;
            if (curr.getData() > data) {
                curr = curr.getLeftTreeNode();
                isLeftNode = true;
            } else {
                curr = curr.getRightTreeNode();
                isLeftNode = false;
            }

            if (null == curr) {
                return false;
            }
        }
        this.count--;

        //1 此节点为叶子节点
        if (null == curr.getLeftTreeNode() && null == curr.getRightTreeNode()) {
            if (curr == root) {
                root = null;
            }
            if (isLeftNode) {
                //如果要删除的节点为父节点的左节点 把父节点的左节点置为空
                parentNode.setLeftTreeNode(null);
            } else {
                parentNode.setRightTreeNode(null);
            }
            return true;
        }

        //2 当前节点有一个子节点
        if (null != curr.getLeftTreeNode() && null == curr.getRightTreeNode()) {
            TreeNode nextTreeNode = curr.getLeftTreeNode();
            if (curr == root) {
                root = nextTreeNode;
            } else if (isLeftNode) {
                parentNode.setLeftTreeNode(nextTreeNode);
            } else {
                parentNode.setRightTreeNode(nextTreeNode);
            }
            return true;
        } else if (null == curr.getLeftTreeNode() && null != curr.getRightTreeNode()) {
            TreeNode nextTreeNode = curr.getRightTreeNode();
            if (curr == root) {
                root = nextTreeNode;
            } else if (isLeftNode) {
                parentNode.setLeftTreeNode(nextTreeNode);
            } else {
                parentNode.setRightTreeNode(nextTreeNode);
            }
            return true;
        }

        //3 当前节点有两个子节点
        if (null != curr.getLeftTreeNode() && null != curr.getRightTreeNode()) {
            //获取删除节点的后继节点
            TreeNode successor = getSuccessor(curr);

            if (curr == root) {
                root = successor;
            } else if (isLeftNode) {
                parentNode.setLeftTreeNode(successor);
            } else {
                parentNode.setRightTreeNode(successor);
            }
            return true;
        }
        return false;
    }

    /**
     * 获取要删除节点的后继节点（重点）
     *
     * @param delNode
     * @return
     */
    private TreeNode getSuccessor(TreeNode delNode) {

        TreeNode successorParent = delNode;
        TreeNode successor = delNode;
        TreeNode curr = delNode.getRightTreeNode();
        while (curr != null) {
            successorParent = successor;
            successor = curr;
            curr = curr.getLeftTreeNode();
        }
        if (successor != delNode.getRightTreeNode()) {
            successorParent.setLeftTreeNode(successor.getRightTreeNode());
            successor.setRightTreeNode(delNode.getRightTreeNode());
        }
        return successor;
    }

}
