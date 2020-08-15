package xp.oj;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HousewifeWind {

    private static int n;
    private static Edge[] line; // 边集合
    private static int[] head; // 顶点只保存第一个边，其他边通过链表连接
    private static int[] to; // 邻接表
    private static int[] next; // 邻接表

    private static int cnt;

    private static int[] size; // 统计子树大小
    private static int[] son; // 重儿子
    private static int[] fa; // 父节点
    private static int[] vDepth; // 顶点深度
    private static int[] vSeq; // 顶点先序遍历的序号
    private static int num;
    private static int[] vTop; // 顶点所在的重边
    private static long[] weigth; // 存储点权
    private static long[] sum; // 线段树


    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        n = in.nextInt();
        int q = in.nextInt();
        int s = in.nextInt();
        init();
        for (int i = 1; i < n; i++) {
            line[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
            addEdge(line[i].from, line[i].to);
        }
        dfsMarkSon(1, 0, 0);
        treeFractor(1, 1);
        weigthTransfer();
        lineTreeInit(1, n, 1);

        while(q-- > 0){
            if(0 == in.nextInt()){
                int v = in.nextInt() ;
                out.println(change(s , v)) ;
                s = v ;
            }
            else update(1 , n , 1 , vSeq[line[in.nextInt()].from] , in.nextLong()) ;
        }

        out.flush();
    }

    // 参数初始化
    private static void init() {
        line = new Edge[n + 1];
        size = new int[n + 1];
        vTop = new int[n + 1];
        son = new int[n + 1];
        vDepth = new int[n + 1];
        vSeq = new int[n + 1];
        fa = new int[n + 1];

        head = new int[n + 1];
        to = new int[2 * n + 1];
        next = new int[2 * n + 1];
        Arrays.fill(head, -1);
        Arrays.fill(son, -1);

        weigth = new long[n + 1];
        sum = new long[n << 2];
        cnt = num = 0;

    }

    // 从s节点到t的路径距离
    private static long change(int v, int u) {

        long res = 0;
        while (vTop[v] != vTop[u]) {
            if (vDepth[vTop[v]] < vDepth[vTop[u]]) {
                v ^= u; u ^= v; v ^= u;
            }
            res += query(vSeq[vTop[v]], vSeq[v], 1, 1, n);
            v = fa[vTop[v]];
        }
        if (v == u) {
            return res;
        }
        if (vDepth[v] > vDepth[u]) {
            v ^= u; u ^= v; v ^= u;
        }
        return res + query(vSeq[son[v]], vSeq[u], 1, 1, n);
    }

    private static void addEdge(int a, int b) {
        to[cnt] = b; next[cnt] = head[a]; head[a] = cnt++;
        to[cnt] = a; next[cnt] = head[b]; head[b] = cnt++;
    }

    static class Edge{
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static class E{
        int next;
        int to;

        E(int to, int next) {
            this.next = next;
            this.to = to;
        }
    }

    /**
     * DFS遍历树，计算每个顶点的大小和重儿子
     */
    private static void dfsMarkSon(int v, int f, int d) {
        vDepth[v] = d;
        fa[v] = f;
        size[v] = 1;
        for (int i = head[v]; i != -1; i = next[i]) {
            int u = to[i];
            if (f != u) {
                dfsMarkSon(u, v, d + 1);
                size[v] += size[u];
                if (son[v] == -1 || size[u] > size[son[v]]) son[v] = u;
            }
        }
    }

    /**
     * DFS构造重链
     */
    private static void treeFractor(int v, int top) {
        vSeq[v] = ++num;
        vTop[v] = top;
        if (son[v] == -1) {
            return;
        }
        treeFractor(son[v], vTop[v]);
        for (int i = head[v]; i != -1; i = next[i]) {
            int u = to[i];
            if (u != fa[v] && u != son[v]) {
                treeFractor(u, u);
            }
        }
    }

    /**
     * 将边权转为点权
     */
    private static void weigthTransfer() {
        for (int i = 1; i < n; i++) {
            if (vSeq[line[i].from] < vSeq[line[i].to]) {
                line[i].from ^= line[i].to;
                line[i].to ^= line[i].from;
                line[i].from ^= line[i].to;
            }
            weigth[vSeq[line[i].from]] = line[i].weight;
        }
    }

    /**
     * 初始化线段树
     * @param l
     * @param r
     * @param k
     */
    private static void lineTreeInit(int l, int r, int k) {
        if (l == r) {
            sum[k] = weigth[l];
            return;
        }
        int mid = (l + r) >> 1;
        lineTreeInit(l, mid, k << 1);
        lineTreeInit(mid + 1, r, k << 1 | 1);
        pushUp(k);
    }

    private static void pushUp(int k) {
        sum[k] = sum[k << 1] + sum[k << 1 | 1];
    }

    private static long query(int s, int t, int i, int l, int r) {
        if (s <= l && r <= t) {
            return sum[i];
        }
        int res = 0;
        int mid = (l + r) >> 1;
        if (mid >= s) res += query(s, t, i<<1, l, mid);
        if (mid < t) res += query(s, t, i<<1|1, mid+1, r);
        return res;
    }

    /**
     * 单点更新
     * @param i 更新索引
     * @param w 更新后的值
     */
    static void update(int l, int r, int k, int i, long w) {
        if (l == r) {
            sum[k] = w;
            return;
        }
        int mid = (l + r) >> 1;
        if (i <= mid) {
            update(l, mid, k <<1, i, w);
        } else {
            update(mid + 1, r, k << 1|1, i, w);
        }
        pushUp(k);
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = new StringTokenizer("");
        }

        private void eat(String s) {
            tokenizer = new StringTokenizer(s);
        }

        public String nextLine() {
            try {
                return reader.readLine();
            } catch (Exception e) {
                return null;
            }
        }

        public boolean hasNext() {
            while (!tokenizer.hasMoreTokens()) {
                String s = nextLine();
                if (s == null)
                    return false;
                eat(s);
            }
            return true;
        }

        public String next() {
            hasNext();
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

    }

}
