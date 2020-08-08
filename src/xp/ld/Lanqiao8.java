package xp.ld;

import java.util.Scanner;

public class Lanqiao8 {

    public static void main(String[] args) {
        System.out.println(solve());
    }

    private static int solve() {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 2; i <= n ; i++) {
            dp[1][i] = 1;
        }
        for (int i = 1; i < m; i++) {

            for (int j = 1; j <= n ; j++) {
                if ((i & 1) == 1) {
                    // 比后一个大
                    for (int k = 1; k < j ; k++) {
                        dp[i+1][k] = (dp[i+1][k] + dp[i][j]) % 10000;
                    }
                } else {
                    // 比后一个小
                    for (int k = j+1; k <=n ; k++) {
                        dp[i+1][k] = (dp[i+1][k] + dp[i][j]) % 10000;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= n ; i++) {
            ans = (ans + dp[m][i]) % 10000;
        }
        return ans;
    }


}
