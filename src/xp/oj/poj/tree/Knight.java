package xp.oj.poj.tree;

import java.util.Scanner;

/**
 * 基环树，最大独立集
 *
 * 基环树
 * 基环树和普通树的区别在于多了一条边（只能一条），在基环树上进行dp时因为有环，所以状态转移会循环，因此选择将环断开，
 * 然后使用树的最大独立集计算。假设断开的是边是<u,v>，那么在dfs时没有考虑u和v不能同时选择，所以分别计算不要u或者不要v时
 * 最大独立集，然后取其较大的值。以
 * zjoi2008 骑士为例子使用基环树求最大独立集。
 * 问题描述
 * 每个骑士有且只有一个不可以同时上场的骑士，和一个战斗力。求最大战斗力。
 * 问题分析
 * 以不能同时上场的两个骑士构建一条边，构成n个骑士n条边的基环树或者基环森林。在每个基环树中选择一个顶点集合使战斗力最大，
 * 就是一个最大独立集问题。每次断开一条边，然后以边的两个顶点开始分别进行一次dfs，取其最大值。
 * 基环树可以使用无向树也可以使用有向树，使用无向树的时候在代码实现中要注意一对节点中会可能会有4条边。构造有向树的时候
 * 一定要构造成每个节点的入度为1，出度大于等于0，不然就没办法遍历整个树了。
 */
public class Knight {

    public static void main(String[] args) {
        new Knight().solve();
    }

    int[] head;
    int[] to;
    int[] next;
    int cnt = 1;
    int[] weight;
    int[] d;
    long[][] dp;
    int mark = 0;
    int[] queue;
    private void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        init(n);
        for (int i = 1; i <= n; i++) {
            weight[i] = in.nextInt();
            d[i] = in.nextInt();
            addEdge(d[i], i);
        }
        in.close();
        long ans = 0;
        for (int i = 1; i <= n ; i++) {
            if (weight[i] < 0) continue;
            getLoop(i);
            dfs(mark, mark);
            long tem = dp[mark][0];
            dfs(d[mark], d[mark]);
            ans += Math.max(tem, dp[d[mark]][0]);
        }
        System.out.println(ans);
    }

    /**
     * 有向树遍历
     * @param u
     * @param ban 断边的端点，dfs的根节点
     */
    private void dfs(int u, int ban) {


        int h = 0;
        int top = 0;
        // 元素入队
        if (weight[u] > 0)
            weight[u] = -weight[u];
        queue[top++] = u;
        dp[u][0] = 0;
        dp[u][1] = Math.abs(weight[u]);
        while (h < top) {
            for (int i = head[queue[h]]; i != 0; i = next[i]) {
                if (to[i] == ban) continue;
                if (weight[to[i]] > 0)
                    weight[to[i]] = -weight[to[i]];
                queue[top++] = to[i];
                dp[to[i]][0] = 0;
                dp[to[i]][1] = Math.abs(weight[to[i]]);
            }
            h++;
        }
        // 反向遍历
        while (top > 0) {
            top--;
            int now = queue[top];
            dp[d[now]][0] += Math.max(dp[now][0], dp[now][1]);
            dp[d[now]][1] += dp[now][0];
        }
//        dp[u][0] = 0;
//        dp[u][1] = Math.abs(weight[u]);
//
//        for (int i = head[u]; i != 0 ; i = next[i]) {
//
//            if (to[i] == ban) continue;
//            dfs(to[i], ban);
//            dp[u][0] += Math.max(dp[to[i]][0], dp[to[i]][1]);
//            dp[u][1] += dp[to[i]][0];
//        }
    }

    /**
     * 在环上找根节点，不会一定将整棵树访问完
     * @param u
     */
    private void getLoop(int u) {
        weight[u] = -weight[u];
        while (weight[d[u]] > 0) {
            u = d[u];
            weight[u] = -weight[u];
        }
        mark = u;
    }

    /**
     * 构造有向树
     * @param s
     * @param t
     */
    private void addEdge(int s, int t) {
        to[cnt] = t;
        next[cnt] = head[s];
        head[s] = cnt++;
    }

    private void init(int n) {
        n = n+ 10;
        head = new int[n + 1];
        to = new int[n + 1];
        next = new int[n + 1];
        weight = new int[n + 1];
        dp = new long[n + 1][2];
        d = new int[n + 1];
        queue = new int[n + 1];
    }
}
