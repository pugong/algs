package org.yunxin.leetCodeDemo;

import org.junit.Test;

/**
 * Created by lpug on 14/09/2017.
 */
public class ReverseStringTest {

    @Test
    public void testReverse() throws Exception {
        String t1 = "hello";
        String t1Result = "olleh";
        String t2 = "";
        String t3 = null;

        ReverseString rs = new ReverseString();
        assert (rs.reverse(t1).equals(t1Result));

        assert (rs.reverse(t2).isEmpty());

        assert(rs.reverse(t3).equals(t3));
    }
}