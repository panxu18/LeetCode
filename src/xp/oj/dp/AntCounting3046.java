package xp.oj.dp;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 多重集合组合数
 *
 * 问题描述
 * 有n种物品，每种有ai个，不同物品可以区分，相同物品无法区分，从这些物品中取出[s,b]个物品，有多少种组合方法。
 * 问题分析
 * n个物品选择m个，肯定是考虑选择0个1个...an个第n种物品，所以可以得到一个简单递推式，但是可以发现这个递推中需要枚举ai种情况，这个和前n项和类似，这里是前ai项和。注意边界条件，选择0个物品的方案数是1。另外防止出现负数，需要加上mod。
 * 注意初始条件，dp[0][0] = 1。
 */
public class AntCounting3046 {
    public static void main(String[] args) {
        new AntCounting3046().solve();
    }

    int mod = 1000000;
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        T = in.nextInt();
        A = in.nextInt();
        S = in.nextInt();
        B = in.nextInt();
        for (int i = 0; i < A; i++) {
            arr[in.nextInt()]++;
        }
        out.println(getAns());
        out.flush();
    }

    int T, A, S, B;
    int[] arr = new int[1005];
    int[][] dp = new int[2][100005];
    int getAns(){
        Arrays.fill(dp[0], 0); // 边界
        for (int i = 1; i <= T; i++) {
            dp[i&1][0] = 1; // 取0个物品方案数为1
            for (int j = 1; j <= B; j++) {
                if (j >= arr[i] + 1) {
                    dp[i&1][j] = dp[(i+1)&1][j] + dp[i&1][j - 1] - dp[(i+1)&1][j - 1 - arr[i]] + mod; // 防止出现负数
                } else {
                    dp[i&1][j] = dp[(i+1)&1][j] + dp[i&1][j - 1];
                }
                dp[i&1][j] = dp[i&1][j] % mod;
            }
        }
        int ans = 0;
        for (int i = S; i <= B; i++) {
            ans = (dp[T&1][i] + ans) % mod;
        }
        return ans;
    }
}
