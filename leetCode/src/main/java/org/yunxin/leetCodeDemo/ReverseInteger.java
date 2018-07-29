package org.yunxin.leetCodeDemo;

/**
 * Created by lpug on 2018/7/28.
 * Given a 32-bit signed integer, reverse digits of an integer.
 * Assume we are dealing with an environment which could only
 * store integers within the 32-bit signed integer
 * range: [−2^31,  2^31 − 1]. For the purpose of this problem,
 * assume that your function returns 0 when the reversed
 * integer overflows.
 */

public class ReverseInteger {
    public int reverse(int x) {

        int result = 0;

        while (x != 0)
        {
            int reminder = x % 10;
            int tmpResult = result * 10 + reminder;
            if ((tmpResult - reminder) / 10 != result) {
                return 0;
            }
            result = tmpResult;
            x = x / 10;
        }

        return result;

    }
}
