package xp.oj.bittree;

import java.io.*;
import java.util.Arrays;

/**
 * 二维树状数组，单点更新、单点查询
 *
 * 问题描述
 * 给定一个矩阵，初始化为0，现在可以进行两种操作，一种是查询某个点的值是 0 还是 1。另一种是让这个矩阵的一个子矩阵内
 * 的值取反。
 * 问题分析
 * 这个题简单点看的话是一个区间更新单点查询的二维树状数组。但是，每次操作并不是传统的区间加上一个数的操作，而是取反，
 * 所以一点的状态可以通过被反转的次数计算出来。记录区间操作次数可以通过取区间4个顶点，来表示一个这个区间内的点被反转，
 * 类似与一维树状数组区间更新中用的辅助数组。
 *
 */
public class Matrix2155 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    String readStr() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {
        new Matrix2155().solve();
    }

    int MAXN = 1005;
    int N, T, M;
    int[][] bit = new int[MAXN][MAXN];
    private void solve() throws IOException {
        T = read();
        for (int t = 0; t < T; t++) {
            N = read();
            M = read();
            init();
            for (int i = 0; i < M; i++) {
                String q = readStr();
                if ("C".equals(q)){
                    int x1 = read();
                    int y1 = read();
                    int x2 = read();
                    int y2 = read();
                    change(x1, y1, x2, y2);
                } else {
                    int x = read();
                    int y = read();
                    out.println(sum(x,y)&1);
                }
            }
            out.println();
        }
        out.flush();
    }

    private void change(int x1, int y1, int x2, int y2) {

        add(x1,y1, 1);
        add(x1,y2+1, 1);
        add(x2+1,y1, 1);
        add(x2+1,y2+1, 1);
    }

    // 二维树状数组
    private long sum(int x, int y){
        long res = 0;
        while (x > 0){
            int t = y;
            while (t > 0){
                res += bit[x][t];
                t -= t &-t;
            }
            x -= x&-x;
        }
        return res;
    }

    private void add(int x, int y, long a){
        while (x <= N){
            int t = y;
            while (t <= N){
                bit[x][t] += a;
                t += t & -t;
            }
            x += x & -x;
        }

    }

    private void init(){
        for (int i = 0; i < MAXN; i++) {
            Arrays.fill(bit[i],0, MAXN, 0);
        }

    }
}
