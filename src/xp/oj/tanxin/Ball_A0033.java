package xp.oj.tanxin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 贪心
 *
 * 问题描述
 * 有十个数，判断是否能分成两个递增序列。
 * 问题分析
 * 可以使用搜索，这里是贪心，假设前i个数已经得到两个递增数组，第i+1个数加入肯定选择最后一个数比较大的数组，这样后面较小
 * 的数才能添加到另外一个数组。
 */
public class Ball_A0033 {

    public static void main(String[] args) throws IOException {
        new Ball_A0033().solve();
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

    String next() throws IOException {
        while (st == null  || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return st.nextToken();
    }

    long N;
    char[][] arr = new char[105][2105];
    boolean[][] vis = new boolean[105][2105];
    void solve() throws IOException {
        N = readInt();
        out: for (int i = 0; i < N; i++) {
            String[] strs = in.readLine().split(" ");
            int[] arr = new int[strs.length];
            for (int j = 0; j < strs.length; j++) {
                arr[j] = Integer.parseInt(strs[j]);
            }
            int l = 0, r = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] > l) {
                    l = arr[j];
                } else if (arr[j] > r) {
                    r = arr[j];
                } else {
                    out.println("NO");
                    continue out;
                }
            }
            out.println("YES");
        }
        out.flush();
    }
}
