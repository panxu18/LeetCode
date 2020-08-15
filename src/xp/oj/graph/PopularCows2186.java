package xp.oj.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 强连通分量
 *
 * 问题描述
 * 每一头牛的愿望就是变成一头最受欢迎的牛。现在有N头牛，给你M对整数(A,B)，表示牛A认为牛B受欢迎。 这种关系是具有传递性的，如果A认为B受欢迎，B认为C受欢迎，那么牛A也认为牛C受欢迎。你的任务是求出有多少头牛被所有的牛认为是受欢迎的。
 *
 * 问题分析
 * 如果将边B->A表示为A认可B是红人，那么一头牛被所有牛认可的条件为，通过它可以遍历到所有牛。但是，对每一头牛都重复这个操作时间复杂度为O(NM)。如果一头牛C被所有牛认可，那么和C在一个强联通分量内的牛都被所有牛认可，不在强联通分量之内的牛不被所有牛认可。因此，解决这个问题分为两步：
 * 1. 找到一头被所有牛认可的牛
 * 2. 求该头牛所在的联通分量的大小
 */
public class PopularCows2186 {

    static boolean[] used;
    static ArrayList<ArrayList<Integer>> graph;
    static ArrayList<ArrayList<Integer>> regraph;
//    static int[] seq;
//    static int untime;
    static int last;
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int m = cin.nextInt();
        used = new boolean[n + 1];
        graph = new ArrayList<ArrayList<Integer>>(n + 1);
        regraph = new ArrayList<ArrayList<Integer>>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Integer>());
            regraph.add(new ArrayList<Integer>());
        }
//        seq = new int[N];
        for (int i = 0; i < m; i++) {
            int a = cin.nextInt();
            int b = cin.nextInt();
            graph.get(b).add(a);
            regraph.get(a).add(b);
        }

        Arrays.fill(used, false);
        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                dfs(i, graph);
            }
        }
        Arrays.fill(used, false);
        if (dfs(last, graph) < n) {
            System.out.println(0);
            return;
        }
        Arrays.fill(used, false);
        System.out.println(dfs(last, regraph));

    }

    private static int dfs(int v, ArrayList<ArrayList<Integer>> graph) {
        used[v] = true;
        int res = 1;
        for (int e : graph.get(v)) {
            if (!used[e]) {
                res += dfs(e, graph);
            }
        }
//        seq[untime++] = y;
        last = v;
        return res;
    }

}
