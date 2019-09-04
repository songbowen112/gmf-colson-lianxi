package com.colson.dal.util.matrix;

import com.colson.dal.common.biz.pkg.BaseDTO;
import com.colson.dal.util.ArraysUtils;

/**
 * 三对角矩阵
 * [1 3 0 0 0]
 * [5 7 2 0 0]
 * [0 3 2 3 0]
 * [0 0 3 1 2]
 * [0 0 0 3 6]
 *
 */
public class TridiagonalMatrix extends BaseDTO {

    //矩阵边长
    private int length = 0;

    private Object[][] data;

    /**
     * 行优先
     */
    private Object[] rowPriority;

    /**
     * 列优先
     */
    private Object[] linePriority;

    public void init(int length, int constant) {
        this.length = length;

        data = new Object[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int differ = Math.abs(i-j);
                if (differ>1) {
                    data[i][j] = constant;
                } else {
                    data[i][j] = i + 10;
                }
            }
        }

        //行优先
        rowPriority = new Object[(length-2)*3+5];//优化方法
        for (int i=0,num=0;i<length;i++) {
            for (int j=i-1;Math.abs(i-j)<=1;j++) {
                if (j>=length) {
                    break;
                }
                j=j<0?0:j;
                rowPriority[num++] = data[i][j];
            }
        }
        rowPriority[rowPriority.length-1] = constant;
//        rowPriority = new Object[(length-2)*3+5];//效率低的方法
//        for (int i=0,num=0;i<length;i++) {
//            for (int j=0;j<length;j++) {
//                int differ = Math.abs(i-j);
//                if (differ<=1) {
//                    rowPriority[num++] = data[i][j];
//                }
//            }
//        }
//        rowPriority[rowPriority.length-1] = constant;

        //列优先
        linePriority = new Object[(length-2)*3+5];//优化方法
        for (int j=0,num=0;j<length;j++) {
            for (int i=j-1;Math.abs(i-j)<=1;i++) {
                if (i>=length) {
                    break;
                }
                i=i<0?0:i;
                linePriority[num++] = data[i][j];
            }
        }
        linePriority[linePriority.length-1] = constant;
//        linePriority = new Object[(length-2)*3+5];//
//        for (int j=0,num=0;j<length;j++) {
//            for (int i=0;i<length;i++) {
//                int differ = Math.abs(i-j);
//                if (differ<=1) {
//                    linePriority[num++] = data[i][j];
//                }
//            }
//        }
//        linePriority[linePriority.length-1] = constant;
    }

    /**
     * 获取下三角行优先数组
     * @return
     */
    public Object[] getRowPriority() {
        return rowPriority;
    }

    /**
     * 获取下三角列优先数组
     * @return
     */
    public Object[] getLinePriority() {
        return linePriority;
    }

    //下三角行优先
    public int getRowIndex(int i,int j) {
        if (i>=length || j>=length) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(i,j));
        }
        if (i >= j) {
            return i*(i-1)/2+j+i;//矩阵下标从0,0开始存储
        }
        return rowPriority.length-1;
    }

    //下三角列优先
    public int getLineIndex(int i,int j) {
        if (i>=length || j>=length) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(i,j));
        }
        if (i >= j) {
            return (2*length+1-j)*j/2+i-j;//矩阵下标从0,0开始存储
        }
        return linePriority.length-1;
    }

    public void printArray() {
        System.out.print("行优先->"+ ArraysUtils.printArr(rowPriority));
        System.out.print("列优先->"+ArraysUtils.printArr(linePriority));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<data.length;i++) {
            sb.append("[");
            for (int j=0;j<data[i].length;j++) {
                if(j==data[i].length-1) {
                    sb.append(data[i][j]);
                } else {
                    sb.append(data[i][j]+" ");
                }
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

    /**
     * 下标越界信息
     * @param i,y
     * @return
     */
    private String outOfBoundsMsg(int i,int j) {
        return "currIndex: {"+i+","+j+"}, maxIndex: {"+length+","+length+"}";
    }
}