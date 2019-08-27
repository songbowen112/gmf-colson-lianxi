package com.colson.dal.util.matrix;

import com.colson.dal.util.ArraysUtils;

import com.colson.dal.common.biz.pkg.BaseDTO;

/**
 * 对称矩阵(必须是正方形),只需要存储一半数据跟对角线上的数据
 * 行优先存储跟列优先存储相同
 * [0 1 2]
 * [1 2 3]
 * [2 3 4]
 * 下三角行优先存储为：[0 1 2 2 3 4]
 */
public class SymmetryMatrix<E> extends BaseDTO {

    //矩阵边长
    private int length = 0;

    private Object[][] data;

    /**
     * 上三角行优先=(下三角列优先)
     */
    private Object[] array1;

    /**
     * 下三角行优先=(上三角列优先)
     */
    private Object[] array2;

    /**
     * 上三角列优先
     */
    private Object[] array3;

    /**
     * 下三角列优先
     */
    private Object[] array4;


    public SymmetryMatrix() {
    }

    public void init(int length) {
        this.length = length;

        data = new Object[length][length];
        for (int i=0;i<length;i++) {
            for (int j=0;j<length;j++) {
                data[i][j] = i+j;
            }
        }

        //上三角行优先
        array1 = new Object[length*(length+1)/2];//等差数列求和公式Sn = 1+2+3+...+(n-1)+n = n(n+1)/2
        for (int i=0,num=0;i<length;i++) {
            for (int j=i;j<length;j++) {
                array1[num++] = data[i][j];
            }
        }

        //下三角行优先
        array2 = new Object[length*(length+1)/2];//等差数列求和公式Sn = 1+2+3+...+(n-1)+n = n(n+1)/2
        for (int j=0,num=0;j<length;j++) {
            for (int i=0;i<=j;i++) {
                array2[num++] = data[i][j];
            }
        }

        //上三角列优先
        array3 = new Object[length*(length+1)/2];//等差数列求和公式Sn = 1+2+3+...+(n-1)+n = n(n+1)/2
        for (int i=0,num=0;i<length;i++) {
            for (int j=0;j<=i;j++) {
                array3[num++] = data[i][j];
            }
        }

        //下三角列优先
        array4 = new Object[length*(length+1)/2];//等差数列求和公式Sn = 1+2+3+...+(n-1)+n = n(n+1)/2
        for (int j=0,num=0;j<length;j++) {
            for (int i=j;i<length;i++) {
                array4[num++] = data[i][j];
            }
        }
    }

    /**
     * 获取上三角行优先数组
     * @return
     */
    public Object[] getArray1() {
        return array1;
    }

    /**
     * 获取下三角行优先数组
     * @return
     */
    public Object[] getArray2() {
        return array2;
    }

    /**
     * 获取上三角列优先数组
     * @return
     */
    public Object[] getArray3() {
        return array3;
    }

    /**
     * 获取下三角列优先数组
     * @return
     */
    public Object[] getArray4() {
        return array4;
    }

    //下三角行优先
    public int getRowIndex(int x,int y) {
        if (x>=length || y>=length) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(x,y));
        }
        if (x >= y) {
            return x*(x-1)/2+y+x;//矩阵下标从0,0开始存储
        }
        return y*length+x;
    }

    //下三角列优先
    public int getLineIndex(int x,int y) {
        if (x>=length || y>=length) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(x,y));
        }
        return y*length+x;
    }

    public void printArray() {
        System.out.println("上三角行优先->"+ArraysUtils.printArr(array1));
        System.out.println("下三角行优先->"+ArraysUtils.printArr(array2));
        System.out.println("上三角列优先->"+ArraysUtils.printArr(array3));
        System.out.println("下三角列优先->"+ArraysUtils.printArr(array4));
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
        return "currIndex: {"+x+","+y+"}, maxIndex: {"+length+","+length+"}";
    }
}
