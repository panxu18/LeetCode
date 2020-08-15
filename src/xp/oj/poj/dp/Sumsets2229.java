package xp.oj.poj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 划分数dp
 *
 * 问题描述
 * 将一个数分解为2的幂次方的数的和，计算有多少中分解方法。
 * 问题分析
 * 这里有一个简单的解法，如果a是奇数，那么至少有一个1，组合数就只用考虑a-1。如果a是偶数，可以分为包含1和不包含1两种互斥的情况。
 * 当然也可以使用划分数计数，对于a可以分为每组至少放一个和至少有一组为空两种互斥情况。首先计算有多少组，然后套用划分数dp计算。
 */
public class Sumsets2229 {

    public static void main(String[] args) throws IOException {
        new Sumsets2229().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    int readInt() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return Integer.parseInt(st.nextToken());
    }

    String readLine() throws IOException {
        return in.readLine();
    }

    int N;
    int[] dp = new int[1000005];
    int mod = 1000000000;
    void solve() throws IOException {
        N = readInt();
//        fastDP();
        bagDP();
        out.println(dp[N]);
        out.flush();
    }

    void fastDP() {
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= N; i++) {
            if ((i&1) == 0) {
                dp[i] = (dp[i/2] + dp[i - 1]) % mod;
            } else {
                dp[i] = dp[i - 1];
            }
        }
    }

    void bagDP() {
        int m = 32 - Integer.numberOfLeadingZeros(N - 1); // 1右移m位大于等于N
        dp[0] = 1;
        for (int i = 0; i <= m ; i++) {
            for (int j = 1; j <= N ; j++) {
                if (j >= (1 << i)) {
                    dp[j] = (dp[j] + dp[j - (1 << i)]) % mod;
                }
            }
        }
    }

}
