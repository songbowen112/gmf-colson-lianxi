package com.colson.dal.util.matrix;

import java.io.Serializable;

/**
 * 对称矩阵
 */
public class SymmetryMatrix<E> implements Serializable {

    private Object[][] data;

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
}
