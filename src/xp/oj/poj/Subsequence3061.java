package xp.oj.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * 尺取法
 *
 * 问题描述
 * 给一个长度为N的序列，计算和不小于S的最短连续子序列的长度。
 * 问题分析
 * 朴素算法就是计算从第i个元素开始和不小于S的连续序列长度，当找到一个子序列之后就可以计算i+1了。在实现上就是一个先进先出队列。
 */
public class Subsequence3061 {

    public static void main(String[] args) throws IOException {
        new Subsequence3061().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    double readDouble() throws IOException {
        if(canRead()) {
            return Double.parseDouble(st.nextToken());
        }
        throw new NoSuchElementException();
    }

    int readInt() throws IOException {
        if (canRead()) {
            return Integer.parseInt(st.nextToken());
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

    int T, N, S;
    int INF = 1000000007;
    int[] arr = new int[100005];
    private void solve() throws IOException {
        T = readInt();
        for (int t = 0; t < T; t++) {
            N = readInt();
            S = readInt();
            int head = 0, tail = 0, sum = 0, ans = INF;
            for (int i = 0; i < N; i++) {
                int a = readInt();
                arr[tail++] = a;
                sum += a;
                while (sum >= S && head < tail){
                    ans = Math.min(ans, tail - head);
                    sum -= arr[head++];
                }
            }
            out.println(ans == INF ? 0 : ans);
        }
        out.flush();
    }
}
