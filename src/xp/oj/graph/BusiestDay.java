package xp.oj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 2SAT问题，强连通分量
 */
public class BusiestDay {

    static int[] S;
    static int[] T;
    static int[][] graph;
    static int[][] regraph;
    static boolean[] used;
    static int[] cmp;
    static int[] seq;
    static int cnt;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // 处理输入
        n = Integer.parseInt(bf.readLine());
        S = new int[2 * n];
        T = new int[2 * n];
        used = new boolean[2 * n];
        cmp = new int[2 * n];
        seq = new int[2 * n];
        for (int i = 0; i < n; i++) {
            String[] str = bf.readLine().split(" ");
            int start =  time2Int(str[0]);
            int end = time2Int(str[1]);
            int p = time2Int(str[2]);
            S[i] = start;
            T[i] = start + p;
            S[i + n] = end - p;
            T[i + n] = end;
        }

        // 构图
        graph = new int[2 * n][2 * n + 1];
        regraph = new int[2 * n][2 * n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (covered(i, j)) {
                    addEdge(i, j+n);
                    addEdge(j, i+n);
                }
                if (covered(i, j + n)) {
                    addEdge(i, j);
                    addEdge(j + n, i+n);
                }
                if (covered(i + n, j)) {
                    addEdge(i + n, j+n);
                    addEdge(j, i);
                }
                if (covered(i + n, j + n)) {
                    addEdge(i + n, j);
                    addEdge(j + n, i);
                }
            }
        }

        // 强连通分量分解
        ssc();

        // 判断是否有解
        for (int i = 0; i < n; i++) {
            if (cmp[i] == cmp[i + n]) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
        for (int i = 0; i < n; i++) {
            if (cmp[i] > cmp[i + n]) {
                System.out.printf("%02d:%02d %02d:%02d\n",S[i] / 60, S[i] % 60, T[i] / 60, T[i] % 60);
            } else {
                System.out.printf("%02d:%02d %02d:%02d\n",S[i + n] / 60, S[i + n] % 60, T[i + n] / 60, T[i + n] % 60);
            }
        }
    }

    static boolean covered(int i, int j) {
       return Math.min(T[i], T[j]) > Math.max(S[i], S[j]);
    }

    private static void addEdge(int f, int t) {
        int index = ++graph[f][0];
        graph[f][index] = t;
        index = ++regraph[t][0];
        regraph[t][index] = f;
    }

    private static void ssc() {

        // 正向dfs
        for (int i = 0; i < 2*n; i++) {
            if (!used[i]) {
                dfs(i);
            }
        }

        // 反向dfs
        Arrays.fill(used, false);
        int k = 0;
        for (int i = 2 *n - 1; i >= 0 ; i--) {
            if (!used[seq[i]]) {
                redfs(seq[i], k++);
            }
        }

    }

    private static int dfs(int v) {
        used[v] = true;
        int res = 1;
        for (int i = 1; i <= graph[v][0]; i++) {
            if (!used[graph[v][i]]) {
                res += dfs(graph[v][i]);
            }
        }
        seq[cnt++] = v;
        return res;
    }

    private static int redfs(int v, int k) {
        used[v] = true;
        int res = 1;
        for (int i = 1; i <= regraph[v][0]; i++) {
            if (!used[regraph[v][i]]) {
                res += redfs(regraph[v][i], k);
            }
        }
        cmp[v] = k;
        return res;
    }

    private static int time2Int(String str) {
        int res = 0;
        for (StringTokenizer stringTokenizer = new StringTokenizer(str, ":"); stringTokenizer.hasMoreTokens(); ) {
            res = res * 60 + Integer.parseInt(stringTokenizer.nextToken());

        }
        return res;
    }

}
