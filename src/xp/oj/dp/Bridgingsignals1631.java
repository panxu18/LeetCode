package xp.oj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 最大上升子序列， 动态规划
 *
 * 问题描述
 * 问题描述比较复杂，最后的意思就是图中所示的线条中，最多可以选出多少条不相交线条。
 * 问题分析
 * 线条用<a,b>表示，a表示左边的端口，b表示端口。两条线条不相交的条件为a<a'&b<b'，这是一个二维非降子序列问题，右边端口已经按升序排列。
 */
public class Bridgingsignals1631 {

    public static void main(String[] args) throws IOException {
        new Bridgingsignals1631().solve();
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

    int N, P;
    int[] ports = new int[40005];
    int[] dp = new int[40005];
    int ans;
    void solve() throws IOException {
        N = readInt();
        for (int i = 0; i < N; i++) {
            P = readInt();
            for (int j = 0; j < P; j++) {
                ports[j] = readInt();
            }
            LIS();
            out.println(ans);
        }
        out.flush();
    }

    void LIS() {
        int len = 0;
        for (int i = 0; i < P; i++) {
            int idx = Arrays.binarySearch(dp, 0, len, ports[i]);
            if (idx < 0) {
                int j = -idx - 1;
                dp[j] = ports[i];
                if (j == len){
                    len++;
                }
            }
        }
        ans = len;
    }
}
