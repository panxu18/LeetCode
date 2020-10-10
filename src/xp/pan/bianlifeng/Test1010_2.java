package xp.pan.bianlifeng;

import java.util.Scanner;

public class Test1010_2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int[][] dp = new int[k + 1][n + 1];
        dp[0][0] = 1;
        for (int j = 1; j <= k; j++) {
            for (int i = 0; i <= n; i++) {
                if (i >= j) {
                    dp[j][i] = dp[j - 1][i - 1] + dp[j][i - j];
                }
            }
        }
        System.out.println(dp[k][n]);
    }
}
