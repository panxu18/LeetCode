package xp.oj.dp;

import java.util.Scanner;

import static java.lang.Math.max;

/**
 * 三维动态规划、最大费用最大流
 * <p>
 * 问题描述
 * 有A,B两个仓库需要用车，分别需要a，b辆车，保证车的数量是大于两地需求之和的。
 * 现在已知每辆车去两地能得到的利润为ai，bi计算如何分配使利润最大。
 * 问题分析
 * 可以根据使用动态规划的思想实现，类似背包问题，dp[k][a][b]表示对k辆车进行调度，AB两地分别需要a和b辆车。
 */
public class CarScheduling {

    static int MAXN = 1010;
    static int[][][] dp = new int[MAXN][MAXN][2];
    static int[][] profit = new int[MAXN][2];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int K = scan.nextInt();
        int A = scan.nextInt();
        int B = scan.nextInt();
        for (int i = 0; i < K; i++) {
            profit[i][0] = scan.nextInt();
            profit[i][1] = scan.nextInt();
        }
        for (int k = 1; k <= K; k++) {
            dp[1][0][(k + 1) & 1] = max(dp[1][0][k & 1], profit[k - 1][0]);
            dp[0][1][(k + 1) & 1] = max(dp[0][1][k & 1], profit[k - 1][1]);
        }
        for (int k = 1; k <= K; k++) {
            for (int i = 1; i <= A; i++) {
                for (int j = 1; j <= B; j++) {
                    dp[i][j][(k + 1) & 1] = dp[i][j][k & 1];
                    dp[i][j][(k + 1) & 1] = max(dp[i][j][(k + 1) & 1], dp[i - 1][j][k & 1] + profit[k - 1][0]);
                    dp[i][j][(k + 1) & 1] = max(dp[i][j][(k + 1) & 1], dp[i][j - 1][k & 1] + profit[k - 1][1]);
                }
            }
        }
    }


}
