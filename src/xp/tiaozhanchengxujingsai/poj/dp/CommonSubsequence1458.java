package xp.tiaozhanchengxujingsai.poj.dp;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 最长的公共子序列
 *
 * 使用动态规划+滚动数组，从最后一个字符开始，如果两个字符相同，那么最大公共子序列一定包含着两个字符。如果最后两个字符不相同，那么至少有一个字符是不含在最大公共子序列中的。注意初始条件，空字符串和其他字符串最大公共子序列长度为0，所以初始状态全部为0。
 */
public class CommonSubsequence1458 {

    public static void main(String[] args) {
        new CommonSubsequence1458().solve();
    }

    int[][] dp = new int[2][1000];
    int n,m;
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        while (in.hasNext()) {
            String s1 = in.next();
            String s2 = in.next();
            n = s1.length();
            m = s2.length();
            out.println(lcs(s1, s2));
        }
        out.flush();
    }

    private int lcs(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.fill(dp[0], 0);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr1[i - 1] == arr2[j - 1])
                    dp[i&1][j] = dp[(i+ 1) & 1][j - 1] + 1;
                else
                    dp[i&1][j] = Math.max(dp[(i+ 1) & 1][j], dp[i&1][j - 1]);
            }
        }
        return dp[n&1][m];
    }
}
