/**
 * Created by lpug on 15/09/2017.
 */
public class LeetCode {

    /*
    Implement atoi to convert a string to an integer.

    Hint: Carefully consider all possible input cases. If you want a challenge,
    please do not see below and ask yourself what are the possible input cases.

    Notes: It is intended for this problem to be specified vaguely (ie,
    no given input specs). You are responsible to gather all the input
    requirements up front.

     */
    public int myAtoi(String str) {
        if (str == null || str.isEmpty())
            return 0;
        boolean negative = false;
        int result = 0;
        str = str.trim();
        char firstChar = str.charAt(0);
        if (firstChar == '-')
            negative = true;
        else if (firstChar == '+')
            negative = false;
        else
            result = Character.getNumericValue(firstChar);

        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '-'|| c == '+')
                return 0;

            if (c < 0)
                return 0;

            int temp = Character.getNumericValue(c);
            if(temp > 10 || temp < 0)
                return 0;

            result *= 10;
            result += temp;
        }

        return negative ? -result : result;

    }

    /* Validate if a given string is numeric.

        Some examples:
        "0" => true
        " 0.1 " => true
        "abc" => false
        "1 a" => false
        "2e10" => true
        Note: It is intended for the problem statement to be ambiguous.
        You should gather all requirements up front before implementing one.
    */

    public boolean isNumber(String s) {
//        throw new NotImplementedException();
        s = s.trim();
        if(s.isEmpty())
        {
            return  false;
        }
        try  {
            Double.parseDouble(s);
            return  true;
        }
        catch (Exception e){
            // parse "2e10"

            return false;
        }
    }
}
