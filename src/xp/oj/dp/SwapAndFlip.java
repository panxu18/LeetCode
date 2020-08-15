package xp.oj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import static java.lang.Math.min;

/**
 * 状态压缩, 动态规划
 *
 * 问题描述
 * 有n张牌，每张牌的正反面都有一个数字，每次只能将相邻两张牌翻转并交换位置。计算最少需要多少次交换能使牌面非降排列。
 * 问题分析
 * 正经解法应该是使用状态压缩dp，因为只能枚举所有情况才能知道最后的结果。用集合S表示已排好序的牌，此时操作的次数为c，剩下的牌对应集合U，在U中选择一张牌j加入S。因为加入到S的过程中会破坏S中牌面的状态，所以为了保证无后效性，将j作为S最后一张牌。如果j比S最后一张牌小，则不能加入到S中，这里需要证明这种策略是可行的。判断第i张牌的值，根据i的开始位置和结束位置判断。
 */
public class SwapAndFlip {

    public static void main(String[] args) throws IOException {
        new SwapAndFlip().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    int readInt() throws IOException {
        if (canRead()) {
            return Integer.parseInt(st.nextToken());
        }
        throw new NoSuchElementException();
    }
    long readLong() throws IOException {
        if (canRead()) {
            return Long.parseLong(st.nextToken());
        }
        throw new NoSuchElementException();
    }

    char readChar() throws IOException {
        if (canRead()) {
            return st.nextToken().charAt(0);
        }
        throw new NoSuchElementException();
    }

    boolean canRead() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String s = in.readLine();
            if (s != null) {
                st = new StringTokenizer(s, " ");
            } else {
                return false;
            }
        }
        return true;
    }

    int MAXN = 20;
    int INF = 1000000007;
    int N;
    int[] A = new int[MAXN], B = new int[MAXN];
    private void solve() throws IOException {
        N = readInt();
        for (int i = 0; i < N; i++) {
            A[i] = readInt();
        }
        for (int i = 0; i < N; i++) {
            B[i] = readInt();
        }
        dosolve();
        int ans = INF;
        for (int i = 0; i < N; i++) {
            ans = min(ans, dp[(1<<N)-1][i]);
        }
        out.println(ans == INF ? -1 : ans);
        out.flush();
    }

    int[][] dp = new int[1<<MAXN][MAXN];
    private void dosolve() {
        for (int S = 0; S < (1 << N); S++) {
            Arrays.fill(dp[S], INF);
        }
        for (int i = 0; i < N; i++) {
            dp[1<<i][i] = 0;
        }
        for (int S = 0; S < (1 << N); S++) {
            for (int i = 0; i < N; i++) {
                if ((S>>i&1) == 1) {
                    // 最后一张牌是i
                    int c = Integer.bitCount(S);
                    int vi = (c-1&1) == (i&1) ? A[i] : B[i];
                    int cost = c;
                    for (int j = 0; j < N; j++) {
                        if ((S>>j&1) == 1) {
                            cost--;
                            continue;
                        }
                        int vj = (c&1) == (j&1)? A[j] : B[j];
                        if (vj >= vi) {
                            dp[S|1<<j][j] = min(dp[S|1<<j][j], dp[S][i] + cost);
                        }
                    }
                }
            }
        }
    }
}
