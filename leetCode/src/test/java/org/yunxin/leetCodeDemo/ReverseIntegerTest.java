package org.yunxin.leetCodeDemo;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by lpug on 2018/7/28.
 */
public class ReverseIntegerTest extends TestCase {

    @Test
    public void testReverse() throws Exception {
        ReverseInteger reverseInteger = new ReverseInteger();
        assert reverseInteger.reverse(123) == 321;
        assert reverseInteger.reverse(-123) == -321;
        assert reverseInteger.reverse(120) == 21;
        assert reverseInteger.reverse(Integer.MAX_VALUE) == 0;
        assert  reverseInteger.reverse(Integer.MIN_VALUE) == 0;
    }
}