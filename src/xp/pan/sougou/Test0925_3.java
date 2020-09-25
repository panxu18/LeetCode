package xp.pan.sougou;

import java.util.ArrayList;
import java.util.Stack;

public class Test0925_3 {


    static class Interval {
      int start;
      int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
//        10,14,[[0,1],[0,2],[0,3],[1,4],[3,4],[2,6],[4,5],[5,6],[6,7],[7,8],[6,9],[9,10],[8,-1],[10,-1]]
        int n = 10, m = 14;
        Interval[] conn = {new Interval(0, 1), new Interval(0, 2), new Interval(0, 3), new Interval(1, 4),
                new Interval(3, 4), new Interval(2, 6), new Interval(4, 5), new Interval(5, 6),
                new Interval(6, 7), new Interval(7, 8), new Interval(6, 9), new Interval(9, 10), new Interval(8, -1),
                new Interval(10, -1)};
        Interval res = trim(n, m, conn);
        System.out.printf("%d %d%n", res.start, res.end);
    }

    static ArrayList<ArrayList<Interval>> head = new ArrayList<>();
    static boolean[] used = new boolean[1007];
    static boolean[] ans = new boolean[1007];
    public static Interval trim (int N, int M, Interval[] conn) {
        // write code here
        for (int i = 0; i <= N; i++) {
            head.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            Interval e = conn[i];
            head.get(e.start).add(e);
        }
        Stack<Integer> path = new Stack<>();
        dfs(0, path);
        int cnt = 0;
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            if (ans[i]) {
                cnt++;
                sum += i;
                sum %= 1000007;
            }
        }
        return new Interval(cnt, sum);
    }

    private static void dfs(int i, Stack<Integer> path) {
        if (i == -1) {
            for (Integer j : path) {
                ans[j] = true;
            }
            return;
        }
        if (used[i]) {
            return;
        }
        used[i] = true;
        path.push(i);
        ArrayList<Interval> cur = head.get(i);
        for (Interval e : cur) {
            if (e.end == -1 || !used[e.end]) {
                dfs(e.end, path);
            }
        }
        path.pop();
        used[i] = false;

    }

}
