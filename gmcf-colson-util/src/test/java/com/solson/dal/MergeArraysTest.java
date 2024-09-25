package com.solson.dal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 请用代码实现:给定一个由区间[start,end]组成的无序数组，合并所有重叠的区间。
 * 其中[start,end]由两个整数组成，end >= start.
 * 例如;
 * 输人: [[1,3],[2,6],[15,18],[8,10]]
 * 输出: [1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6]重叠，将它们合并为 [1,6].
 */
public class MergeArraysTest {

    public static void main(String[] args) {
        int[][] arrays = new int[][]{{1, 3}, {2, 6}, {15, 18}, {8, 10}};
        List<int[]> mergedIntervals = mergeIntervals(arrays);
        for (int[] array : mergedIntervals) {
            System.out.println(Arrays.toString(array));
        }
    }
    public static List<int[]> mergeIntervals(int[][] intervals) {
        // 冒泡排序
        for (int i = 0; i < intervals.length; i++) {
            // 确保 j + 1 不超过数组的最大索引
            for (int j = 0; j < intervals.length - i - 1; j++) {
                if (intervals[j][0] > intervals[j + 1][0]) {
                    int[] temp = intervals[j];
                    intervals[j] = intervals[j + 1];
                    intervals[j + 1] = temp;
                }
            }
        }
        // 创建一个列表来存储合并后的区间
        List<int[]> mergedIntervals = new ArrayList<>();

        for (int[] interval : intervals) {
            // 如果列表为空，或者当前区间的起始时间大于已合并区间的结束时间，则直接添加
            if (mergedIntervals.isEmpty() || mergedIntervals.get(mergedIntervals.size() - 1)[1] < interval[0]) {
                mergedIntervals.add(interval);
            } else {
                // 否则，合并当前区间与最后一个已合并的区间
                int[] lastInterval = mergedIntervals.get(mergedIntervals.size() - 1);
                lastInterval[1] = Math.max(lastInterval[1], interval[1]);
            }
        }
        return mergedIntervals;
    }
}
