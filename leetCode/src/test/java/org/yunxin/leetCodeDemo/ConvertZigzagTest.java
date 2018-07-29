package org.yunxin.leetCodeDemo;

import org.junit.Test;


/**
 * Created by lpug on 11/09/2017.
 */
public class ConvertZigzagTest {


    @Test
    public void testConvert() throws Exception {
        assert(new ConvertZigzag().convert("PAYPALISHIRING", 3).equals("PAHNAPLSIIGYIR"));
    }
}