package xp.oj.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * 最长回文子串，manacher算法
 *
 * 问题描述
 * 求一个字符串中最长的回文串的长度。
 * 问题分析
 * 通过后缀数组和高度数组可以求最大回文串，但是因为后缀数组的计算需要比较多的数组，所以会导致内存超限。而扩展KMP算法还需要使用二分枚举所有二分点，计算比较复杂，因此本题使用manacher算法解决回文串问题。
 * manacher算法比较容易理解，如果以某个位置的字符为中心的回文覆盖区域为[i-L,i+L]，那么在这段区域内的回文会按照字符i中心对称，因此当计算i+ a时，可以先考虑i-a的回文长度，如果i-a的回文长超出了区域[i-L,i+L]，则i+a需要从i+L开始搜索回文长度。这个思想和扩展KMP搜索最大前缀是一样的。主要流程如下
 * 1、扩充字符串为奇数长度，即在原字符中每个字符的间隔中插入特殊字符。
 * 2、根据对称点的情况分为3中情况
 * 1、对称点不存在，i已经超过区域，需要从i开始搜索回文
 * 2、对称点的回文在区域内，i的回文长度和对称点相同
 * 3、对称点回文超过区域，i的回文一定超过区域边界
 *
 */
public class Palindrome3974 {

    public static void main(String[] args) {
        new Palindrome3974().solve();
    }

    void solve() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String str = null;
        try {
            for (int i = 1 ; !"END".equals(str = in.readLine()); i++) {
                out.printf("Case %d: %d\n", i, getParlidrome(str));
            }
        } catch (IOException e) {
            return;
        }
        out.flush();
    }
    char[] arr = new char[2000010];
    int[] dp = new int[2000010];
    int len;
    private int getParlidrome(String str) {
        len = str.length();
        int k = 0;
        // 转换字符串
        str.getChars(0, str.length(), arr, 0);
        for (int i = len - 1; i >= 0 ; i--) {
            arr[2 * i] = '#';
            arr[2 * i + 1] = str.charAt(i);
        }
        arr[len * 2] = '#';
        manacher(arr, dp, 2 * len + 1);
        int ans = 0;
        for (int i = 0; i < 2 * len + 1 ; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans - 1;
    }

    void manacher(char[] arr, int[] dp, int n) {
        int p = 0;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int j = 2 * p - i; // 对称点
            int h = j < 0 ? 0 : dp[j]; // 对称点半长
            if (h < dp[p] + p - i) { // 情况1
                dp[i] = h;
            } else { // 情况2/3
                h = Math.max(0, dp[p] + p - i);
                while (i + h < n && i >= h && arr[i + h] == arr[i - h]) h++;
                dp[i] = h;
                p = i;
            }
        }
    }
}
