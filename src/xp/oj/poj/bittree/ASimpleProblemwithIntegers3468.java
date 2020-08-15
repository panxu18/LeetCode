package xp.oj.poj.bittree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * 树状数组，区间更新，区间查询
 *
 *问题描述
 * 给N个数的序列，进行以下两种操作，
 * 1、将区间l-r的数增加a
 * 2、查询区间l-r的数的和
 * 问题分析
 * 区间和可以使用线段树这种数据结构，将数据的更新和查询操作缩减为logn数量级。这里使用树状数组，对于区间更新线段树可以使
 * 用延迟更新，而树状数组可以使用辅助数组，设一个前n项和为si = bit1*i + bit2,这里bit1就是用来辅助区间更新的树状数组。
 */
public class ASimpleProblemwithIntegers3468 {

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
        new ASimpleProblemwithIntegers3468().solve();
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

    int MAXN = 100005;
    long INF = 1000000007;
    int N, Q;
    private void solve() throws IOException {
        N = readInt();
        Q = readInt();
        for (int i = 1; i <= N; i++) {
            add(bit1, i, readInt());
        }
        for (int i = 0; i < Q; i++) {
            char c = readChar();
            if (c == 'Q') {
                out.println(query(readInt(), readInt()));
            } else {
                change(readInt(), readInt(), readInt());
            }
        }
        out.flush();
    }

    private void change(int a, int b, int c) {

        add(bit1, a, -(a-1)*c);
        add(bit2, a, c);
        add(bit1, b+1, b*c);
        add(bit2, b+1, c);
    }

    private long query(int a, int b) {
        long r1 = sum(bit1,b) - sum(bit1, a - 1);
        long r2 = sum(bit2, b) * b - sum(bit2, a - 1) * (a-1);

        return r1 + r2;
    }



    long[] bit1 = new long[MAXN]; // 常数项
    long[] bit2 = new long[MAXN]; // x^1项

    private  void add(long[] bit, int i, long a){
        while (i<=N){
            bit[i] += a;
            i += i&-i;
        }
    }

    private long sum(long[]bit, int i) {
        long res = 0;
        while (i > 0) {
            res += bit[i];
            i -= i&-i;
        }
        return res;
    }
}
