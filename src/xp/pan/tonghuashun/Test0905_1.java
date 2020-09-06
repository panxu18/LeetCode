package xp.pan.tonghuashun;

import java.io.UnsupportedEncodingException;

public class Test0905_1 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "aaabbbc";
        System.out.println(str.matches("ab+c")); // fase
        System.out.println(str.matches("a+b+c+")); // true
    }
}
