package com.colson.dal;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *  
 */
public class MeituanTest4 {

    static Integer[] getResultArray(Integer[] nums, Integer target) {

        List<Integer> results = new ArrayList<>();
        Integer sum = 0;
        for (int i = 0; i < nums.length; i++) {
            while (target != sum) {
                results.add(i);
                sum += nums[i];
            }
            return (Integer[]) results.toArray();
        }
        return null;
    }



}

