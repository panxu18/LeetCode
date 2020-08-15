package xp.oj.tanxin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 贪心，最大区间覆盖
 *
 * 问题描述
 * 在一维坐标轴上，给定若干个区间，选择最少的区间覆盖整个坐标轴。
 * 问题分析
 * 第一步，选择能覆盖到坐标0，且结束坐标最大的区间，如果选择的区间为[li,ri]，那么第二步选择能覆盖到ri+1，且结束坐标最大
 * 的区间。依次进行下去直到所有坐标被覆盖。
 * 对坐标s，只有左边界小于等于s的区间能覆盖s，从中找到右边界最大的区间t，如果t小于s说明没有区间能覆盖到坐标s，
 * 如果能覆盖s，则更新s为下一个需要覆盖的坐标即t+1。直到所有区间遍历完或者所有坐标都被覆盖结束。
 */
public class CleaningShifts2376 {

    public static void main(String[] args) throws IOException {
        new CleaningShifts2376().solve();
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

    int N, T;
    Range[] arr = new Range[25005];
    int ans = 0;
    void solve() throws IOException {
        N = readInt();
        T = readInt();
        for (int i = 0; i < N; i++) {
            arr[i] = new Range(readInt(), readInt());
        }

        Arrays.sort(arr, 0, N);
        int s = 1;
        int t = 0;
        int i = 0;
        while (s <= T) {
            if (i < N && arr[i].l <= s) {
                t = Math.max(t, arr[i++].r);
            } else if (t < s){
                break;
            } else {
                s = t + 1;
                ans++;
            }
        }
        out.println(s > T ? ans : -1);
        out.flush();

    }

    class Range implements Comparable<Range>{
        int l, r;

        Range(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public int compareTo(Range o) {
            if (l == o.l) {
                return o.r - r;
            }
            return l - o.l;
        }
    }
}
