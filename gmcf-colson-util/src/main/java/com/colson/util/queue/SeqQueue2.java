package com.colson.util.queue;


import com.colson.common.pkg.BaseDTO;
import com.colson.util.constant.IndexConstant;

/**
 * 顺序队列2（先进先出）
 * 不要求从下标为0的位置存储队首
 * @author songbowen
 * @param <E>
 */
public class SeqQueue2<E> extends BaseDTO {

    /**
     * 初始数据长度为100
     */
    transient Object[] datas = new Object[13];

    /**
     * 队头指针(指向第一个元素)
     */
    transient int begin = -1;

    /**
     * 队尾指针(指向最后一个元素的下标+1)
     */
    transient int end = -1;

    /**
     * 队列长度
     */
    transient int size;

    public SeqQueue2() {
    }

    /**
     * 获取长度
     */
    public int size() {
        return size;
    }

    /**
     * 入队操作:队不满时,先把值赋给队尾指针所指的下标位置,再将队尾指针+1
     * @param data
     */
    public void add(E data) {
        if (end == datas.length) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(datas.length));
        }
        if (end == -1) {
            datas[datas.length/2] = data;
            end = datas.length/2+1;
            begin = datas.length/2;
        } else {
            datas[end] = data;
            end++;
        }
        size++;
    }

    /**
     * 出队操作:队不空时,先取队头元素值,再将队头指针+1
     */
    public E del() {
        if (begin==-1 || end==begin) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(IndexConstant.ZERO));
        }
        E result;
        if (end-begin == 1) {
            result = (E) datas[begin];
            begin = -1;
            end = -1;
        } else {
            result = (E) datas[begin++];
        }
        size--;
        return result;
    }

    /**
     * 获取指定下标的数据
     * @param index
     * @return
     */
    private E get(int index) {
        if (begin==-1) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(IndexConstant.ZERO));
        }
        return (E) datas[index];
    }

    /**
     * 根据下标获取有效数据
     * @param which
     * @return
     */
    public E find(int which) {
        if (begin==-1 || begin+which>=end) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(which));
        }
        return (E) datas[begin+which];
    }

    /**
     * 返回指定数据的下标
     * @param data
     * @return
     */
    public int indexOf(E data) {
        if (begin==-1) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(IndexConstant.ZERO));
        }
        for (int i=begin;i<end;i++) {
            if (get(i).equals(data)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        if (begin==-1) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        for (int i=begin;i<end;i++) {
            sb.append(this.get(i)+",");
        }
        return sb.toString().isEmpty()?"[]":"[" + sb.substring(0,sb.length()-1) + ']';
    }

    /**
     * 下标越界信息
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+this.size();
    }
}
