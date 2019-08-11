package com.colson.dal.util.matrix;

import java.io.Serializable;

/**
 * 对称矩阵
 */
public class SymmetryMatrix<E> implements Serializable {

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

    public SymmetryMatrix() {
    }

    public void init(int length) {
        data = new Object[length][length];
        for (int i=0;i<length;i++) {
            for (int j=0;j<length;j++) {
                data[i][j] = i+j;
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<data.length;i++) {
            for (int j=0;j<data[i].length;j++) {
                sb.append(data[i][j]+" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * 下标越界信息
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+rowNum*lineNum;
    }
}
