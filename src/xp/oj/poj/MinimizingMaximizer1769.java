package xp.oj.poj;

import java.io.*;

import static java.lang.Math.min;

/**
 * 线段树
 *
 * 问题描述
 * 有M个排序器，它们可以接收长度为N的序列，第i个排序器可以将序列的si-ti这一部分区间排序，排序器依次执行。
 * 计算最少选择几个排序器，可以计算出序列中的最大值。
 * 问题分析
 * 选择最少的区间覆盖整个范围，且使后面的区间能和前面的区间重叠。注意区间要从1开始逐渐上升直到N。对于一个区间，
 * 如果可以将最大值进行排序，那么最大值会被排到区间右端点。如何判断该区间是否能对最大值排序？只要前面的区间能将最大值
 * 放到当前区间范围内，那么当前区间就可以处理到最大值。因此保存最大值是否能被移动到某一个位置，以及移动到该位置的最少
 * 次数。每次需要查询当前区间内移动的最小值，同时更新右端点所在位置的最小值，这种区间查询，单点更新适合使用线段树实现。
 */
public class MinimizingMaximizer1769 {
    public static void main(String[] args) throws IOException {
        new MinimizingMaximizer1769().solve();
    }
    PrintWriter out = new PrintWriter(System.out);
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    double readDouble() throws IOException {
        in.nextToken();
        return in.nval;
    }

    int MAXN = 50005;
    int MAXM = 500005;
    int INF = 1000000007;
    int N, M;
    int[][] A = new int[MAXM][2];
    long ans;
    private void solve() throws IOException {
        N = (int) readDouble();
        M = (int) readDouble();
        for (int i = 0; i < M; i++) {
            A[i][0] = (int) readDouble();
            A[i][1] = (int) readDouble();
        }

        doSovle();
        ans = query(N,N, 1, 1,N);
        out.println(ans);
        out.flush();

    }

    private void doSovle() {
        build(1, 1, N);
        change(1, 0, 1, 1, N);
        for (int i = 0; i < M; i++) {
            int min = query(A[i][0], A[i][1], 1, 1, N);
            if (min < INF){
                change(A[i][1], min+1, 1, 1, N);
            }
        }
    }

    int[] tree = new int[MAXN<<2];

    private void build(int v, int l, int r){
        if (l == r) {
            tree[v] = INF;
            return;
        }
        int mid = (l + r) >>1;
        build(v<<1, l, mid);
        build(v<<1|1, mid+1, r);
        up(v);
    }

    private void up(int v) {
        tree[v] = min(tree[v<<1], tree[v<<1|1]);
    }

    private int query(int s, int t, int v, int l, int r) {
        if (s <= l && t>= r) {
            return tree[v];
        }
        int mid = (l+r)>>1;
        int min = INF;
        if (s <= mid) {
            min = min(query(s, t, v<<1, l, mid), min) ;
        }
        if (mid < t){
            min = min(min, query(s, t, v<<1|1, mid +1, r));
        }
        return min;
    }

    private void change(int s,int val, int v, int l, int r) {
        if (l == r) {
            tree[v] = min(tree[v], val);
            return;
        }
        int mid = (l+r)>>1;
        if (s<=mid) {
            change(s, val, v<<1, l, mid);
        } else {
            change(s, val, v<<1|1, mid+1, r);
        }
        up(v);
    }
}

