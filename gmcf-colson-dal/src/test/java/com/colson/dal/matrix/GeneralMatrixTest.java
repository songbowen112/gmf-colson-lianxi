package com.colson.dal.matrix;

import com.colson.dal.util.matrix.GeneralMatrix;

public class GeneralMatrixTest {

    public static void main(String[] args) {
        GeneralMatrix matrix = new GeneralMatrix();
        matrix.init(2,3);
        System.out.println(matrix);
    }
}
