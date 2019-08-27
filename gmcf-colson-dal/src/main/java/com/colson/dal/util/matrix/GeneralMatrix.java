package com.colson.dal.util.matrix;

import com.colson.dal.util.ArraysUtils;

import com.colson.dal.common.biz.pkg.BaseDTO;

/**
 * 普通矩阵
 * [0 1 2 3]
 * [4 5 6 7]
 * 行优先存储为：[0 1 2 3 4 5 6 7]
 * 列优先存储为：[0 4 1 5 2 6 3 7]
 */
public class GeneralMatrix<E> extends BaseDTO {

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

    public GeneralMatrix() {
    }

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

        rowPriority = new Object[x*y];
        linePriority = new Object[x*y];
        for (int i=0,num=0;i<y;i++) {
            for (int j=0;j<x;j++) {
                rowPriority[num++] = data[i][j];
            }
        }
        for (int i=0,num=0;i<x;i++) {
            for (int j=0;j<y;j++) {
                linePriority[num++] = data[j][i];
            }
        }
    }

    /**
     * 获取行优先数组
     * @return
     */
    public Object[] getRowArray() {
        return rowPriority;
    }

    /**
     * 获取列优先数组
     * @return
     */
    public Object[] getLineArray() {
        return linePriority;
    }

    public int getRowIndex(int x,int y) {
        if (x>=rowNum || y>=lineNum) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(x,y));
        }
        return y*rowNum+x;

    }

    public int getLineIndex(int x,int y) {
        if (x>=rowNum || y>=lineNum) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(x,y));
        }
        return x*lineNum+y;
    }

    public void printArray() {
        //载入行数据
        System.out.print("行优先一维存储");
        System.out.print(ArraysUtils.printArr(rowPriority));

        //载入列数据
        System.out.print("列优先一维存储");
        System.out.print(ArraysUtils.printArr(linePriority));
    }

    public void printArray2() {
        //载入行数据
        System.out.print("行优先一维存储");
        System.out.print(ArraysUtils.printArr2(rowPriority));

        //载入列数据
        System.out.print("列优先一维存储");
        System.out.print(ArraysUtils.printArr2(linePriority));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<data.length;i++) {
            sb.append("[");
            for (int j=0;j<data[i].length;j++) {
                if(j==data[i].length-1) {
                    sb.append(data[i][j]);
                } else {
                    sb.append(data[i][j]+" ");
                }
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

    /**
     * 下标越界信息
     * @param x,y
     * @return
     */
    private String outOfBoundsMsg(int x,int y) {
        return "currIndex: {"+x+","+y+"}, maxIndex: {"+rowNum+","+lineNum+"}";
    }

}
