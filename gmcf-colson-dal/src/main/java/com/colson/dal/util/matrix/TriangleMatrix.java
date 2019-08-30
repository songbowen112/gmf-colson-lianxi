package com.colson.dal.util.matrix;

/**
 * 三角矩阵
 * 下三角矩阵的上三角区都是一个常数,上三角矩阵的下三角区都是一个常数
 * [1 0 0]
 * [2 6 0]
 * [3 5 8]
 */
public class TriangleMatrix {

    //矩阵宽度
    private int rowNum = 0;

    //矩阵长度
    private int lineNum = 0;

    private Object[][] data;

    /**
     * 行优先存储
     */
    private Object[] rowPriority;

    /**
     * 列优先存储
     */
    private Object[] linePriority;

    /**
     * 下标越界信息
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: ";
    }
}
