package xp.oj.flow;

import java.io.*;
import java.util.Arrays;

/**
 * 关键边, 完全匹配
 *
 * 问题描述
 * 有N个矩形，矩形之间有重叠，有N个数在这些矩形区域内，现在将矩形和数字一一对应，保证对应关系唯一。
 * 问题分析
 * 不太理解题目意思，看了题解之后发现是将矩形和数字对应，而且不能出现模棱两可的对应关系，例如样例2中就无法唯一确定，矩形A可以和数字1或数字2对应时，矩形B都可以和另外一个数字对应，这就没有确定唯一的对应关系。样例1中，A必须对应4，如果A不对应4，那么剩下的矩形和数字无法一一对应。
 * 根据上面分析也可以发现，如果删除A和4的对应关系，图就无法完全匹配，就表示A和4的对应关系可以唯一确定下来。因此，算法也是采用这样的枚举策略，依次删除一条边，判断图是否能完全匹配，如果能完全匹配，表示该边是关键边。
 * 初始化偷懒导致无限wa
 */
public class SortingSlides1486 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new SortingSlides1486().solve();
    }

    int MAXN = 100;
    int N, M, K;
    Edge[] head = new Edge[MAXN];
    Rec[] recs = new Rec[MAXN];
    int[] match = new int[MAXN];
    boolean[] vis = new boolean[MAXN];
    int deletedu = -1, deletedv = -1;

    private void solve() throws IOException {
        int cases = 0;
        while ((N=read()) != 0){
            cases++;
            caseInit();
            for (int i = 0; i < N; i++) {
                recs[i] = new Rec(read(), read(), read(), read());
            }
            for (int i = 0; i < N; i++) {
                int x = read();
                int y = read();
                for (int j = 0; j < N; j++) {
                    if (recs[j].contains(x, y)) {
                        head[j] = new Edge(j, i + N, head[j]);
                    }
                }
            }
            biGraphMath(); // 初始匹配
            out.printf("Heap %d\n", cases);
            boolean flag = false;
            for (int i = 0; i < N; i++) {
                if (match[i] != -1){
                    int cp = match[i];
                    match[cp] = -1;
                    deletedu = i;
                    deletedv = cp;
                    Arrays.fill(vis,false);
                    if (!find(i)){
                        out.printf("(%c,%d) ",i+'A', cp-N+1);
                        flag = true;
                        match[cp] = i;
                    }

                }
            }
            if (flag){
                out.printf("\n\n");
            } else {
                out.printf("none\n\n");
            }
        }
        out.flush();
    }

    private void caseInit() {
        Arrays.fill(match, -1);
        Arrays.fill(head, null);
        deletedu = -1;
        deletedv = -1;
    }

    private void biGraphMath() {
        for (int i = 0; i < N; i++) {
            if (match[i] == -1){
                Arrays.fill(vis, false);
                find(i);
            }
        }
    }

    private boolean find(int v) {
        vis[v] = true;
        for (Edge e = head[v]; e != null; e = e.next) {
            if (deletedu == v && deletedv == e.to) {
                // 删除边
                continue;
            }
            int cp = match[e.to];
            if (cp == -1 || !vis[cp] && find(cp)) {
                match[v] = e.to;
                match[e.to] = v;
                return true;
            }
        }
        return false;
    }

    private class Edge {
        int from, to;
        Edge next;
        boolean valid;

        public Edge(int from, int to, Edge next) {
            this.from = from;
            this.to = to;
            this.next = next;
            this.valid = true;
        }
    }

    private class Rec {
        int xmin, xmax, ymin, ymax;

        public Rec(int xmin, int xmax, int ymin, int ymax) {
            this.xmin = xmin;
            this.xmax = xmax;
            this.ymin = ymin;
            this.ymax = ymax;
        }

        private boolean contains(int x, int y){
            return x >= xmin && x <= xmax && y >= ymin && y <= ymax;
        }
    }
}
