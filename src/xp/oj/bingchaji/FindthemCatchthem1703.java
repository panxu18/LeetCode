package xp.oj.bingchaji;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * 并查集
 * 问题描述
 * 有N个嫌疑犯，它们属于两个犯罪团伙，目前得到的消息只能确定某两个嫌疑犯不在同一个团伙中，现在查询两个嫌疑犯是否在同一个团伙中。
 * 问题分析
 * 和食物链类似，这里两个嫌疑犯之间属于互斥的集合，通过取反使其能在同一集合中。
 */
public class FindthemCatchthem1703 {

    public static void main(String[] args) throws IOException {
        new FindthemCatchthem1703().solve();
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

    String readLine() throws IOException {
        String s;
        if (st != null &&st.hasMoreTokens()) {
            s = st.nextToken("");
        } else {
            s = in.readLine();
        }
        if (s == null) {
            throw new NoSuchElementException();
        }
        st = null;
        return s;

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

    int T, N, M;
    int[] par = new int[200005];
    int[] rank = new int[200005];

    private void solve() throws IOException {
        T = readInt();
        for (int t = 0; t < T; t++) {
            N = readInt();
            M = readInt();
            init();
            for (int i = 0; i < M; i++) {
                int c = readChar();
                int a = readInt();
                int b = readInt();
                if (c == 'A') {
                    if (isSame(a, b)) {
                        out.println("In the same gang.");
                    } else if (isSame(a, b+N) || isSame(b, a+N)) {
                        out.println("In different gangs.");
                    } else {
                        out.println("Not sure yet.");
                    }
                } else {
                    union(a, b+N);
                    union(b, a+N);
                }
            }
        }
        out.flush();
    }

    void init() {
        for (int i = 0; i < par.length; i++) {
            par[i] = i;
        }
        Arrays.fill(rank,0);
    }

    void union(int x, int y){
        int px = find(x);
        int py = find(y);
        if (px == py) {
            return;
        }
        if (rank[x] < rank[y]) {
            par[px] = py;
        } else {
            par[py] = px;
            if (rank[px] == rank[py]) {
                rank[px]++;
            }
        }
    }

    private int find(int x) {
        return par[x] == x ? x : (par[x] = find(par[x]));
    }

    private boolean isSame(int x, int y) {
        return find(x) == find(y);
    }
}
