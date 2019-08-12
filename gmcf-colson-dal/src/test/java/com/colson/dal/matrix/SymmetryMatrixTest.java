package com.colson.dal.matrix;

import com.colson.dal.util.matrix.SymmetryMatrix;

public class SymmetryMatrixTest {

    public static void main(String[] args) {
        SymmetryMatrix matrix = new SymmetryMatrix();

        matrix.init(3);
        System.out.println(matrix);
        System.out.println(matrix.getArray());

        int x = 1;
        int y = 2;
        int index = matrix.getIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的行优先下标为"+index+",值为"+matrix.getArray()[index]);
        x = 1;
        y = 3;
        index = matrix.getIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的行优先下标为"+index+",值为"+matrix.getArray()[index]);
        x = 2;
        y = 3;
        index = matrix.getIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的行优先下标为"+index+",值为"+matrix.getArray()[index]);


    }
}
