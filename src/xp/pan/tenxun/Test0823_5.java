package xp.pan.tenxun;

import java.util.Scanner;

import static java.lang.Math.min;

/**
 * 动态规划，回文串
 *
 * 给定一个字符串，计算区间[l,r]之间可以分为多少个回文串，回文串长度尽量长.
 * 根据要求可知，如果一个区间的子串是回文串，那么返回1，否则需要枚举所有中点，判断两边是否是回文串
 */
public class Test0823_5 {

    private static char[] arr;
    private static int[][] dp = new int[500][500];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        arr = str.toCharArray();
        init(str.length());
        while (in.hasNext()) {
            int l = in.nextInt();
            int r = in.nextInt();
            System.out.println(dp[l][r]);
        }
    }

    private static void init(int n) {
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int len = 1; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                dp[i][i + len] = len + 1;
                if (check(i, len)) {
                    dp[i][i + len] = 1;
                    continue;
                }
                for (int j = 0; j < len; j++) {
                    dp[i][i + len] = min(dp[i][i + j] + dp[i + j + 1][i + len], dp[i][i + len]);
                }
            }
        }
    }

    private static boolean check(int i, int len) {
        int s = i;
        int t = i + len;
        while (s < t) {
            if (arr[s] != arr[t]) {
                return false;
            }
        }
        return true;
    }
}
