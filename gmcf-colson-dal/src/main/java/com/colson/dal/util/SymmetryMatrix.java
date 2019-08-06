package com.colson.dal.util;

import java.io.Serializable;

/**
 * 对称矩阵
 */
public class SymmetryMatrix<E> implements Serializable {

    private Object[][] data;

    public SymmetryMatrix() {
    }

    public void init(int length) {
        int num = 0;
        data = new Object[length][length];
        for (int i=0;i<length;i++) {
            for (int j=0;j<length;j++) {
                data[i][j] = num++;
            }
        }
    }
}
