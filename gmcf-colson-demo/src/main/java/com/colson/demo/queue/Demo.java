package com.colson.demo.queue;


import com.colson.util.queue.SeqQueue;
import com.colson.util.queue.SeqQueue2;
import com.colson.util.stack.SeqStack;

/**
 * 在旋转排序数组中搜索
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。请编写一个函数来搜索给定的目标值，如果目标值存在返回其索引，否则返回 -1。
 * 例如：
 * // [ 0, 1, 2,4, 5, 6, 7]旋转后 [4, 5, 6, 7, 0, 1, 2]
 * 搜索目标值 0，返回 4
 * // 在旋转后的数组 [4, 5, 6, 7, 0, 1, 2] 中搜索目标值 3，返回 -1
 */
public class Demo {

    public static void main(String[] args) {
        int target = 0;
        int[] ints = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(find(ints, target));
    }

    public static int find(int[] array, int target) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            length = length/2;
        }
        for (int i = 0; i < length/2; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        for (int i = length-1; i > length/2; i--) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

//    public static int findIndex(int[] array1, int[] array2, int target) {
//        int length = array.length;
//        for (int i = 0; i < length; i++) {
//            length = length/2;
//        }
//        for (int i = 0; i < length/2; i++) {
//            if (array[i] == target) {
//                return i;
//            }
//        }
//        for (int i = length-1; i > length/2; i--) {
//            if (array[i] == target) {
//                return i;
//            }
//        }
//        return -1;
//    }
}
