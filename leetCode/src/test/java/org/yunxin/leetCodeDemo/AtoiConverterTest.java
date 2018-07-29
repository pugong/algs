package org.yunxin.leetCodeDemo;

import org.junit.Test;

/**
 * Created by lpug on 15/09/2017.
 */
public class AtoiConverterTest {

    @Test
    public void testMyAtoi() throws Exception {
        AtoiConverter leetCode = new AtoiConverter();
        assert (leetCode.myAtoi("10") == 10);
        assert (leetCode.myAtoi("2147483647") == 2147483647);
        assert (leetCode.myAtoi("-2147483647") == -2147483647);
        assert (leetCode.myAtoi("+-10") == 0);
        assert (leetCode.myAtoi(" ") == 0);
        assert leetCode.myAtoi("4193 with words") == 4193;
        assert leetCode.myAtoi("words and 987") == 0;

        assert leetCode.myAtoi("-91283472332") == -2147483648;

        assert leetCode.myAtoi("  -0012a42") == -12;

        assert leetCode.myAtoi("abc") == 0;

        assert leetCode.myAtoi("-abc") == 0;
        assert leetCode.myAtoi("-5-") == -5;
    }

    @Test
    public void testIsNumber() throws Exception {
        AtoiConverter leetCode = new AtoiConverter();
        assert (leetCode.isNumber("0") == true);
        assert (leetCode.isNumber(" 0.1 ") == true);
        assert (leetCode.isNumber("abc") == false);
        assert (leetCode.isNumber("1 a") == false);
        assert (leetCode.isNumber("2e10") == true);
    }
}