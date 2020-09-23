package xp.pan.zijietiaodong;

import java.util.Scanner;

/**
 * 最小循环节
 * 利用KMP的思路计算最大公共前后缀
 */
public class Test0920_1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        System.out.println(new Test0920_1().repeatedSubstringPattern(str));
    }

    public String repeatedSubstringPattern(String s) {
        final int len = s.length();
        char[] charArr = s.toCharArray();
        int maxPrefix = getPrefix(charArr);
        if ((maxPrefix + 1) << 1 >= len
                && len % (len - maxPrefix - 1) == 0) {
            return s.substring(0, maxPrefix + 1);
        }
        return s;
    }

    private int getPrefix(char[] arr) {
        int[] fail = new int[arr.length];
        fail[0] = -1;
        int j = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            while (j != -1 && arr[i] != arr[j]) {
                j = fail[j];
            }
            fail[i + 1] = arr[i + 1] == arr[++j] ? fail[j] : j;
        }
        // 计算prefix[arr.length-1]
        while (j != -1 && arr[arr.length - 1] != arr[j]) {
            j = fail[j];
        }
        return j;
    }
}
