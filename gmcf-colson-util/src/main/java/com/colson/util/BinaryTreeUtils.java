package com.colson.util;

import com.colson.common.bean.TreeNode;
import com.colson.util.stack.LinkedStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author song
 * @description: 二叉树工具类
 * @date 2022/7/1 下午4:46
 */
public class BinaryTreeUtils {

    /**
     * 前序遍历（递归方式）
     * 参考文献：
     * https://blog.csdn.net/weixin_42119730/article/details/118584005
     * 根结点 -> 左孩子 -> 右孩子
     * @param root
     */
    public static void beforeOrder(TreeNode root) {
        if (null != root) {
            System.out.print(root.getData() + "->");
            beforeOrder(root.getLeftTreeNode());
            beforeOrder(root.getRightTreeNode());
        }
    }

    /**
     * 前序遍历（非递归方式）
     * 根结点 -> 左孩子 -> 右孩子
     * @param root
     */
    public static List<Integer> beforeOrder2(TreeNode root) {
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
    public static void inOrder(TreeNode root) {
        if (null != root) {
            inOrder(root.getLeftTreeNode());
            System.out.print(root.getData() + "->");
            inOrder(root.getRightTreeNode());
        }
    }

    /**
     * 中序遍历（非递归方式）
     * 左孩子 -> 根结点 -> 右孩子
     * @param root
     */
    public static List<Integer> inOrder2(TreeNode root) {
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
    public static void afterOrder(TreeNode root) {
        if (null != root) {
            afterOrder(root.getLeftTreeNode());
            afterOrder(root.getRightTreeNode());
            System.out.print(root.getData() + "->");
        }
    }

    /**
     * 后序遍历（非递归方式）
     * 左孩子 -> 右孩子 -> 根结点
     * @param root
     */
    public static List<Integer> afterOrder2(TreeNode root) {
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
