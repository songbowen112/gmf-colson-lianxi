package com.colson.dal.matrix;

import com.colson.dal.util.matrix.SymmetryMatrix;

public class SymmetryMatrixTest {

    public static void main(String[] args) {
        SymmetryMatrix matrix = new SymmetryMatrix();
        matrix.init(4);
        System.out.println(matrix);
    }
}
