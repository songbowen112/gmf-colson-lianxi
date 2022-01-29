package com.colson.util.matrix;


import com.colson.common.pkg.BaseDTO;
import com.colson.util.ArraysUtils;

/**
 * 三角矩阵
 * 下三角矩阵的上三角区都是一个常数,上三角矩阵的下三角区都是一个常数
 * [1 0 0 0 0]
 * [2 3 0 0 0]
 * [3 4 5 0 0]
 * [4 5 6 7 0]
 * [5 6 7 8 9]
 * 下三角行优先->[1 2 3 3 4 5 4 5 6 7 5 6 7 8 9 0]
 * 下三角列优先->[1 2 3 4 5 3 4 5 6 5 6 7 7 8 9 0]
 */
public class TriangleMatrix extends BaseDTO {

    //矩阵边长
    private int length = 0;

    private Object[][] data;

    /**
     * 下三角行优先
     */
    private Object[] rowPriority;

    /**
     * 下三角列优先
     */
    private Object[] linePriority;

    public void init(int length, int constant) {
        this.length = length;

        data = new Object[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (j > i) {
                    data[i][j] = constant;
                } else {
                    data[i][j] = i + j + 1;
                }
            }
        }

        //下三角行优先
        rowPriority = new Object[length*(length+1)/2 + 1];//等差数列求和公式Sn = 1+2+3+...+(n-1)+n = n(n+1)/2
        for (int i=0,num=0;i<length;i++) {
            for (int j=0;j<=i;j++) {
                rowPriority[num++] = data[i][j];
            }
        }
        rowPriority[rowPriority.length-1] = constant;

        //下三角列优先
        linePriority = new Object[length*(length+1)/2 + 1];//等差数列求和公式Sn = 1+2+3+...+(n-1)+n = n(n+1)/2
        for (int j=0,num=0;j<length;j++) {
            for (int i=j;i<length;i++) {
                linePriority[num++] = data[i][j];
            }
        }
        linePriority[linePriority.length-1] = constant;
    }

    /**
     * 获取下三角行优先数组
     * @return
     */
    public Object[] getRowPriority() {
        return rowPriority;
    }

    /**
     * 获取下三角列优先数组
     * @return
     */
    public Object[] getLinePriority() {
        return linePriority;
    }

    //下三角行优先
    public int getRowIndex(int i,int j) {
        if (i>=length || j>=length) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(i,j));
        }
        if (i >= j) {
            return i*(i-1)/2+j+i;//矩阵下标从0,0开始存储
        }
        return rowPriority.length-1;
    }

    //下三角列优先
    public int getLineIndex(int i,int j) {
        if (i>=length || j>=length) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(i,j));
        }
        if (i >= j) {
            return (2*length+1-j)*j/2+i-j;//矩阵下标从0,0开始存储
        }
        return linePriority.length-1;
    }

    public void printArray() {
        System.out.print("下三角行优先->"+ ArraysUtils.printArr(rowPriority));
        System.out.print("下三角列优先->"+ ArraysUtils.printArr(linePriority));
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
     * @param i,y
     * @return
     */
    private String outOfBoundsMsg(int i,int j) {
        return "currIndex: {"+i+","+j+"}, maxIndex: {"+length+","+length+"}";
    }
}
