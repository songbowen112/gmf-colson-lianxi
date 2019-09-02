package com.colson.dal.matrix;

import com.colson.dal.util.matrix.TriangleMatrix;
import com.colson.dal.util.matrix.TridiagonalMatrix;

public class TridiagonalMatrixTest {

    public static void main(String[] args) {
        TridiagonalMatrix matrix = new TridiagonalMatrix();

        matrix.init(5,0);
        System.out.println(matrix);
        matrix.printArray();

        int x = 0;
        int y = 0;
        int index = matrix.getRowIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的行优先下标为"+index+",值为"+matrix.getRowPriority()[index]);
        x = 1;
        y = 2;
        index = matrix.getRowIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的行优先下标为"+index+",值为"+matrix.getRowPriority()[index]);
        x = 2;
        y = 2;
        index = matrix.getRowIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的行优先下标为"+index+",值为"+matrix.getRowPriority()[index]);

        System.out.println("---------------------------");
        x = 3;
        y = 2;
        index = matrix.getLineIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的列优先下标为"+index+",值为"+matrix.getLinePriority()[index]);
        x = 2;
        y = 2;
        index = matrix.getLineIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的列优先下标为"+index+",值为"+matrix.getLinePriority()[index]);
        x = 4;
        y = 3;
        index = matrix.getLineIndex(x, y);
        System.out.println("矩阵m("+x+","+y+")的列优先下标为"+index+",值为"+matrix.getLinePriority()[index]);


    }
}
