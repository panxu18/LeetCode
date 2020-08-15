package xp.oj.poj.dp;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 多重背包，动态规划
 *
 * 问题描述
 * 有n个数字，每种数字个数为mi，判断是否可以从这些数字中选择若干个可以组成哪些不大于k的数字。
 * 问题分析
 * 动态规划+滚动数组，类似于背包问题，但是每个数字可以选择不超过mi次，因此对于某一个数字的的决策需要考虑选0到mi个，一共mi+1种情况。实际上不需要考虑mi+1中情况，就像前缀和一样，可以优化到常数种情况。如果dp[i][j]为true，当第i个数还有剩余时，那么dp[i][j + ai]一定为true。因此dp[i][j]保存是否可以组成j以及第i个数剩余多少个。
 * 注意边界，k=0可以直接组成，0种数只能组成0；
 */
public class Coins1742 {

    public static void main(String[] args) {
        new Coins1742().solve();
    }

    int[] dp = new int[100010];
    int n,m;
    int[] as = new int[110];
    int[] cs = new int[110];
    boolean[] used = new boolean[100010];
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        while (in.hasNext()) {
            n = in.nextInt();
            m = in.nextInt();
            if (n == 0 && m == 0) break;
            for (int i = 0; i < n; i++) {
                as[i] = in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                cs[i] = in.nextInt();
            }
            out.println(getAns());
        }
        out.flush();
    }

    private int getAns(){
        Arrays.fill(used, false);
        Arrays.fill(dp,-1);
        dp[0] = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                if (dp[j] >= 0) {
                    dp[j] = cs[i];
                    used[j] = true;
                } else if (j >= as[i] && dp[j - as[i]] > 0) {
                    dp[j] = dp[j - as[i]] - 1;
                    used[j] = true;
                } else {
                    dp[j] = -1;
                }
            }
        }
        for (int i = 1; i <= m; i++) {
            if (used[i]) {
                cnt++;
            }
        }
        return cnt;
    }
}
