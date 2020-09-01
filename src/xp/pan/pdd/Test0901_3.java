package xp.pan.pdd;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 负数背包
 * 有N个物品，容量为M的背包，假设每个物品的大小为C，收益为F，其中C可以为负数，负数表示增加背包容量.
 * 计算最大的收益。
 * 由题意可知使负数01背包问题，dp需要将容量也就是范围向右偏移mid，保证不会出现负索引。先不考虑背包初始容量，直接进行dp。
 * 得到结果应该是背包最终容量为i对应的最大收益。考虑背包容量不能为负数，且背包有初始容量，所以背包最小应该为容量0=mid+M，
 * 因此[0,mid+M]中的最大值即为最优解。
 *
 */
/*
输入
4 4
-1 -1
1 -1
-1 1
6 6
输出
6
 */
public class Test0901_3 {
    private static int N, M;
    private static int[] dp = new int[20];
    private static int[] sarr = new int[5];
    private static int[] farr = new int[5];
    private static int mid = 10; // 初始偏移100000,保证容量不会出现负数
    private static int maxn = 20;
    private static int INF = 1000000007;
    private static int ans = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        N = in.nextInt();
        M = in.nextInt();
        for (int i = 0; i < N; i++) {
            sarr[i] = in.nextInt();
            farr[i] = in.nextInt();
        }
        computDP();
        out.println(ans);
        out.flush();
    }

    private static void computDP() {
        Arrays.fill(dp, -INF);
        dp[mid] = 0;
        for (int i = 0; i < N; i++) {
            if (sarr[i] >= 0) {
                for (int j = maxn - 1; j >= sarr[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - sarr[i]] + farr[i]);
                }
            } else {
                for (int j = 0; j < maxn + sarr[i]; j++) {
                    dp[j] = Math.max(dp[j], dp[j - sarr[i]] + farr[i]);
                }
            }

        }
        for (int i = mid + M; i >= 0; i--) {
            if (dp[i] > 0) {
                ans = Math.max(ans, dp[i]);
            }
        }

    }
}
