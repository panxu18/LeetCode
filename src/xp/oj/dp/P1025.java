package xp.oj.dp;

import java.util.Scanner;

/**
 * 化分数
 * 将整数nn分成k份，且每份不能为空，任意两个方案不相同(不考虑顺序)。
 */
public class P1025 {
    private static int[][] dp = new int[205][205];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        dp[0][0] = 1;
        for (int j = 1; j <= m; j++) {
            for (int i = 0; i <= n; i++) {
                if (i >= j) {
                    dp[j][i] = dp[j-1][i-1] + dp[j][i - j];
                }
            }
        }
        System.out.println(dp[m][n]);
    }
}
