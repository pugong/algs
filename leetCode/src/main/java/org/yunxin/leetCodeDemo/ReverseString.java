package org.yunxin.leetCodeDemo;

/**
 * Created by lpug on 14/09/2017.
 */
public class ReverseString {

    public void reverseString() {

    }


    public String reverse(String s) {

        if(s == null || s.isEmpty())
            return s;

        StringBuilder sb = new StringBuilder();

        for(int i = s.length(); i > 0; i--)
            sb.append(s.charAt(i-1));

        return sb.toString();
    }

}
