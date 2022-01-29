package com.solson.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 4
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 */
public class MeituanTest4 {

    static Integer[] getResultArray(Integer[] nums, Integer target) {

        if (nums.length < 2)
            return null;
        List<Integer> results = new ArrayList<>();
        Integer sum = 0;

        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            map.put(i, nums[i]);
//            while (target != sum) {
//                results.add(i);
//                sum += nums[i];
//            }
            return (Integer[]) results.toArray();
        }
        return null;
    }



}

