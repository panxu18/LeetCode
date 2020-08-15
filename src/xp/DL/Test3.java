package xp.DL;

import java.util.*;

public class Test3 {

    private static final int[] p = new int[100005];
    private static final int[] rank = new int[100005];
    private static final HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        init();
        for (int i = 0; i < m; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            u(a, b);
        }

        for (int i = 1; i <= n; i++) {
            int p = find(i);
            if (map.containsKey(p)) {
                map.get(p).add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(p, list);
            }
        }

        ArrayList<ArrayList<Integer>> res = new ArrayList<>(map.values());
        res.forEach(Collections::sort);
        res.sort(Comparator.comparingInt(list -> list.get(0)));
        System.out.println(res.size());
        for (ArrayList<Integer> list : res) {
            for (int i = 0; i < list.size() - 1; i++) {
                System.out.printf("%d ", list.get(i));
            }
            System.out.println(list.get(list.size() - 1));
        }
    }

    private static void u(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) {
            return;
        }
        if (rank[x] < rank[y]) {
            p[px] = py;
        } else {
            p[py] = px;
            if (rank[px] == rank[py]) {
                rank[px]++;
            }
        }
    }

    private static int find(int x) {
        if (p[x] == x) return x;
        return p[x] = find(p[x]);
    }

    private static void init() {
        for (int i = 0; i < p.length; i++) {
            p[i] = i;
        }
        Arrays.fill(rank, 0);
    }
}
