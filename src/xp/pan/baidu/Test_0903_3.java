package xp.pan.baidu;

import java.util.Scanner;

/**
 * 动态规划
 * 有N阶楼梯，假设每次最多能走M阶楼梯，且每次走的台阶数不能和前两次一样，计算有多少种走法，结果对10e9 + 7求模。
 * 很显然这是一个3维动态规划，优化后时间复杂度为O(n*m^3)。动态转移方程为
 * dp[i][j][k] = sum(dp[i-j-k][p][q])， 0<p,q<=M, q!=j,q!=k,p!=j,p!=k
 */
public class Test_0903_3 {
    private static int N, M;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
    }
}
