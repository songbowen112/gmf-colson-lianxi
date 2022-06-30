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

    private TreeNode<Integer> root;

    public BinaryTree() {
    }

    public BinaryTree(TreeNode root) {
        this.root = root;
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
            parentNode.setLeftTreeNode(nextTreeNode);
        } else if (null == curr.getLeftTreeNode() && null != curr.getRightTreeNode()) {
            TreeNode nextTreeNode = curr.getRightTreeNode();
            if (curr == root) {
                root = nextTreeNode;
            } else if (isLeftNode) {
                parentNode.setLeftTreeNode(nextTreeNode);
            } else {
                parentNode.setRightTreeNode(nextTreeNode);
            }
        }

        //3 当前节点有两个子节点
        if (null != curr.getLeftTreeNode() && null != curr.getRightTreeNode()) {
            TreeNode successor = getSuccessor(curr);
            //获取删除节点的后继节点
            if (curr == root) {
                root = successor;
            } else if (isLeftNode) {
                parentNode.setLeftTreeNode(successor);
            } else {
                parentNode.setRightTreeNode(successor);
            }
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

    /**
     * 前序遍历（递归方式）
     * 参考文献：
     * https://blog.csdn.net/weixin_42119730/article/details/118584005
     * 根结点 -> 左孩子 -> 右孩子
     * @param root
     */
    @Override
    public List<Integer> beforeOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode<Integer> curr = root;
        if (null != curr) {
            result.add(curr.getData());
            System.out.print(curr.getData() + "->");
            beforeOrder(curr.getLeftTreeNode());
            beforeOrder(curr.getRightTreeNode());
        }
        return result;
    }

    /**
     * 前序遍历（非递归方式）
     * 根结点 -> 左孩子 -> 右孩子
     * @param root
     */
    public List<Integer> beforeOrder2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        LinkedStack<TreeNode> linkedStack = new LinkedStack<>();
        TreeNode<Integer> curr = root;
        while (null != curr || linkedStack.size() > 0) {
            if (null != curr) {
                result.add(curr.getData());
                System.out.print(curr.getData() + "->");
                linkedStack.push(curr);
                curr = curr.getLeftTreeNode();
            } else {
                TreeNode pop = linkedStack.pop();
                curr = pop.getRightTreeNode();
            }
        }
        return result;
    }

    /**
     * 中序遍历（递归方式）
     * 左孩子 -> 根结点 -> 右孩子
     * @param root
     */
    @Override
    public List<Integer> inOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode<Integer> curr = root;
        if (null != curr) {
            inOrder(curr.getLeftTreeNode());
            result.add(curr.getData());
            System.out.print(curr.getData() + "->");
            inOrder(curr.getRightTreeNode());
        }
        return result;
    }

    /**
     * 中序遍历（非递归方式）
     * 左孩子 -> 根结点 -> 右孩子
     * @param root
     */
    public List<Integer> inOrder2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        LinkedStack<TreeNode> linkedStack = new LinkedStack<>();
        TreeNode<Integer> curr = root;
        while (null != curr || linkedStack.size() > 0) {
            if (null != curr) {
                linkedStack.push(curr);
                curr = curr.getLeftTreeNode();
            } else {
                TreeNode<Integer> pop = linkedStack.pop();
                result.add(pop.getData());
                System.out.print(pop.getData() + "->");
                curr = pop.getRightTreeNode();
            }
        }
        return result;
    }

    /**
     * 后序遍历（递归方式）
     * 左孩子 -> 右孩子 -> 根结点
     * @param root
     */
    @Override
    public List<Integer> afterOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode<Integer> curr = root;
        if (null != curr) {
            afterOrder(curr.getLeftTreeNode());
            afterOrder(curr.getRightTreeNode());
            result.add(curr.getData());
            System.out.print(curr.getData() + "->");
        }
        return result;
    }

    /**
     * 后序遍历（非递归方式）
     * 左孩子 -> 右孩子 -> 根结点
     * @param root
     */
    public List<Integer> afterOrder2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        LinkedStack<TreeNode> linkedStack = new LinkedStack<>();
        TreeNode<Integer> curr = root;
        TreeNode<Integer> cur, pre = null;
        linkedStack.push(curr);
        while (linkedStack.size() > 0 ) {
            cur = linkedStack.peek();
            if ((cur.getLeftTreeNode() == null && cur.getRightTreeNode() == null) || (pre != null && (pre == cur.getLeftTreeNode() || pre == cur.getRightTreeNode()))) {
                result.add(cur.getData());
                System.out.print(cur.getData() + "->");
                linkedStack.pop();
                pre = cur;
            } else {
                if (cur.getRightTreeNode() != null)
                    linkedStack.push(cur.getRightTreeNode());
                if (cur.getLeftTreeNode() != null)
                    linkedStack.push(cur.getLeftTreeNode());
            }
        }
        return result;
    }

    /**
     * 直观打印二叉树
     * // ------ Output ------
     * /*
     * The binary tree is:
     *       2
     *     /   \
     *   3       5
     *  / \     / \
     * 7   8   1   3
     *
     */
    public static void print(TreeNode root) {
        System.out.println("The binary tree is:");
        if (root == null) {
            System.out.println("Empty binary tree!");
        }
        int height = getHeight(root);
        int arrHeight = height * 2 - 1;
        int arrWidth = (2 << (height - 2)) * 3 + 1;
        String[][] r = new String[arrHeight][arrWidth];
        for (int i = 0; i < arrHeight; i++) {
            for (int j = 0; j < arrWidth; j++) {
                r[i][j] = " ";
            }
        }
        writeToArray(height, root, r, 0, arrWidth / 2);
        for (String[] line : r) {
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                strBuilder.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            System.out.println(strBuilder);
        }
    }

    private static int getHeight(TreeNode root) {
        return root == null ? 0 : (1 + Math.max(getHeight(root.getLeftTreeNode()), getHeight(root.getRightTreeNode())));
    }

    private static void writeToArray(int height, TreeNode node, String[][] r, int i, int j) {
        if (node == null) {
            return;
        }
        r[i][j] = String.valueOf(node.getData());
        int curLevel = (i + 1) >> 1;
        if (curLevel == height) {
            return;
        }
        int gap = height - curLevel - 1;
        if (node.getLeftTreeNode() != null) {
            r[i + 1][j - gap] = "/";
            writeToArray(height, node.getLeftTreeNode(), r, i + 2, j - gap * 2);
        }
        if (node.getRightTreeNode() != null) {
            r[i + 1][j + gap] = "\\";
            writeToArray(height, node.getRightTreeNode(), r, i + 2, j + gap * 2);
        }
    }

}
