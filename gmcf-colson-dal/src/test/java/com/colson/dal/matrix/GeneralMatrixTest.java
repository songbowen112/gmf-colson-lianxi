package com.colson.dal.matrix;

import com.colson.dal.util.matrix.GeneralMatrix;

public class GeneralMatrixTest {

    public static void main(String[] args) {
        GeneralMatrix matrix = new GeneralMatrix();

        matrix.init(3,4);
        System.out.println(matrix);
        matrix.printArray();
        matrix.printArray2();

        int x = 1;
        int y = 2;
        int rowIndex = matrix.getRowIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的行优先下标为"+rowIndex+",值为"+matrix.getRowArray()[rowIndex]);
        x = 1;
        y = 3;
        rowIndex = matrix.getRowIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的行优先下标为"+rowIndex+",值为"+matrix.getRowArray()[rowIndex]);
        x = 2;
        y = 3;
        rowIndex = matrix.getRowIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的行优先下标为"+rowIndex+",值为"+matrix.getRowArray()[rowIndex]);

        x = 1;
        y = 2;
        int lineIndex = matrix.getLineIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的列优先下标为"+lineIndex+",值为"+matrix.getLineArray()[lineIndex]);
        x = 1;
        y = 3;
        lineIndex = matrix.getLineIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的列优先下标为"+lineIndex+",值为"+matrix.getLineArray()[lineIndex]);
        x = 2;
        y = 3;
        lineIndex = matrix.getLineIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的列优先下标为"+lineIndex+",值为"+matrix.getLineArray()[lineIndex]);

    }
}
