package com.colson.dal.matrix;

import com.colson.dal.util.matrix.GeneralMatrix;

public class GeneralMatrixTest {

    public static void main(String[] args) {
        GeneralMatrix matrix = new GeneralMatrix();
        System.out.println(matrix.getArray());

        matrix.init(3,4);
        System.out.println(matrix);
        System.out.println(matrix.getArray2());

        System.out.println(matrix.getRowIndex(1,2));
        System.out.println(matrix.getRowIndex(2,3));
        System.out.println(matrix.getRowIndex(1,3));

        System.out.println(matrix.getLineIndex(1,2));
        System.out.println(matrix.getLineIndex(2,3));
        System.out.println(matrix.getLineIndex(3,3));



    }
}
