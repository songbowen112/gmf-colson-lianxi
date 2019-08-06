package com.colson.dal.matrix;

import com.colson.dal.util.GeneralMatrix;

public class GeneralMatrixTest {

    public static void main(String[] args) {
        GeneralMatrix matrix = new GeneralMatrix();
        matrix.init(10,10);
        System.out.println(matrix);
    }
}
