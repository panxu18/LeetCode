package xp.pan.meituan;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 给一个N才分为若干个正整数之和，要求整数不大于K，且至少有个数大于D。计算有多少种方案。
 * 和划分数不同的地方 在于这里整数的顺序不同视为不同方案,所以有
 * dp[i] = sum(dp[i - j]), 1<=j<=min(k,i)，
 * 可以前缀和优化。
 * 题目中第二个条件至少要大于D，可以通过将所有整数不大于D-1的组合减去。
 */
public class Test0913_3 {

    private static int mod = 998244353;
    private static int[] dp = new int[1007];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int K = in.nextInt();
        int D = in.nextInt();
        System.out.println(solve(N, K) - solve(N,D - 1));
    }

    private static int solve(int n, int k) {
        Arrays.fill(dp, 0);
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] << 1 % mod;
            if (i - k - 1 >= 0) {
                dp[i] -= dp[i - k - 1];
            }
        }
        return dp[n];
    }
}
