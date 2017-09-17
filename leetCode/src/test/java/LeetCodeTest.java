import org.junit.Test;

/**
 * Created by lpug on 15/09/2017.
 */
public class LeetCodeTest {

    @Test
    public void testMyAtoi() throws Exception {
        LeetCode leetCode = new LeetCode();
        assert (leetCode.myAtoi("10") == 10);
        assert (leetCode.myAtoi("2147483647") == 2147483647);
        assert (leetCode.myAtoi("-2147483647") == -2147483647);
        assert (leetCode.myAtoi("+-10") == 0);

    }

    @Test
    public void testIsNumber() throws Exception {
        LeetCode leetCode = new LeetCode();
        assert (leetCode.isNumber("0") == true);
        assert (leetCode.isNumber(" 0.1 ") == true);
        assert (leetCode.isNumber("abc") == false);
        assert (leetCode.isNumber("1 a") == false);
        assert (leetCode.isNumber("2e10") == true);
    }
}