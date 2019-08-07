package com.colson.dal.util.matrix;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 普通矩阵
 */
public class GeneralMatrix<E> implements Serializable {

    private Object[][] data;

    public GeneralMatrix() {
    }

    public void init(int x,int y) {
        int num = 0;
        data = new Object[x][y];
        for (int i=0;i<x;i++) {
            for (int j=0;j<y;j++) {
                data[i][j] = num++;
            }
        }
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
