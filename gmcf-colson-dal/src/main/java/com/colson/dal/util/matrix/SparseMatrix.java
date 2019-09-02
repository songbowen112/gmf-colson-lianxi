package com.colson.dal.util.matrix;

/**
 * 稀疏矩阵
 */
public class SparseMatrix {

    //矩阵宽度
    private int rowNum = 0;

    //矩阵长度
    private int lineNum = 0;

    private Object[][] data;

    /**
     * 初始化数据
     * @param x
     * @param y
     */
    public void init(int x,int y) {
        this.rowNum = x;
        this.lineNum = y;

        data = new Object[y][x];
        for (int i=0,num=0;i<y;i++) {
            for (int j=0;j<x;j++) {
                data[i][j] = num++;
            }
        }
    }
}
