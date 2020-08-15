package xp.oj.poj.graph;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 二分图判定
 *
 * 问题描述
 * 判断给出的图是否是二分图
 * 问题分析
 * 二分图判定可以对没有染色的顶点染色，然后通过dfs对节点染色，如果染色中有相邻顶点颜色相同，说明不是二分图。
 */
public class BugLife2492 {

    public static void main(String[] args) {
        new BugLife2492().solve();
    }

    int s, n, m, X, Y;
    Edge[] head = new Edge[2005];
    int[] color = new int[2005];
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        s = in.nextInt();
        for (int i = 1; i <= s; i++) {
            n = in.nextInt();
            m = in.nextInt();
            Arrays.fill(head, 0, n + 1, null);
            Arrays.fill(color, 0, n + 1,0);
            for (int j = 0; j < m; j++) {
                int a = in.nextInt();
                int b = in.nextInt();
                addEdges(a, b);
            }
            out.printf("Scenario #%d:\n", i);
            out.println(check() ? "No suspicious bugs found!" : "Suspicious bugs found!");
            out.println("");
        }
        out.flush();
    }

    private void addEdges(int a, int b){
        head[a] = new Edge(b, head[a]);
        head[b] = new Edge(a, head[b]);
    }

    private boolean check(){
        for (int i = 1; i <= n ; i++)
            if (color[i] == 0 && !dfs(i, 1)) return false;
        return true;
    }

    private boolean dfs(int v, int c) {
        color[v] = c;
        for (Edge e = head[v]; e != null; e = e.next) {
            if (color[e.to] == c) return false;
            if (color[e.to] == 0 && !dfs(e.to, -c)) return false;
        }
        return true;
    }

    class Edge{
        int to;
        Edge next;

        public Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }
}
