package com.colson.dal.util.matrix;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 对称矩阵(必须是正方形),只需要存储一半数据跟对角线上的数据
 * 行优先存储跟列优先存储相同
 * [0 1 2]
 * [1 2 3]
 * [2 3 4]
 * 一维存储为：[0 1 2 2 3 4]
 */
public class SymmetryMatrix<E> implements Serializable {

    //矩阵边长
    private int length = 0;

    private Object[][] data;

    /**
     * 一维存储(下三角)
     */
    private Object[] array;

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
        array = new Object[length*(length+1)/2];
        for (int i=0,num=0;i<length;i++) {
            for (int j=0;j<length;j++) {
                array[num++] = data[i][j];
            }
        }
    }

    /**
     * 获取行优先数组
     * @return
     */
    public Object[] getArray() {
        return array;
    }

    public int getIndex(int x,int y) {
        int target = (x+1)*(y+1);
        if (target>length*length) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(target));
        }
        return y*length+x;

    }

    public String printArray() {
        int index;
        //打印一维数组
        StringBuilder sb = new StringBuilder();
        sb.append("一维存储：[");
        if (null != array) {
            Arrays.stream(array).forEach(i -> {
                sb.append(i + " ");
            });
            index = sb.lastIndexOf(" ");
            sb.deleteCharAt(index);
        }

        sb.append("]\n");
        return sb.toString();
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
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+length*length;
    }
}
