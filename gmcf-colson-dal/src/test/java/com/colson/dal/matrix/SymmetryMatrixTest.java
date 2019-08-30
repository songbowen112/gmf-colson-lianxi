package com.colson.dal.matrix;

import com.colson.dal.util.matrix.SymmetryMatrix;

public class SymmetryMatrixTest {

    public static void main(String[] args) {
        SymmetryMatrix matrix = new SymmetryMatrix();

        matrix.init(5);
        System.out.println(matrix);
        matrix.printArray();

        int x = 1;
        int y = 1;
        int index = matrix.getRowIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的行优先下标为"+index+",值为"+matrix.getArray2()[index]);
        x = 1;
        y = 2;
        index = matrix.getRowIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的行优先下标为"+index+",值为"+matrix.getArray2()[index]);
        x = 2;
        y = 2;
        index = matrix.getRowIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的行优先下标为"+index+",值为"+matrix.getArray2()[index]);

        System.out.println("---------------------------");
        x = 1;
        y = 2;
        index = matrix.getLineIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的列优先下标为"+index+",值为"+matrix.getArray4()[index]);
        x = 2;
        y = 2;
        index = matrix.getLineIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的列优先下标为"+index+",值为"+matrix.getArray4()[index]);
        x = 2;
        y = 4;
        index = matrix.getLineIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的列优先下标为"+index+",值为"+matrix.getArray4()[index]);
        x = 3;
        y = 2;
        index = matrix.getLineIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的列优先下标为"+index+",值为"+matrix.getArray4()[index]);


    }
}
