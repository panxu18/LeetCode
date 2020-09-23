package xp.leetcode;

import java.util.Arrays;

public class LeetCode322 {
    public int coinChange(int[] coins, int amount) {
        // dp[i][j] = min(dp[i-1][j-k*conis[i]]+k), dp[i-1][j-k*coins[i]] > -1
        // dp[i][j] = min(dp[i-1][j], dp[i][j-conis[i]]+1),dp[i-1][j]>-1, dp[i][j-conis[i]]>-1
        int[] dp = new int[amount+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j-coins[i]] != -1) {
                    if (dp[j] == -1) {
                        dp[j] = dp[j-coins[i]] + 1;
                    } else {
                        dp[j] = Math.min(dp[j], dp[j-coins[i]] + 1);
                    }

                }
            }
        }
        return dp[amount];
    }
}
