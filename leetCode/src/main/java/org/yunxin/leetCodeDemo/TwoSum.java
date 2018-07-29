package org.yunxin.leetCodeDemo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lpug on 14/09/2017.
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {

        // return bruteTwoSum(nums, target)
        return hashTwoSum(nums, target);
    }

    private int[] bruteTwoSum(int[] nums, int target) {
        // brute force  - O(n^2)
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");

    }

    private int[] hashTwoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
