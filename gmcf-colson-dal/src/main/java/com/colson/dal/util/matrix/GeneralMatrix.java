package com.colson.dal.util.matrix;

import com.colson.dal.util.constant.IndexConstant;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 普通矩阵
 */
public class GeneralMatrix<E> implements Serializable {

    //矩阵宽度
    private int rowNum = 0;

    //矩阵长度
    private int lineNum = 0;

    private Object[][] data;

    /**
     * 行优先存储
     */
    private Object[] rowPriority;

    /**
     * 列优先存储
     */
    private Object[] linePriority;

    public GeneralMatrix() {
    }

    public void init(int x,int y) {
        this.rowNum = x;
        this.lineNum = y;

        rowPriority = new Object[x*y];
        linePriority = new Object[x*y];
        data = new Object[x][y];
        for (int i=0,num=0;i<x;i++) {
            for (int j=0;j<y;j++) {
                data[i][j] = num++;
            }
        }
        for (int i=0,num=0;i<x;i++) {
            for (int j=0;j<y;j++) {
                rowPriority[num++] = data[i][j];
            }
        }

        for (int i=0,num=0;i<y;i++) {
            for (int j=0;j<x;j++) {
                linePriority[num++] = data[j][i];
            }
        }
    }

    public int getRowIndex(int x,int y) {
        int target = (x+1)*(y+1);
        if (target>rowNum*lineNum) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(target));
        }
        return x*lineNum+y;
    }

    public int getLineIndex(int x,int y) {
        int target = (x+1)*(y+1);
        if (target>rowNum*lineNum) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(target));
        }
        return y*rowNum+x;
    }

    public String getArray() {
        int index;
        //载入行数据
        StringBuilder sb = new StringBuilder();
        sb.append("行优先一维存储：[");
        if (null != rowPriority) {
            Arrays.stream(rowPriority).forEach(i -> {
                sb.append(i + " ");
            });
            index = sb.lastIndexOf(" ");
            sb.deleteCharAt(index);
        }

        //载入列数据
        sb.append("]\n列优先一维存储：[");
        if (null != linePriority) {
            Arrays.stream(linePriority).forEach(i -> {
                sb.append(i + " ");
            });
            index = sb.lastIndexOf(" ");
            sb.deleteCharAt(index);
        }

        sb.append("]\n");
        return sb.toString();
    }

    public String getArray2() {
        //载入行数据
        StringBuilder sb = new StringBuilder();
        sb.append("行优先一维存储：[");
        if (null != rowPriority) {
            for (int i = 0; i < rowPriority.length; i++) {
                if(i==rowPriority.length-1) {
                    sb.append(rowPriority[i]);
                } else {
                    sb.append(rowPriority[i] + " ");
                }
            }
        }

        //载入列数据
        sb.append("]\n列优先一维存储：[");
        if (null != linePriority) {
            for (int i = 0; i < linePriority.length; i++) {
                if(i==linePriority.length-1) {
                    sb.append(linePriority[i]);
                } else {
                    sb.append(linePriority[i] + " ");
                }
            }
        }
        sb.append("]\n");
        return sb.toString();
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
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+rowNum*lineNum;
    }
}
