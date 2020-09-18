package xp.pan.bianlifeng;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 完全背包计数
 *
 * 硬币凑数，有N中硬币，需要组合中M元，有多少种组合办法。
 * 以dp[i][j]表示前i个物品j元可以有多少中组合，
 * dp[i][j] = dp[i-1][j] + dp[i-1][j-arr[i]] + dp[i-1][j-2 * arr[i]]
 */
public class Test0918_3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        int[] arr = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = n; j >= 0; j--) {
                for (int k = 1; k * arr[i] <= j; k++) {
                    dp[j] += dp[j - k * arr[i]];
                }
            }
        }
        System.out.println(dp[n]);
    }
}
