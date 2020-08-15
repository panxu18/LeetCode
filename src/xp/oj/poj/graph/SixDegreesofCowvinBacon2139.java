package xp.oj.poj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * 多源最短路径，floyd算法
 *
 * 问题描述
 * 有n头奶牛，如果奶牛在同一部电影中工作过，那么这些奶牛就是有关系的，关系距离为1当然也可
 * 以通过其他奶牛产生间接的关系，问你那头奶牛的与其他奶牛的平均关系距离最小。
 * 问题分析
 * 多源最短路径，直接使用floyd算法，然后计算路径平均值。
 * 注意点：最后结果是取整。
 */
public class SixDegreesofCowvinBacon2139 {

    public static void main(String[] args) throws IOException {
        new SixDegreesofCowvinBacon2139().solve();
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

    int N, M;
    int[][] graph = new int[305][305];
    int[] movArr = new int[305];
    int INF = 1000000007;
    int ans = INF;
    private void solve() throws IOException {
        N = readInt();
        M = readInt();

        for (int i = 0; i < 305; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            int mov = readInt();
            for (int j = 0; j < mov; j++) {
                movArr[j] = readInt();
                for (int k = 0; k < j; k++) {
                    graph[movArr[j]][movArr[k]] = 1;
                    graph[movArr[k]][movArr[j]] = 1;
                }
            }
        }
        floyd();
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                if (i!= j && graph[i][j] != INF) {
                    sum += graph[i][j];
                }
            }
            ans = Math.min(ans, sum * 100/(N-1));
        }
        out.println(ans);
        out.flush();
    }

    void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }

}
