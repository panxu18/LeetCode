package xp.tiaozhanchengxujingsai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PopularCows {

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
