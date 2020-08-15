package xp.oj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 区间最大值
 *
 * 使用dp，使用dp[t][m][id]表示t时刻在某一个苹果树下有m次移动机会时最多可以吃到的苹果数量。转移方程为dp[t][m][id] = max{dp[t + i][m-1][id^1] + dp[t][0][id] - dp[t + i][0][id] | t + i <= T + 1, m > 0}。
 * 利用空间压缩dp，最后是一个二维的dp。
 * 通过观察转移方程可以发现右边是求区间最值，可以优化为dp[t][m][id] = dp[t][0][id] + max{dp[t + i][m-1][id^1] - dp[t + i][0][id] | t + i <= T + 1, m > 0},
 * dp[t][m][id] = dp[t][0][id] + max{dp[t + 1][m][id] - dp[t + i][0][id], dp[t][m-1][id^1] - dp[t][0][id]},其中0<=t<T。
 */
public class AppleCatching2385 {

    public static void main(String[] args) throws IOException {
        new AppleCatching2385().solve();
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

    int T, M;
    int[][] dp = new int[1005][2];
    int[] apples = new int[1005];
    int[][] sum = new int[2][1005];
    void solve() throws IOException {
        T = readInt();
        M = readInt();
        for (int i = 0; i < T; i++) {
            if (readInt() == 1)
                apples[i] = 1;
        }
        int s = 0;
        for (int i = T - 1; i >= 0 ; i--) {
            s += apples[i];
            sum[0][i] = s;
            sum[1][i] = T - i - s;
        }
//        computeDP();
        fastDP();
        out.println(dp[0][0]);
        out.flush();
    }

    private void computeDP() {
        for (int i = 0; i <= M; i++) {
            for (int j = 0; j < T; j++) {
                for (int k = j; k <= T ; k++) {
                    dp[j][0] = Math.max(dp[k][1] + sum[0][j] - sum[0][k], dp[j][0]);
                    dp[j][1] = Math.max(dp[k][0] + sum[1][j] - sum[1][k], dp[j][1]);
                }
            }
        }
    }

    private void fastDP() {
        for (int i = 0; i <= M; i++) {
            for (int j = T - 1; j >= 0; j--) {
                int temp = dp[j][0];
                dp[j][0] = sum[0][j] + Math.max(dp[j + 1][0] - sum[0][j + 1], dp[j][1] - sum[0][j]);
                dp[j][1] = sum[1][j] + Math.max(dp[j + 1][1] - sum[1][j + 1], temp - sum[1][j]);
            }
        }
    }
}
