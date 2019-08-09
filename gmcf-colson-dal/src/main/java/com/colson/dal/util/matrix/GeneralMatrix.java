package com.colson.dal.util.matrix;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 普通矩阵
 */
public class GeneralMatrix<E> implements Serializable {

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

    public void init(int x,int y) {
        rowPriority = new Object[x*y];
        linePriority = new Object[x*y];
        data = new Object[x][y];
        for (int i=0,num=0;i<x;i++) {
            for (int j=0;j<y;j++) {
                data[i][j] = num++;
            }
        }
        for (int i=0,num=0;i<x;i++) {
            for (int j=0;j<y;j++) {
                rowPriority[num++] = data[i][j];
            }
        }

        for (int i=0,num=0;i<y;i++) {
            for (int j=0;j<x;j++) {
                linePriority[num] = data[j][i];
                num++;
            }
        }
    }

    public String getArray2() {
        StringBuilder sb = new StringBuilder();
        sb.append("行优先一维存储：[");
        Arrays.stream(rowPriority).forEach(i -> {
            sb.append(i + " ");
        });
        int index = sb.lastIndexOf(" ");
        sb.deleteCharAt(index);


        sb.append("]\n列优先一维存储：[");
        Arrays.stream(linePriority).forEach(i -> {
            sb.append(i + " ");
        });
        index = sb.lastIndexOf(" ");
        sb.deleteCharAt(index);

        sb.append("]\n");
        return sb.toString();
    }

    public String getArray() {
        StringBuilder sb = new StringBuilder();
        sb.append("行优先一维存储：[");
        for (int i = 0; i < rowPriority.length; i++) {
            if(i==rowPriority.length-1) {
                sb.append(i);
            } else {
                sb.append(i + " ");
            }
        }

        sb.append("]\n列优先一维存储：[");
        for (int i = 0; i < linePriority.length; i++) {
            if(i==linePriority.length-1) {
                sb.append(i);
            } else {
                sb.append(i + " ");
            }
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
}
