package xp.oj.bittree;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 坐标离散化， 树状数组，二维矩阵扫描线
 *
 * 问题描述
 * 无限大的棋盘上，在横向和纵向上被包围的白子会变成黑子，求最终黑子个数。
 * 问题分析
 * 问题在于求每个线段和其他线段有多少个交点。最开始的思路是枚举水平的扫描线，通过树状数组计算出与该扫描线相交的线段。
 * 树状数组通过判断每条扫描线上的黑色点是否是一个垂直线段的端点，然后更新树状数组。但是这样实现后总是TLE，
 * 不知道是什么原因，感觉每条扫描线上的黑色点必须逐个更新，就算使用双树状数组也也需要根据每个黑色点更新树状数组。
 * 后来改用双树状数组AC了，感觉和单树状数组的复杂度只是常数差距。同样是枚举水平扫描线，但是这里通过计算每个垂直方向的线
 * 段和多少条水平线段相交。可以通过树状数组维持每个坐标为x的垂直线上有多条水平线段穿过，每条扫描线两端之间的垂直线都和
 * 该扫描线相交（这是一句废话），所以这是一个区间更新的问题。通过枚举扫描线上的每一个黑色点，计算它所在的垂直线段和其
 * 他扫描线的交点。这里没有保存这条垂直线段，而是把计算过的垂直线段对应的交点从树状数组中减去（单点更新）。
 *
 */
public class InnerVertices3109 {

    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new InnerVertices3109().solve();
    }

    int MAXN = 100005;
    long ans = 0;
    int N ;
    int[] X = new int[MAXN];
    int[] Y = new int[MAXN];
    int[] bit0 = new int[MAXN];
    int[] bit1 = new int[MAXN];
    Line[] lines = new Line[MAXN];
    int[] temp = new int[MAXN];
    int W, H;
    private void solve() throws IOException {
        N = read();
        for (int i = 0; i < N; i++) {
            X[i] = read();
            Y[i] = read();
        }
        W = compress(X, N);
        H = compress(Y, N);
        for (int i = 0; i < N; i++) {
            if (lines[Y[i]] == null){
                lines[Y[i]] = new Line();
            }
            lines[Y[i]].points.add(X[i]);
        }

        ans = doSolve() + N;
        out.println(ans);
        out.flush();
    }

    /**
     * 坐标压缩,压缩后的数保存在原始数组中
     * @param a 需要压缩的数组
     * @param len 数组有效数长度
     * @return 返回坐标压缩后的数组长度
     */
    private int compress(int[] a, int len){
        System.arraycopy(a, 0, temp, 0, len);
        Arrays.sort(temp, 0, len);
        int newLen = unique(temp,len);
        for (int i = 0; i < len; i++) {
            a[i] = Arrays.binarySearch(temp, 0, newLen, a[i])+1;
        }
        return newLen;
    }

    /**
     * 数组去重
     * @param a 原始数组
     * @param n 数组长度
     * @return 返回去重后的数组长度
     */
    private int unique(int[] a, int n) {
        int head = 0, tail = 0;
        for (int i = 0; i < n; i++) {
            if (head == tail || a[i]!=a[tail-1]){
                a[tail++] = a[i];
            }
        }
        return tail;
    }

    boolean[] used = new boolean[MAXN];
    private long doSolve() {
        init();
        int res = 0;
        Line line = null;
        for (int i = 1; i <= H; i++) {
            line = lines[i];
            if (line == null){
                continue;
            }
            Collections.sort(line.points);

            for (int x :
                    line.points) {
                int s = sum(x) - sum(x-1);
                if (used[x]){
                    res += s;
                }
                add(x, x, -s-1);
                used[x] = true;
            }
            add(line.points.get(0), line.points.get(line.points.size()-1), 1);

        }
        return res;
    }

    private int sum(int x) {
        return sum(bit0,x)*x + sum(bit1,x);
    }

    private void add(int s, int t, int a){
        if (s == t){
            add(bit1, s, a);
            return;
        }
        add(bit0, s, a);
        add(bit0, t+1, -a);
        add(bit1, s, -(s-1)*a);
        add(bit1, t + 1, t*a);
    }


    private void init(){
        Arrays.fill(bit0,0);
        Arrays.fill(bit1,0);
    }
    private int sum(int[] bit, int i){
        int res = 0;
        while (i > 0){
            res = res + bit[i];
            i -= i&-i;
        }
        return res;
    }

    private void add(int[] bit, int i, int a){
        while (i<=MAXN){
            bit[i] += a;
            i += i&-i;
        }
    }

    class Line{
        ArrayList<Integer> points;

        public Line() {
            this.points = new ArrayList<Integer>();
        }
    }
}
