package xp.oj.tree;

import java.util.Scanner;

/**
 * 树最大独立集，树形dp
 * 树的最大独立集是指在树中选择不直接相连的定点，这些定点组成的集合最大。一般解法使用DFS+DP解决。以poj2342-Anniversary
 * party为例子，实现树最大独立集代码。
 * 问题描述
 * 题目大意：某大学举行周年会，有些人要参加，每个人都有个活跃度，但是不能让一个人和他的直接上司同时参加，已知这些人的直
 * 接上司，求最大的活跃度。
 * 问题分析
 * 因为每个人只有一个上级，而且除了老板所有人必定有一个上级，因此可以通过直接上下级连边构成一颗树。直接上下级之间的两个
 * 人不能同时与会，所以问题就变成了一个独立集问题。
 */
public class AnniversaryParty {

    public static void main(String[] args) {
//        new AnniversaryParty().solve();

        double interval = 500;
        int a = 800;
        System.out.println(a/interval);
    }

    int[] award;
    Edge[] head;
    int[][] dp; // 第i个顶点选或者不选
    private void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        init(n);
        for (int i = 1; i <= n; i++) {
            award[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            int s = in.nextInt();
            int f = in.nextInt();
            addEdge(s,f);
        }
        dfs(1, 0); // 任意顶点开始都可以
        System.out.println(Math.max(dp[1][0], dp[1][1]));
        
    }

    private void dfs(int i, int f) {
        dp[i][1] = award[i];
        for (Edge e = head[i]; e != null; e = e.next) {
            if (e.to == f) continue;
            dfs(e.to, i);
            dp[i][0] += Math.max(dp[e.to][0], dp[e.to][1]);
            dp[i][1] += dp[e.to][0];
        }
    }

    private void addEdge(int s, int f) {
        head[s] = new Edge(head[s], f);
        head[f] = new Edge(head[f], s);
    }

    private void init(int n) {
        award = new int[n + 1];
        head = new Edge[n + 1];
        dp = new int[n + 1][2];

    }

    class Edge{
        Edge next;
        int to;

        public Edge(Edge next, int to) {
            this.next = next;
            this.to = to;
        }
    }
}
