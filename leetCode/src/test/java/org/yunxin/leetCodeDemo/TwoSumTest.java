package org.yunxin.leetCodeDemo;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lpug on 14/09/2017.
 */
public class TwoSumTest {

    @Test
    public void testTwoSum() throws Exception {
        int[] nums = new int[] {2, 7, 11, 15};
        int target = 9;

        Assert.assertArrayEquals(new TwoSum().twoSum(nums, target), (new int[]{0, 1}));
    }
}