package xp.oj.poj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 负数背包， 动态规划
 *
 * 问题描述
 * 有k个物品，每个物品包含两个属性s和f，任取若干个物品使两属性之和最大，且各属性之和不为负数。
 * 问题分析
 * 类似一个01背包，用dp[i][s]表示前i个物品第一个属性之和为s时最大的f之和。因为s可以取负值，所以这是一个负数背包，负数背包通常将dp的范围右移到正数区间，该题将区间右移100000。dp的方向也需要根据s的正负进行调整。
 * 注意点：初始化点为0点右移后的位置；题目中无解返回0。
 */
public class CowExhibition2184 {
    public static void main(String[] args) throws IOException {
        new CowExhibition2184().solve();
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
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), "");
        }

        return st.nextToken();
    }

    char readChar() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return st.nextToken().charAt(0);
    }

    int N;
    int[] dp = new int[200005];
    int[] sarr = new int[105];
    int[] farr = new int[105];
    int mid = 100000;
    int maxn = 200005;
    int IFN = 1000000007;
    int ans = 0;
    private void solve() throws IOException {
        N = readInt();
        for (int i = 0; i < N; i++) {
            sarr[i] = readInt();
            farr[i] = readInt();
        }
        computDP();
        out.println(ans);
        out.flush();
    }

    private void computDP() {
        Arrays.fill(dp, -IFN);
        dp[mid] = 0;
        for (int i = 0; i < N; i++) {
            if (sarr[i] > 0) {
                for (int j = maxn - 1; j >= sarr[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - sarr[i]] + farr[i]);
                }
            } else {
                for (int j = 0; j < maxn + sarr[i]; j++) {
                    dp[j] = Math.max(dp[j], dp[j - sarr[i]] + farr[i]);
                }
            }

        }
        for (int i = mid; i < maxn; i++) {
            if (dp[i] > 0) {
                ans = Math.max(ans, dp[i] + i - mid);
            }
        }

    }

    class Block implements Comparable<Block>{
        int h,a,c;
        Block(int h, int a, int c){
            this.h = h;
            this.a = a;
            this.c = c;
        }

        @Override
        public int compareTo(Block o) {
            return a - o.a;
        }
    }
}
